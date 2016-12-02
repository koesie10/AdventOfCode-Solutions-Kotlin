package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day2Test {
    @Test
    fun part1Example() {
        assertEquals(1985, Day2.getBathroomCode("""ULL
RRDDD
LURDL
UUUUD"""))
    }

    @Test
    fun part1RealInput() {
        assertEquals(35749, Day2.getBathroomCode(getInput(2)))
    }
}