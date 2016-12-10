package com.koenv.adventofcode

import java.util.regex.Pattern

object Day9 {
    fun decompress(input: String): String {
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

    fun countDecompressed(input: String): Int {
        val decompressed = decompress(input)

        return decompressed.replace("\\s", "").trim().length
    }

    val numberRegex: Pattern = Pattern.compile("(\\d+)x(\\d+)\\)")
}