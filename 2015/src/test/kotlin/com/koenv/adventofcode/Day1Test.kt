package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

public class Day1Test {
    @Test
    fun getFloorFirstExample() {
        assertEquals(0, Day1.getFloor("(())"))
        assertEquals(0, Day1.getFloor("()()"))
    }

    @Test
    fun getFloorSecondExample() {
        assertEquals(3, Day1.getFloor("((("))
        assertEquals(3, Day1.getFloor("(()(()("))
    }

    @Test
    fun getFloorThirdExample() {
        assertEquals(3, Day1.getFloor("))((((("))
    }

    @Test
    fun getFloorFourthExample() {
        assertEquals(-1, Day1.getFloor("())"))
        assertEquals(-1, Day1.getFloor("))("))
    }

    @Test
    fun getFloorFifthExample() {
        assertEquals(-3, Day1.getFloor(")))"))
        assertEquals(-3, Day1.getFloor(")())())"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun getFloorInvalidText() {
        Day1.getFloor("()Test")
    }

    @Test
    fun getFloorReal() {
        assertEquals(74, Day1.getFloor(getInput(1)))
    }

    @Test
    fun enterBasementFirstExample() {
        assertEquals(1, Day1.getIndexOfBasementEnter(")"))
    }

    @Test
    fun enterBasementSecondExample() {
        assertEquals(5, Day1.getIndexOfBasementEnter("()())"))
    }

    @Test
    fun enterBasementReal() {
        assertEquals(1795, Day1.getIndexOfBasementEnter(getInput(1)))
    }
}