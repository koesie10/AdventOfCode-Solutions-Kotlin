package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day2Test {
    @Test
    fun squareFeetOfWrappingPaperFirstExample() {
        assertEquals(58, Day2.getSquareFeetOfWrappingPaper("2x3x4"))
    }

    @Test
    fun squareFeetOfWrappingPaperSecondExample() {
        assertEquals(43, Day2.getSquareFeetOfWrappingPaper("1x1x10"))
    }

    @Test
    fun totalSquareFeetOfWrappingPaperReal() {
        assertEquals(1586300, Day2.getTotalSquareFeetOfWrappingPaper(getInput(2)))
    }

    @Test
    fun squareFeetOfRibbonFirstExample() {
        assertEquals(34, Day2.getSquareFeetOfRibbon("2x3x4"))
    }

    @Test
    fun squareFeetOfRibbonSecondExample() {
        assertEquals(14, Day2.getSquareFeetOfRibbon("1x1x10"))
    }

    @Test
    fun totalSquareFeetOfRibbonReal() {
        assertEquals(3737498, Day2.getTotalSquareFeetOfRibbon(getInput(2)))
    }
}