package com.koenv.adventofcode

object Day1 {
    public fun getFloor(input: String): Int {
        return input.map { convertToNumber(it) }.sum()
    }

    public fun getIndexOfBasementEnter(input: String): Int {
        val inputs = input.map { convertToNumber(it) }
        var sum = 0
        inputs.forEachIndexed { i, it ->
            sum += it
            if (sum < 0) {
                return i + 1
            }
        }
        throw IllegalStateException("Santa doesn't enter the basement")
    }

    private fun convertToNumber(input: Char): Int {
        if (input == ')') {
            return -1
        } else if (input == '(') {
            return 1
        }
        throw IllegalArgumentException("Invalid character: $input")
    }
}