package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day20Test {
    @Test
    fun part1Example() {
        assertEquals(0, Day20.getPresentsCount(0))
        assertEquals(10, Day20.getPresentsCount(1))
        assertEquals(30, Day20.getPresentsCount(2))
        assertEquals(40, Day20.getPresentsCount(3))
        assertEquals(70, Day20.getPresentsCount(4))
        assertEquals(60, Day20.getPresentsCount(5))
        assertEquals(120, Day20.getPresentsCount(6))
        assertEquals(80, Day20.getPresentsCount(7))
        assertEquals(150, Day20.getPresentsCount(8))
        assertEquals(130, Day20.getPresentsCount(9))
    }

    @Test
    fun part1Real() {
        assertEquals(665280, Day20.getHouseWherePresentsCountIsAtLeast(29000000))
    }

    @Test
    fun part2Real() {
        assertEquals(705600, Day20.getHouseWherePresentsCountIsAtLeast(29000000, 11, 50))
    }
}