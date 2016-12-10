package com.koenv.adventofcode

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class Day8Test {
    @Test
    fun part1Example1() {
        assertArrayEquals(getPart1ExampleArray(), Day8.getFinalPixels("rect 3x2", 7, 3))
    }

    @Test
    fun part1Example2() {
        val array = getPart1ExampleArray()
        array[1][0] = false
        array[1][2] = true

        assertArrayEquals(array, Day8.getFinalPixels("""
rect 3x2
rotate column x=1 by 1
        """, 7, 3))
    }

    @Test
    fun part1Example3() {
        val array = getPart1ExampleArray()

        array[0][0] = false
        array[1][0] = false
        array[2][0] = false
        array[3][0] = false
        array[4][0] = true
        array[5][0] = false
        array[6][0] = true

        array[1][2] = true

        assertArrayEquals(array, Day8.getFinalPixels("""
rect 3x2
rotate column x=1 by 1
rotate row y=0 by 4
        """, 7, 3))
    }

    @Test
    fun part1Example4() {
        val array = getPart1ExampleArray()

        array[0][0] = false
        array[2][0] = false
        array[3][0] = false
        array[4][0] = true
        array[5][0] = false
        array[6][0] = true

        array[1][0] = true
        array[1][1] = false
        array[1][2] = true

        assertArrayEquals(array, Day8.getFinalPixels("""
rect 3x2
rotate column x=1 by 1
rotate row y=0 by 4
rotate column x=1 by 1
        """, 7, 3))

        assertEquals(6, Day8.getFinalPixelCount(array))
    }

    @Test
    fun part1Real() {
        assertEquals(116, Day8.getFinalPixelCount(getInput(8)))
    }

    @Test
    fun part2Real() {
        val out = ByteArrayOutputStream()
        val printStream = PrintStream(out)

        Day8.printPixels(Day8.getFinalPixels(getInput(8)), printStream)

        val result = String(out.toByteArray())

        assertEquals("""
#..#.###...##....##.####.#....###...##..####.####.
#..#.#..#.#..#....#.#....#....#..#.#..#.#.......#.
#..#.#..#.#..#....#.###..#....###..#....###....#..
#..#.###..#..#....#.#....#....#..#.#....#.....#...
#..#.#....#..#.#..#.#....#....#..#.#..#.#....#....
.##..#.....##...##..#....####.###...##..####.####.
""".trim(), result.replace("\r\n", "\n").trim()) // for Windows
    }

    private fun getPart1ExampleArray() = Array<Array<Boolean>>(7, {
        if (it in 0..2) {
            return@Array arrayOf(true, true, false)
        }
        return@Array Array<Boolean>(3, { false })
    })
}