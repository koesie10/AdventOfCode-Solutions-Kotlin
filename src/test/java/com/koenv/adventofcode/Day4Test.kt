package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals


class Day4Test {
    @Test
    fun fiveZeroesAtStartOfHashFirstExample() {
        assertEquals(609043, Day4.getIndexOfZeroesAtStart("abcdef", 5))
    }

    @Test
    fun fiveZeroesAtStartOfHashSecondExample() {
        assertEquals(1048970, Day4.getIndexOfZeroesAtStart("pqrstuv", 5))
    }

    @Test
    fun fiveZeroesAtStartOfHashReal() {
        assertEquals(117946, Day4.getIndexOfZeroesAtStart("ckczppom", 5))
    }

    @Test
    fun sixZeroesAtStartOfHashReal() {
        assertEquals(3938038, Day4.getIndexOfZeroesAtStart("ckczppom", 6))
    }
}