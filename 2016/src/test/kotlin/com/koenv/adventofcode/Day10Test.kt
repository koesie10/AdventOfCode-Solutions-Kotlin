package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day10Test {
    @Test
    fun part1Example() {
        assertEquals(2, Day10.execute("""
value 5 goes to bot 2
bot 2 gives low to bot 1 and high to bot 0
value 3 goes to bot 1
bot 1 gives low to output 1 and high to bot 0
bot 0 gives low to output 2 and high to output 0
value 2 goes to bot 2
        """, 2, 5))
    }

    @Test
    fun part1Real() {
        assertEquals(118, Day10.execute(getInput(10), 61, 17))
    }

    @Test
    fun part2Real() {
        assertEquals(143153, Day10.part2(getInput(10)))
    }
}