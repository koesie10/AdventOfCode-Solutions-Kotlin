package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day9Test {
    @Test
    fun part1Example() {
        assertEquals(605, Day9.getDistance("""London to Dublin = 464
London to Belfast = 518
Dublin to Belfast = 141"""))
    }

    @Test
    fun part1Real() {
        assertEquals(251, Day9.getDistance(getInput(9)))
    }

    @Test
    fun part2Example() {
        assertEquals(982, Day9.getDistance("""London to Dublin = 464
London to Belfast = 518
Dublin to Belfast = 141""", false))
    }

    @Test
    fun part2Real() {
        assertEquals(898, Day9.getDistance(getInput(9), false))
    }
}