package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day8Test {
    @Test
    fun example1() {
        assertEquals(Day8.CharacterCount(2, 0, 6), Day8.getCharacterCount("\"\""))
    }

    @Test
    fun example2() {
        assertEquals(Day8.CharacterCount(5, 3, 9), Day8.getCharacterCount("\"abc\""))
    }

    @Test
    fun example3() {
        assertEquals(Day8.CharacterCount(10, 7, 16), Day8.getCharacterCount("\"aaa\\\"aaa\""))
    }

    @Test
    fun example4() {
        assertEquals(Day8.CharacterCount(6, 1, 11), Day8.getCharacterCount("\"\\x27\""))
    }

    @Test
    fun part1AllExamples() {
        assertEquals(12, Day8.getPart1Output("\"\"\n\"abc\"\n\"aaa\\\"aaa\"\n\"\\x27\""))
    }

    @Test
    fun part1Real() {
        assertEquals(1371, Day8.getPart1Output(getInput(8)))
    }

    @Test
    fun part2AllExamples() {
        assertEquals(19, Day8.getPart2Output("\"\"\n\"abc\"\n\"aaa\\\"aaa\"\n\"\\x27\""))
    }

    @Test
    fun part2Real() {
        assertEquals(2117, Day8.getPart2Output(getInput(8)))
    }
}