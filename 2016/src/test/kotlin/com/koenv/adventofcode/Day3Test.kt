package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day3Test {
    @Test
    fun part1Example1() {
        assertEquals(false, Day3.isTrianglePossible("5 10 25"))
    }

    @Test
    fun part1Example2() {
        assertEquals(true, Day3.isTrianglePossible("3 4 5"))
    }

    @Test
    fun part1Real() {
        assertEquals(1032, Day3.getPossibleTriangles(getInput(3)))
    }

    @Test
    fun part2Example() {
        assertEquals(6, Day3.getPossibleTrianglesByColumn("""
101 301 501
102 302 502
103 303 503
201 401 601
202 402 602
203 403 603
        """))
    }

    @Test
    fun part2Real() {
        assertEquals(1838, Day3.getPossibleTrianglesByColumn(getInput(3)))
    }
}