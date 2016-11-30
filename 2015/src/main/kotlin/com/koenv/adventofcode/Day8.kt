package com.koenv.adventofcode

object Day8 {
    public fun getCharacterCount(input: String): CharacterCount {
        val codeCharacterCount = input.length
        val stringCharacterCount = input
                .replace("\\\"", "\"")
                .replace("\\\\", "\\")
                .replace(HEXADECIMAL_CHARACTER_REGEX, { Integer.parseInt(it.groups[1]!!.value, 16).toChar().toString() })
                .length - 2 // the first and last don't count
        val reencodedCount = input
                .replace("\\\\", "aaaa")
                .replace("\\\"", "aaaa")
                .replace(HEXADECIMAL_CHARACTER_REGEX, "aaaaa")
                .length + 4 // 4 for the start and ending

        return CharacterCount(codeCharacterCount, stringCharacterCount, reencodedCount)
    }

    public fun getPart1Output(input: String): Int {
        return input.lines()
                .map {
                    getCharacterCount(it)
                }
                .map {
                    it.code - it.string
                }
                .sum()
    }

    public fun getPart2Output(input: String): Int {
        return input.lines()
                .map {
                    getCharacterCount(it)
                }
                .map {
                    it.reencoded - it.code
                }
                .sum()
    }

    data class CharacterCount(val code: Int, val string: Int, val reencoded: Int)

    val HEXADECIMAL_CHARACTER_REGEX = "\\\\x([0-9a-fA-F]{2})".toRegex()
}