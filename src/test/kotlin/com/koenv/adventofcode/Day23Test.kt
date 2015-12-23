package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day23Test {
    @Test
    fun part1FirstExample() {
        val obj = Day23(
                """inc a
jio a, +2
tpl a
inc a"""
        )

        obj.parse()
        assertEquals(2, obj.registers[0])
        assertEquals(0, obj.registers[1])
    }

    @Test
    fun part1Real() {
        val obj = Day23(getInput(23))
        obj.parse()
        assertEquals(307, obj.registers[1])
    }

    @Test
    fun part2FirstExample() {
        val obj = Day23(
                """inc a
jio a, +2
tpl a
inc a"""
        )

        obj.registers[0] = 1
        obj.parse()
        assertEquals(7, obj.registers[0])
        assertEquals(0, obj.registers[1])
    }

    @Test
    fun part2Real() {
        val obj = Day23(getInput(23))
        obj.registers[0] = 1
        obj.parse()
        assertEquals(160, obj.registers[1])
    }
}