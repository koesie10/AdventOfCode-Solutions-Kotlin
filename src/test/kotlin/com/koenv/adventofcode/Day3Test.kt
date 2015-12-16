package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day3Test {
    @Test
    fun visitedHousesFirstExample() {
        assertEquals(2, Day3.getTotalVisitedHouses(">"))
    }

    @Test
    fun visitedHousesSecondExample() {
        assertEquals(4, Day3.getTotalVisitedHouses("^>v<"))
    }

    @Test
    fun visitedHousesThirdExample() {
        assertEquals(2, Day3.getTotalVisitedHouses("^v^v^v^v^v"))
    }

    @Test
    fun visitedHousesReal() {
        assertEquals(2592, Day3.getTotalVisitedHouses(getInput(3)))
    }

    @Test
    fun visitedHousesRoboSantaFirstExample() {
        assertEquals(3, Day3.getTotalVisitedHousesWithRoboSanta("^v"))
    }

    @Test
    fun visitedHousesRoboSantaSecondExample() {
        assertEquals(3, Day3.getTotalVisitedHousesWithRoboSanta("^>v<"))
    }

    @Test
    fun visitedHousesRoboSantaThirdExample() {
        assertEquals(11, Day3.getTotalVisitedHousesWithRoboSanta("^v^v^v^v^v"))
    }

    @Test
    fun visitedHousesRoboSantaReal() {
        assertEquals(2360, Day3.getTotalVisitedHousesWithRoboSanta(getInput(3)))
    }
}