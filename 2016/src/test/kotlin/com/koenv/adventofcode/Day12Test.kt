package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day12Test {
    @Test
    fun part1Example() {
        val obj = Day12()
        obj.parse("""
cpy 41 a
inc a
inc a
dec a
jnz a 2
dec a
""")
        assertEquals(42, obj.registers["a"])
    }

    @Test
    fun part1Real() {
        val obj = Day12()
        obj.parse(getInput(12))
        assertEquals(318009, obj.registers["a"])
    }

    @Test
    fun part2Real() {
        val obj = Day12()
        obj.registers["c"] = 1
        obj.parse(getInput(12))
        assertEquals(9227663, obj.registers["a"])
    }
}