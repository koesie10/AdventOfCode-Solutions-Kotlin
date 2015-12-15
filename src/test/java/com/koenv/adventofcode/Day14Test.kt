package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day14Test {
    @Test
    fun part1Example1() {
        assertEquals(1120, Day14.getWinningDistanceTravelled("Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.", 1000))
    }

    @Test
    fun part1Example2() {
        assertEquals(1056, Day14.getWinningDistanceTravelled("Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.", 1000))
    }

    @Test
    fun part1ExamplesCombined() {
        assertEquals(
                1120,
                Day14.getWinningDistanceTravelled(
                        """Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.""",
                        1000
                )
        )
    }

    @Test
    fun part1Real() {
        assertEquals(2660, Day14.getWinningDistanceTravelled(getInput(14), 2503))
    }

    @Test
    fun part2Real() {
        assertEquals(1256, Day14.getWinningScore(getInput(14), 2503))
    }
}