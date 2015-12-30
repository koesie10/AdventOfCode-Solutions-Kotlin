package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day24Test {
    @Test
    fun part1Example() {
        assertEquals(99, Day24.getQuantumEntanglementOfIdealConfiguration(listOf(1, 2, 3, 4, 5, 7, 8, 9, 10, 11).joinToString("\n"), 3))
    }

    @Test
    fun part1Real() {
        assertEquals(11266889531, Day24.getQuantumEntanglementOfIdealConfiguration(getInput(24), 3))
    }

    @Test
    fun part2Example() {
        assertEquals(44, Day24.getQuantumEntanglementOfIdealConfiguration(listOf(1, 2, 3, 4, 5, 7, 8, 9, 10, 11).joinToString("\n"), 4))
    }

    @Test
    fun part2Real() {
        assertEquals(77387711, Day24.getQuantumEntanglementOfIdealConfiguration(getInput(24), 4))
    }
}