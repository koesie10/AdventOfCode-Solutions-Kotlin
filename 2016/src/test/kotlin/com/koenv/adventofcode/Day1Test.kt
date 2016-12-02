package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day1Test {
    @Test
    fun part1Example1() {
        assertEquals(5, Day1.getBlocksAway("R2, L3"))
    }

    @Test
    fun part1Example2() {
        assertEquals(2, Day1.getBlocksAway("R2, R2, R2"))
    }

    @Test
    fun part1Example3() {
        assertEquals(12, Day1.getBlocksAway("R5, L5, R5, R3"))
    }

    @Test
    fun part1RealInput() {
        assertEquals(307, Day1.getBlocksAway(getInput(1)))
    }

    @Test
    fun part2Example() {
        assertEquals(4, Day1.getFirstLocationVisitedTwice("R8, R4, R4, R8"))
    }

    @Test
    fun part2RealInput() {
        assertEquals(165, Day1.getFirstLocationVisitedTwice(getInput(1)))
    }
}