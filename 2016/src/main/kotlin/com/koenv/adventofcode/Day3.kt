package com.koenv.adventofcode

object Day3 {
    fun isTrianglePossible(input: String) = isTrianglePossible(getSides(input))

    private fun isTrianglePossible(sides: List<Int>) = isTrianglePossible(sides[0], sides[1], sides[2])

    private fun isTrianglePossible(a: Int, b: Int, c: Int): Boolean {
        if (a + b <= c) {
            return false
        }

        if (b + c <= a) {
            return false
        }

        if (c + a <= b) {
            return false
        }

        return true
    }

    private fun getSides(input: String): List<Int> {
        val sides = input.split(" ")
                .filter(String::isNotBlank)
                .map(String::toInt)

        if (sides.size != 3) {
            throw IllegalArgumentException("A triangle must have 3 sides: $input")
        }
        return sides
    }

    fun getPossibleTriangles(input: String): Int {
        return input.lines()
                .filter(String::isNotBlank)
                .map { isTrianglePossible(it) }
                .sumBy { if (it) 1 else 0 }
    }

    fun getPossibleTrianglesByColumn(input: String): Int {
        val lines = input.lines().filter(String::isNotBlank)

        var possibilities = 0

        for (i in 0..(lines.size - 1) step 3) {
            val sides = lines.subList(i, i + 3)
                    .map { getSides(it) }
                    .flatten()

            for (j in 0..2) {
                if (isTrianglePossible(sides[j], sides[j + 3], sides[j + 6])) {
                    possibilities++
                }
            }
        }

        return possibilities
    }
}