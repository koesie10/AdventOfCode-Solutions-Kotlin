package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day2Part2Test {
    @Test
    fun part1Example() {
        assertEquals("5DB3", Day2Part2.getBathroomCode("""ULL
RRDDD
LURDL
UUUUD"""))
    }

    @Test
    fun part1RealInput() {
        assertEquals("9365C", Day2Part2.getBathroomCode(getInput(2)))
    }
}