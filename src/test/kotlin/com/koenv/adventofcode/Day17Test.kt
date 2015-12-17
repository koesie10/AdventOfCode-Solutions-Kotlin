package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day17Test {
    @Test
    fun part1Example() {
        assertEquals(4, Day17.getCombinationsThatFit(25, listOf(20, 15, 10, 5, 5)))
    }

    @Test
    fun part1Real() {
        assertEquals(4372, Day17.getCombinationsThatFit(150, getInput(17)))
    }

    @Test
    fun part2Example() {
        assertEquals(3, Day17.getSizeOfMinimumContainers(25, listOf(20, 15, 10, 5, 5)))
    }

    @Test
    fun part2Real() {
        assertEquals(4, Day17.getSizeOfMinimumContainers(150, getInput(17)))
    }
}