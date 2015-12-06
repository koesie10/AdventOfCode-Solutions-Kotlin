package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day6Test {
    @Test
    fun part1Example1() {
        val obj = Day6Part1(1000, 1000)
        assertEquals(0, obj.numberOfLightsInOnState)
        assertEquals(1000000, obj.numberOfLightsInOffState)
        obj.parseCommand("turn on 0,0 through 999,999")
        assertEquals(1000000, obj.numberOfLightsInOnState)
        assertEquals(0, obj.numberOfLightsInOffState)
    }

    @Test
    fun part1Example2() {
        val obj = Day6Part1(1000, 1000)
        assertEquals(0, obj.numberOfLightsInOnState)
        assertEquals(1000000, obj.numberOfLightsInOffState)
        obj.parseCommand("toggle 0,0 through 999,0")
        assertEquals(1000, obj.numberOfLightsInOnState)
        assertEquals(999000, obj.numberOfLightsInOffState)
    }

    @Test
    fun part1Example2Reversed() {
        val obj = Day6Part1(1000, 1000)
        obj.setStateForAll(Day6Part1.STATE_ON)
        assertEquals(1000000, obj.numberOfLightsInOnState)
        assertEquals(0, obj.numberOfLightsInOffState)
        obj.parseCommand("toggle 0,0 through 999,0")
        assertEquals(999000, obj.numberOfLightsInOnState)
        assertEquals(1000, obj.numberOfLightsInOffState)
    }

    @Test
    fun part1Example3() {
        val obj = Day6Part1(1000, 1000)
        assertEquals(0, obj.numberOfLightsInOnState)
        assertEquals(1000000, obj.numberOfLightsInOffState)
        obj.parseCommand("turn off 499,499 through 500,500")
        assertEquals(0, obj.numberOfLightsInOnState)
        assertEquals(1000000, obj.numberOfLightsInOffState)
    }

    @Test
    fun part1ExampleReversed() {
        val obj = Day6Part1(1000, 1000)
        obj.setStateForAll(Day6Part1.STATE_ON)
        assertEquals(1000000, obj.numberOfLightsInOnState)
        assertEquals(0, obj.numberOfLightsInOffState)
        obj.parseCommand("turn off 499,499 through 500,500")
        assertEquals(999996, obj.numberOfLightsInOnState)
        assertEquals(4, obj.numberOfLightsInOffState)
    }

    @Test
    fun part1Real() {
        val obj = Day6Part1(1000, 1000)
        assertEquals(0, obj.numberOfLightsInOnState)
        assertEquals(1000000, obj.numberOfLightsInOffState)
        obj.parseCommands(getInput(6))
        assertEquals(377891, obj.numberOfLightsInOnState)
        assertEquals(622109, obj.numberOfLightsInOffState)
    }

    @Test
    fun part2Example1() {
        val obj = Day6Part2(1000, 1000)
        assertEquals(0, obj.totalBrightness)
        obj.parseCommand("turn on 0,0 through 0,0")
        assertEquals(1, obj.totalBrightness)
    }

    @Test
    fun part2Example2() {
        val obj = Day6Part2(1000, 1000)
        assertEquals(0, obj.totalBrightness)
        obj.parseCommand("toggle 0,0 through 999,999")
        assertEquals(2000000, obj.totalBrightness)
    }

    @Test
    fun part2Real() {
        val obj = Day6Part2(1000, 1000)
        assertEquals(0, obj.totalBrightness)
        obj.parseCommands(getInput(6))
        assertEquals(14110788, obj.totalBrightness)
    }
}