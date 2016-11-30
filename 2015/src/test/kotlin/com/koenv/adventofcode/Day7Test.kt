package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day7Test {
    @Test
    fun part1Example1() {
        val obj = Day7()
        assertEquals(0, obj.numberOfWires)
        obj.parseCommand("123 -> x")
        assertEquals(1, obj.numberOfWires)
        assertEquals(123, obj["x"])
    }

    @Test
    fun part1Example2() {
        val obj = Day7()
        assertEquals(0, obj.numberOfWires)
        obj.parseCommand("x AND y -> z")
        obj.parseCommand("123 -> x")
        obj.parseCommand("456 -> y")
        assertEquals(3, obj.numberOfWires)
        assertEquals(123, obj["x"])
    }

    @Test
    fun part1Example3() {
        val obj = Day7()
        assertEquals(0, obj.numberOfWires)
        obj.parseCommand("p LSHIFT 2 -> q")
        obj.parseCommand("123 -> p")
        assertEquals(2, obj.numberOfWires)
        assertEquals(123, obj["p"])
        assertEquals(492, obj["q"])
    }

    @Test
    fun part1Example4() {
        val obj = Day7()
        assertEquals(0, obj.numberOfWires)
        obj.parseCommand("NOT e -> f")
        obj.parseCommand("123 -> e")
        assertEquals(2, obj.numberOfWires)
        assertEquals(123, obj["e"])
        assertEquals(65412, obj["f"])
    }

    @Test
    fun part1Example5() {
        val obj = Day7()
        assertEquals(0, obj.numberOfWires)
        obj.parseCommands(
                """
                    123 -> x
                    456 -> y
                    x AND y -> d
                    x OR y -> e
                    x LSHIFT 2 -> f
                    y RSHIFT 2 -> g
                    NOT x -> h
                    NOT y -> i
                """
        )
        assertEquals(8, obj.numberOfWires)
        assertEquals(72, obj["d"])
        assertEquals(507, obj["e"])
        assertEquals(492, obj["f"])
        assertEquals(114, obj["g"])
        assertEquals(65412, obj["h"])
        assertEquals(65079, obj["i"])
        assertEquals(123, obj["x"])
        assertEquals(456, obj["y"])
    }

    @Test
    fun part1Real() {
        val obj = Day7()
        assertEquals(0, obj.numberOfWires)
        obj.parseCommands(getInput(7))
        assertEquals(339, obj.numberOfWires)
        assertEquals(46065, obj["a"])
    }

    @Test
    fun part2Real() {
        val obj = Day7()
        assertEquals(0, obj.numberOfWires)
        obj.parseCommands(getInput(7).replace("1674 -> b", "46065 -> b")) // this is my hacky way :)
        assertEquals(339, obj.numberOfWires)
        assertEquals(14134, obj["a"])
    }
}