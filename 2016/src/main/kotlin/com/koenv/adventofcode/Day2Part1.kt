package com.koenv.adventofcode

object Day2Part1 {
    fun getBathroomCode(input: String): Int {
        // store the 'coordinates' on the keypad
        var x = 0
        var y = 0

        // and the result we will have as keycode
        var result = 0

        val lines = input.lines().filter { !it.isBlank() } // don't include empty lines
        lines.forEachIndexed { i, line ->
            line.forEach {
                val (xDiff, yDiff) = getXYDiff(it)
                x += xDiff
                y += yDiff

                x = clampToPad(x)
                y = clampToPad(y)
            }

            // the first one is the largest number, so it will be 10^(n-1) as a multiplier
            result += getKeypadNumber(x ,y) * Math.pow(10.0, (lines.size - i - 1).toDouble()).toInt()
        }

        return result
    }

    fun clampToPad(loc: Int): Int {
        if (loc < -1) {
            return -1
        }
        if (loc > 1) {
            return 1
        }
        return loc
    }

    fun getXYDiff(char: Char) = when (char) {
        'U' -> 0 to -1
        'D' -> 0 to 1
        'L' -> -1 to 0
        'R' -> 1 to 0
        else -> throw IllegalStateException("Invalid char $char")
    }

    fun getKeypadNumber(x: Int, y: Int) = when (x to y) {
        -1 to -1 -> 1
        0 to -1 -> 2
        1 to -1 -> 3
        -1 to 0 -> 4
        0 to 0 -> 5
        1 to 0 -> 6
        -1 to 1 -> 7
        0 to 1 -> 8
        1 to 1 -> 9
        else -> throw IllegalStateException("Invalid keypad code ($x, $y)")
    }
}