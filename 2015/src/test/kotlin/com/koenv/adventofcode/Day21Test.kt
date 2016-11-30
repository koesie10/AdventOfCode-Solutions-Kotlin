package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day21Test {
    @Test
    fun part1Real() {
        assertEquals(111, Day21.getMinGoldSpent(getInput(21)))
    }

    @Test
    fun part2Real() {
        assertEquals(188, Day21.getMaxGoldSpentAndLose(getInput(21)))
    }
}