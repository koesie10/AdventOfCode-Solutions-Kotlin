package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day14Test {
    val INPUT = "qzyelonm"

    @Test
    fun part1Example() {
        assertEquals(22728, Day14.findIndexOfOneTimePadKey("abc", 64, false))
    }

    @Test
    fun part1Real() {
        assertEquals(15168, Day14.findIndexOfOneTimePadKey(INPUT, 64, false))
    }

    @Test
    fun part2Example() {
        assertEquals(22551, Day14.findIndexOfOneTimePadKey("abc", 64, true))
    }

    @Test
    fun part2Real() {
        assertEquals(20864, Day14.findIndexOfOneTimePadKey(INPUT, 64, true))
    }
}