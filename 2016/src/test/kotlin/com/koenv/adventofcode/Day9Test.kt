package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day9Test {
    @Test
    fun part1Example1() {
        assertEquals("ADVENT", Day9.decompress("ADVENT"))
    }

    @Test
    fun part1Example2() {
        assertEquals("ABBBBBC", Day9.decompress("A(1x5)BC"))
    }

    @Test
    fun part1Example3() {
        assertEquals("XYZXYZXYZ", Day9.decompress("(3x3)XYZ"))
    }

    @Test
    fun part1Example4() {
        assertEquals("ABCBCDEFEFG", Day9.decompress("A(2x2)BCD(2x2)EFG"))
    }

    @Test
    fun part1Example5() {
        assertEquals("(1x3)A", Day9.decompress("(6x1)(1x3)A"))
    }

    @Test
    fun part1Example6() {
        assertEquals("X(3x3)ABC(3x3)ABCY", Day9.decompress("X(8x2)(3x3)ABCY"))
    }

    @Test
    fun part1Real() {
        assertEquals(152851, Day9.countDecompressed(getInput(9)))
    }
}