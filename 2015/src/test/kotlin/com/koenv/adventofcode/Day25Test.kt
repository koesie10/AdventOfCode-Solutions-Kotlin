package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day25Test {
    @Test
    public fun part1Example1() {
        val obj = Day25.NumberGenerator(20151125)

        assertEquals(16, Day25.coordinateToPosition(6, 1))
        assertEquals(21, Day25.coordinateToPosition(1, 6))
        assertEquals(13, Day25.coordinateToPosition(3, 3))
        assertEquals(14, Day25.coordinateToPosition(2, 4))
        assertEquals(19, Day25.coordinateToPosition(3, 4))

        assertEquals(7981243, obj.nth(19 - 2))
    }

    @Test
    public fun part1Example2() {
        val obj = Day25.NumberGenerator(20151125)

        assertEquals(31916031, obj.next())
        assertEquals(18749137, obj.next())
        assertEquals(16080970, obj.next())
        assertEquals(21629792, obj.next())
    }

    @Test
    public fun part1Real() {
        assertEquals(19980801, Day25.getValueAt(20151125, getInput(25)))
    }
}