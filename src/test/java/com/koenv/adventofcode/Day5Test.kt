package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day5Test {
    @Test
    fun isNiceFirstExample() {
        assertEquals(true, Day5.isNice("ugknbfddgicrmopn"))
    }

    @Test
    fun isNiceSecondExample() {
        assertEquals(true, Day5.isNice("aaa"))
    }

    @Test
    fun isNiceThirdExample() {
        assertEquals(false, Day5.isNice("jchzalrnumimnmhp"))
    }

    @Test
    fun isNiceFourthExample() {
        assertEquals(false, Day5.isNice("haegwjzuvuyypxyu"))
    }

    @Test
    fun isNiceFifthExample() {
        assertEquals(false, Day5.isNice("dvszwmarrgswjxmb"))
    }

    @Test
    fun isNiceReal() {
        assertEquals(258, Day5.getNumberOfNiceInputs(getInput(5)))
    }

    @Test
    fun newIsNiceFirstExample() {
        assertEquals(true, Day5.newIsNice("qjhvhtzxzqqjkmpb"))
    }

    @Test
    fun newIsNiceSecondExample() {
        assertEquals(true, Day5.newIsNice("xxyxx"))
    }

    @Test
    fun newIsNiceThirdExample() {
        assertEquals(false, Day5.newIsNice("uurcxstgmygtbstg"))
    }

    @Test
    fun newIsNiceFourthExample() {
        assertEquals(false, Day5.newIsNice("ieodomkazucvgmuy"))
    }

    @Test
    fun newIsNiceReal() {
        assertEquals(53, Day5.getNumberOfNewNiceInputs(getInput(5)))
    }
}