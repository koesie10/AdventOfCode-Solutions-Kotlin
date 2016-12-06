package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day5Test {
    @Test
    fun part1Example() {
        assertEquals("18f47a30", Day5.getPassword("abc"))
    }

    @Test
    fun part1Real() {
        assertEquals("c6697b55", Day5.getPassword("ffykfhsq"))
    }

    @Test
    fun part2Example() {
        assertEquals("05ace8e3", Day5.getPasswordInOrder("abc"))
    }

    @Test
    fun part2Real() {
        assertEquals("8c35d1ab", Day5.getPasswordInOrder("ffykfhsq"))
    }
}