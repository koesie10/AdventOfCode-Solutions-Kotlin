package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day12Test {
    @Test
    fun getSumFirstExample() {
        assertEquals(6, Day12.getSum("[1,2,3]"))
        assertEquals(6, Day12.getSum("{\"a\":2,\"b\":4}"))
    }

    @Test
    fun getSumSecondExample() {
        assertEquals(3, Day12.getSum("[[[3]]]"))
        assertEquals(3, Day12.getSum("{\"a\":{\"b\":4},\"c\":-1}"))
    }

    @Test
    fun getSumThirdExample() {
        assertEquals(0, Day12.getSum("{\"a\":[-1,1]}"))
        assertEquals(0, Day12.getSum("[-1,{\"a\":1}]"))
    }

    @Test
    fun getSumFourthExample() {
        assertEquals(0, Day12.getSum("[]"))
        assertEquals(0, Day12.getSum("{}"))
    }

    @Test
    fun getSumReal() {
        assertEquals(119433, Day12.getSum(getInput(12)))
    }

    @Test
    fun getSumSkipRedFirstExample() {
        assertEquals(6, Day12.getSum("[1,2,3]", true))
    }

    @Test
    fun getSumSkipRedSecondExample() {
        assertEquals(4, Day12.getSum("[1,{\"c\":\"red\",\"b\":2},3]", true))
    }

    @Test
    fun getSumSkipRedThirdExample() {
        assertEquals(0, Day12.getSum("{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}", true))
    }

    @Test
    fun getSumSkipRedFourthExample() {
        assertEquals(6, Day12.getSum("[1,\"red\",5]", true))
    }

    @Test
    fun getSumSkipRedReal() {
        assertEquals(68466, Day12.getSum(getInput(12), true))
    }
}