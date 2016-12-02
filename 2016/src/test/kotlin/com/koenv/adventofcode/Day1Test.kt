package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day1Test {
    @Test
    fun example1() {
        assertEquals(5, Day1.getBlocksAway("R2, L3"))
    }

    @Test
    fun example2() {
        assertEquals(2, Day1.getBlocksAway("R2, R2, R2"))
    }

    @Test
    fun example3() {
        assertEquals(12, Day1.getBlocksAway("R5, L5, R5, R3"))
    }

    @Test
    fun realInput() {
        assertEquals(307, Day1.getBlocksAway(getInput(1)))
    }
}