package com.koenv.adventofcode

object Day2Part2 {
    fun getBathroomCode(input: String): String {
        // store the 'coordinates' on the keypad
        var x = -2 // we start on the '5'
        var y = 0

        // and the result we will have as keycode
        var result = ""

        val lines = input.lines().filter { !it.isBlank() } // don't include empty lines
        lines.forEachIndexed { i, line ->
            line.forEach {
                val (xDiff, yDiff) = getXYDiff(it)

                // check whether the new value is valid
                if (!isValidPosition(x + xDiff, y + yDiff)) {
                    return@forEach
                }

                x += xDiff
                y += yDiff
            }

            // just append the code
            result += getKeypadCode(x, y)
        }

        return result
    }

    fun isValidPosition(x: Int, y: Int): Boolean {
        if (!isInRange(y, -2, 2)) {
            return false
        }

        val clampValue = when (y) {
            -2, 2 -> 0
            -1, 1 -> 1
            0 -> 2
            else -> throw IllegalStateException("Invalid y $y")
        }

        return isInRange(x, -clampValue, clampValue)
    }

    fun isInRange(value: Int, lower: Int, upper: Int) = value >= lower && value <= upper

    fun getXYDiff(char: Char) = when (char) {
        'U' -> 0 to -1
        'D' -> 0 to 1
        'L' -> -1 to 0
        'R' -> 1 to 0
        else -> throw IllegalStateException("Invalid char $char")
    }

    fun getKeypadCode(x: Int, y: Int) = when (x to y) {
        0 to -2 -> '1'
        -1 to -1 -> '2'
        0 to -1 -> '3'
        1 to -1 -> '4'
        -2 to 0 -> '5'
        -1 to 0 -> '6'
        0 to 0 -> '7'
        1 to 0 -> '8'
        2 to 0 -> '9'
        -1 to 1 -> 'A'
        0 to 1 -> 'B'
        1 to 1 -> 'C'
        0 to 2 -> 'D'
        else -> throw IllegalStateException("Invalid keypad code ($x, $y)")
    }
}