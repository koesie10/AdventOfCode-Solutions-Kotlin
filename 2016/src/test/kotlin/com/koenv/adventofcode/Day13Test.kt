package com.koenv.adventofcode

import org.junit.Assert.assertEquals
import org.junit.Test

class Day13Test {
    @Test
    fun part1Example() {
        assertEquals(11, Day13.getDistance("10", 7 to 4))
    }

    @Test
    fun part1Real() {
        assertEquals(96, Day13.getDistance(getInput(13), 31 to 39))
    }

    @Test
    fun part2Real() {
        assertEquals(141, Day13.findUniqueLocations(getInput(13), 50))
    }
}