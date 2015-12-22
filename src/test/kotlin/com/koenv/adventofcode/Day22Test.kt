package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day22Test {
    @Test
    fun part1Real() {
        assertEquals(953, Day22.leastAmountAndWin(getInput(22)))
    }

    @Test
    fun part2Real() {
        assertEquals(1289, Day22.leastAmountAndWin(getInput(22), true))
    }
}