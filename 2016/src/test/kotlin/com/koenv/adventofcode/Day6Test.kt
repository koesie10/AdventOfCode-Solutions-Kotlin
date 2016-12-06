package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day6Test {
    @Test
    fun part1Example() {
        assertEquals("easter", Day6.getPassword("""
eedadn
drvtee
eandsr
raavrd
atevrs
tsrnev
sdttsa
rasrtv
nssdts
ntnada
svetve
tesnvt
vntsnd
vrdear
dvrsen
enarar
        """))
    }

    @Test
    fun part1Real() {
        assertEquals("xdkzukcf", Day6.getPassword(getInput(6)))
    }
}