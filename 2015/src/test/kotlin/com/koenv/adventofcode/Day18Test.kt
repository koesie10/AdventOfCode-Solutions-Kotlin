package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day18Test {
    @Test
    fun part1Example() {
        val obj = Day18(6, 6)
        obj.setState(
                """.#.#.#
...##.
#....#
..#...
#.#..#
####.."""
        )

        assertEquals(15, obj.numberOfLightsInOnState)
        assertEquals(21, obj.numberOfLightsInOffState)

        obj.tick()

        assertEquals(11, obj.numberOfLightsInOnState)
        assertEquals(25, obj.numberOfLightsInOffState)

        obj.tick()

        assertEquals(8, obj.numberOfLightsInOnState)
        assertEquals(28, obj.numberOfLightsInOffState)

        obj.tick()

        assertEquals(4, obj.numberOfLightsInOnState)
        assertEquals(32, obj.numberOfLightsInOffState)

        obj.tick()

        assertEquals(4, obj.numberOfLightsInOnState)
        assertEquals(32, obj.numberOfLightsInOffState)
    }

    @Test
    fun part1Real() {
        val obj = Day18(100, 100)

        obj.setState(getInput(18))

        assertEquals(4934, obj.numberOfLightsInOnState)
        assertEquals(5066, obj.numberOfLightsInOffState)

        for (i in 1..100) {
            obj.tick()
        }

        assertEquals(768, obj.numberOfLightsInOnState)
        assertEquals(9232, obj.numberOfLightsInOffState)
    }

    @Test
    fun part2Real() {
        val obj = Day18(100, 100)

        obj.setState(getInput(18))

        assertEquals(4934, obj.numberOfLightsInOnState)
        assertEquals(5066, obj.numberOfLightsInOffState)

        for (i in 1..100) {
            obj.tick()
            obj.turnCornersOn()
        }

        assertEquals(781, obj.numberOfLightsInOnState)
        assertEquals(9219, obj.numberOfLightsInOffState)
    }
}