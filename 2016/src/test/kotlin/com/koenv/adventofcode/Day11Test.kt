package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day11Test {
    @Test
    fun part1Example() {
        assertEquals(11, Day11.getRequiredSteps("""
The first floor contains a hydrogen-compatible microchip, and a lithium-compatible microchip.
The second floor contains a hydrogen generator.
The third floor contains a lithium generator.
The fourth floor contains nothing relevant.
        """))
    }

    @Test
    fun part1Real() {
        assertEquals(37, Day11.getRequiredSteps(getInput(11)))
    }

    @Test
    fun part2Real() {
        // This test can take quite a long time, 1-2 mins on my notebook
        assertEquals(61, Day11.getRequiredSteps(getInput(11, "part2")))
    }

    @Test
    fun isValid() {
        assertEquals(true, Day11.isValid(Day11.State(null, 0, listOf(
                0 to 0,
                1 to 1,
                1 to 2
        ))))

        assertEquals(true, Day11.isValid(Day11.State(null, 0, listOf(
                0 to 3,
                1 to 1,
                2 to 2
        ))))

        assertEquals(false, Day11.isValid(Day11.State(null, 0, listOf(
                0 to 1,
                1 to 1
        ))))
    }
}