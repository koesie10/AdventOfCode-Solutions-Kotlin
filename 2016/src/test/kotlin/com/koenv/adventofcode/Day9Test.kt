package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day9Test {
    @Test
    fun part1Example1() {
        assertEquals("ADVENT", Day9.decompressPart1("ADVENT"))
    }

    @Test
    fun part1Example2() {
        assertEquals("ABBBBBC", Day9.decompressPart1("A(1x5)BC"))
    }

    @Test
    fun part1Example3() {
        assertEquals("XYZXYZXYZ", Day9.decompressPart1("(3x3)XYZ"))
    }

    @Test
    fun part1Example4() {
        assertEquals("ABCBCDEFEFG", Day9.decompressPart1("A(2x2)BCD(2x2)EFG"))
    }

    @Test
    fun part1Example5() {
        assertEquals("(1x3)A", Day9.decompressPart1("(6x1)(1x3)A"))
    }

    @Test
    fun part1Example6() {
        assertEquals("X(3x3)ABC(3x3)ABCY", Day9.decompressPart1("X(8x2)(3x3)ABCY"))
    }

    @Test
    fun part1Real() {
        assertEquals(152851, Day9.countCharacters(Day9.decompressPart1(getInput(9))))
    }

    @Test
    fun part2Example1() {
        assertEquals("XYZXYZXYZ".length.toLong(), Day9.decompressPart2("(3x3)XYZ"))
    }

    @Test
    fun part2Example2() {
        assertEquals("XABCABCABCABCABCABCY".length.toLong(), Day9.decompressPart2("X(8x2)(3x3)ABCY"))
    }

    @Test
    fun part2Example3() {
        assertEquals(241920, Day9.decompressPart2("(27x12)(20x12)(13x14)(7x10)(1x12)A"))
    }

    @Test
    fun part2Example4() {
        assertEquals(445, Day9.decompressPart2("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN"))
    }

    @Test
    fun part2Real() {
        assertEquals(11797310782, Day9.decompressPart2(getInput(9)))
    }
}