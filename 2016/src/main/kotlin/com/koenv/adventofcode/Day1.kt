package com.koenv.adventofcode

object Day1 {
    fun getBlocksAway(input: String): Int {
        var heading: Int = 0 // heading North, positive is clockwise
        // store our coordinates
        var x: Int = 0
        var y: Int = 0

        input.split(",").map(String::trim).forEach {
            heading = getNewHeading(heading, it[0])

            val blocks = it.substring(1).toInt()

            val (xDiff, yDiff) = getXYDiff(heading, blocks)

            x += xDiff
            y += yDiff
        }

        // the total distance is away is just the total x + total y distance
        return Math.abs(x) + Math.abs(y)
    }

    fun getNewHeading(oldHeading: Int, direction: Char): Int {
        var newHeading = oldHeading

        newHeading += when (direction) {
            'R' -> 90
            'L' -> -90
            else -> throw IllegalStateException("Direction is $direction")
        }

        // make sure we are in the range -360 to 360
        newHeading = newHeading.mod(360)

        // and we don't want negative headings
        if (newHeading < 0) {
            newHeading += 360
        }

        return newHeading
    }

    fun getXYDiff(heading: Int, blocks: Int): Pair<Int, Int> {
        when (heading) {
            0 -> return 0 to -blocks
            90 -> return blocks to 0
            180 -> return 0 to blocks
            270 -> return -blocks to 0
            else -> throw IllegalStateException("Heading is $heading")
        }
    }
}