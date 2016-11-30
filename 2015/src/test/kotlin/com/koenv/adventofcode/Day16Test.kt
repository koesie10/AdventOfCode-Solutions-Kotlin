package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day16Test {
    @Test
    fun part1Real() {
        assertEquals(213, Day16.getIdOfMatchedSue(getInput(16)))
    }

    @Test
    fun part2Real() {
        assertEquals(323, Day16.getIdOfMatchedSue(getInput(16), rangedValues = true))
    }
}