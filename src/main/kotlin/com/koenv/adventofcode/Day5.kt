package com.koenv.adventofcode

object Day5 {
    public fun isNice(input: String): Boolean {
        val vowels = input.count { char -> "aeiou".any { it == char } }
        if (vowels < 3) {
            return false
        }

        if (!input.mapIndexed { i, c -> c == input.elementAtOrNull(i - 1) }.any { it }) {
            return false
        }

        val disallowed = listOf("ab", "cd", "pq", "xy")

        if (disallowed.any { input.contains(it) }) {
            return false
        }

        return true
    }

    public fun getNumberOfNiceInputs(input: String): Int {
        return input.lines().count {
            isNice(it)
        }
    }

    private val PAIR_REGEX = "([a-z][a-z])[a-z]*\\1".toRegex()
    private val REPETITION_REGEX = "([a-z])[a-z]\\1".toRegex()

    public fun newIsNice(input: String): Boolean {
        return PAIR_REGEX.find(input) != null && REPETITION_REGEX.find(input) != null
    }

    public fun getNumberOfNewNiceInputs(input: String): Int {
        return input.lines().count {
            newIsNice(it)
        }
    }
}