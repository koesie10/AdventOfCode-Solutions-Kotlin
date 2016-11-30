package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day10Test {
    @Test
    fun part1Example1() {
        assertEquals("11", Day10.lookAndSay("1"))
    }

    @Test
    fun part1Example2() {
        assertEquals("21", Day10.lookAndSay("11"))
    }

    @Test
    fun part1Example3() {
        assertEquals("1211", Day10.lookAndSay("21"))
    }

    @Test
    fun part1Example4() {
        assertEquals("111221", Day10.lookAndSay("1211"))
    }

    @Test
    fun part1Example5() {
        assertEquals("312211", Day10.lookAndSay("111221"))
    }

    @Test
    fun part1Real() {
        var result = "3113322113"
        for (i in 0..39) {
            result = Day10.lookAndSay(result)
        }
        assertEquals(329356, result.length)
    }

    @Test
    fun part2Real() {
        var result = "3113322113"
        for (i in 0..49) {
            result = Day10.lookAndSay(result)
        }
        assertEquals(4666278, result.length)
    }
}