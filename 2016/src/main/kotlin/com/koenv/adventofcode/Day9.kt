package com.koenv.adventofcode

import java.util.regex.Pattern

object Day9 {
    fun decompressPart1(input: String): String {
        var pos = 0
        val result = StringBuilder()
        val matcher = numberRegex.matcher(input)

        while (pos < input.length) {
            val char = input[pos]
            if (char == '(') {
                pos++ // parentheses

                if (!matcher.find(pos) || matcher.start() != pos) {
                    throw IllegalArgumentException("Invalid input at $pos: Cannot find the input")
                }

                val length = matcher.group(1).toInt()
                val count = matcher.group(2).toInt()

                pos += matcher.group(0).length

                val repeated = input.substring(pos, pos + length)
                pos += length

                for (i in 1..count) {
                    result.append(repeated)
                }
            } else {
                result.append(char)
                pos++
            }
        }

        return result.toString()
    }

    fun decompressPart2(input: String): Long {
        var pos = 0
        var result = 0L

        while (pos < input.length) {
            val char = input[pos]
            if (char == '(') {
                pos++ // parentheses

                val (length, lengthPos) = getInt(input, pos)
                pos = lengthPos

                val (count, countPos) = getInt(input, pos)
                pos = countPos

                val repeated = input.substring(pos, pos + length)
                pos += length

                result += count * decompressPart2(repeated)
            } else if (char == ' ' || char == '\n' || char == '\r') {
                pos++
            } else {
                result++
                pos++
            }
        }

        return result
    }

    fun getInt(input: String, pos: Int): Pair<Int, Int> {
        var char = input[pos]

        var newPos = pos + 1

        val result = StringBuilder()

        while (char.isDigit()) {
            result.append(char)

            char = input[newPos++]
        }

        return result.toString().toInt() to newPos
    }

    fun countCharacters(input: String): Int {
        return input.replace("\\s", "").trim().length
    }

    val numberRegex: Pattern = Pattern.compile("(\\d+)x(\\d+)\\)")
}