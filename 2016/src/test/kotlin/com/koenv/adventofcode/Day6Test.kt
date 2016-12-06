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

    @Test
    fun part2Example() {
        assertEquals("advent", Day6.getPasswordPart2("""
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
    fun part2Real() {
        assertEquals("cevsgyvd", Day6.getPasswordPart2(getInput(6)))
    }
}