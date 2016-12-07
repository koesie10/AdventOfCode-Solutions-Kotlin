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
                        throw IllegalStateException("Nested hypernet")
                    }
                    result.add(Part(currentType, currentPart.toString()))

                    currentType = Type.HYPERNET
                    currentPart.setLength(0) // clear it
                }
                ']' -> {
                    if (currentType != Type.HYPERNET) {
                        throw IllegalStateException("Invalid end of hypernet")
                    }
                    result.add(Part(currentType, currentPart.toString()))

                    currentType = Type.IP
                    currentPart.setLength(0) // clear it
                }
                else -> currentPart.append(it)
            }
        }

        if (currentType == Type.HYPERNET) {
            throw IllegalStateException("Unfinished hypernet")
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
            if (chars[i] == chars[i+3] && chars[i + 1] == chars[i + 2]) {
                return chars[i] != chars[i + 1] // it cannot be a continuous string
            }
        }

        return false
    }

    fun countIpsThatSupportTls(input: String) = input.lines()
            .filter(String::isNotBlank)
            .map { supportsTls(it.trim()) }
            .count { it }

    data class Part(
            val type: Type,
            val value: String
    )

    enum class Type {
        IP,
        HYPERNET
    }
}