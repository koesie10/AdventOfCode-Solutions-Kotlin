package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day15Test {
    @Test
    fun part1Example() {
        assertEquals(62842880, Day15.getHighestScore(
                """Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3"""))
    }

    @Test
    fun part1Real() {
        assertEquals(21367368, Day15.getHighestScore(getInput(15)))
    }

    @Test
    fun part2Example() {
        assertEquals(57600000, Day15.getBestCalories(
                """Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3"""))
    }

    @Test
    fun part2Real() {
        assertEquals(1766400, Day15.getBestCalories(getInput(15)))
    }
}