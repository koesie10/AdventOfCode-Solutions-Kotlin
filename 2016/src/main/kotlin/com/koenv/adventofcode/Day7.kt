package com.koenv.adventofcode

object Day7 {
    fun parseParts(input: String): List<Part> {
        val result = mutableListOf<Part>()

        var currentType = Type.IP
        val currentPart = StringBuilder()

        val chars = input.toCharArray()

        chars.forEach {
            when (it) {
                '[' -> {
                    if (currentType == Type.HYPERNET) {
                        throw IllegalArgumentException("Nested hypernet")
                    }
                    result.add(Part(currentType, currentPart.toString()))

                    currentType = Type.HYPERNET
                    currentPart.setLength(0) // clear it
                }
                ']' -> {
                    if (currentType != Type.HYPERNET) {
                        throw IllegalArgumentException("Invalid end of hypernet")
                    }
                    result.add(Part(currentType, currentPart.toString()))

                    currentType = Type.IP
                    currentPart.setLength(0) // clear it
                }
                else -> currentPart.append(it)
            }
        }

        if (currentType == Type.HYPERNET) {
            throw IllegalArgumentException("Unfinished hypernet")
        }

        result.add(Part(currentType, currentPart.toString()))

        return result
    }

    fun supportsTls(parts: List<Part>): Boolean {
        val hasNoAbbaInHypernet = parts
                .filter { it.type == Type.HYPERNET }
                .map { !containsAbba(it.value) }
                .all { it }

        val hasAbbaInRest = parts
                .filter { it.type == Type.IP }
                .map { containsAbba(it.value) }
                .any { it }

        return hasNoAbbaInHypernet && hasAbbaInRest
    }

    fun supportsTls(input: String) = supportsTls(parseParts(input))

    fun containsAbba(input: String): Boolean {
        val chars = input.toCharArray()

        for (i in 0..chars.size - 4) {
            if (chars[i] == chars[i + 3] && chars[i + 1] == chars[i + 2]) {
                return chars[i] != chars[i + 1] // it cannot be a continuous string
            }
        }

        return false
    }

    fun countIpsThatSupport(input: String, predicate: (String) -> Boolean) = input.lines()
            .filter(String::isNotBlank)
            .map { predicate(it.trim()) }
            .count { it }

    fun countIpsThatSupportTls(input: String) = countIpsThatSupport(input, { supportsTls(it) })

    fun getAbas(input: String): List<String> {
        val chars = input.toCharArray()

        val result = mutableListOf<String>()

        for (i in 0..chars.size - 3) {
            if (chars[i] == chars[i + 2] && chars[i] != chars[i + 1]) {
                result.add(String(charArrayOf(chars[i], chars[i + 1], chars[i + 2])))
            }
        }

        return result
    }

    fun getMatchingBab(input: String): String {
        if (input.length != 3) {
            throw IllegalArgumentException("Invalid ABA")
        }

        return String(charArrayOf(input[1], input[0], input[1]))
    }

    fun supportsSsl(input: String) = supportsSsl(parseParts(input))

    fun supportsSsl(parts: List<Part>): Boolean {
        val abas = parts
                .filter { it.type == Type.IP }
                .map { getAbas(it.value) }
                .flatten()

        val babs = parts
                .filter { it.type == Type.HYPERNET }
                .map { getAbas(it.value) }
                .flatten()
                .map { getMatchingBab(it) }

        return abas.intersect(babs).any()
    }

    fun countIpsThatSupportSsl(input: String) = countIpsThatSupport(input, { supportsSsl(it) })

    data class Part(
            val type: Type,
            val value: String
    )

    enum class Type {
        IP,
        HYPERNET
    }
}