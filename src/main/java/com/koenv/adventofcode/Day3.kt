package com.koenv.adventofcode

object Day3 {
    public fun getTotalVisitedHouses(input: String): Int {
        var currentPosition = Coordinate(0, 0)
        val visitedHouses = arrayListOf(currentPosition)

        input.forEach {
            currentPosition += getCoordinateDelta(it)
            visitedHouses.add(currentPosition)
        }

        return visitedHouses.distinct().size
    }

    public fun getTotalVisitedHousesWithRoboSanta(input: String): Int {
        var santaCurrentPosition = Coordinate(0, 0)
        var roboSantaCurrentPosition = santaCurrentPosition

        val visitedHouses = arrayListOf(santaCurrentPosition)

        input.forEachIndexed { i, it ->
            if (i % 2 == 0) {
                santaCurrentPosition += getCoordinateDelta(it)
                visitedHouses.add(santaCurrentPosition)
            } else {
                roboSantaCurrentPosition += getCoordinateDelta(it)
                visitedHouses.add(roboSantaCurrentPosition)
            }
        }

        return visitedHouses.distinct().size
    }

    private fun getCoordinateDelta(input: Char): Coordinate {
        when (input) {
            '>' -> {
                return Coordinate(1, 0)
            }
            '<' -> {
                return Coordinate(-1, 0)
            }
            '^' -> {
                return Coordinate(0, -1)
            }
            'v' -> {
                return Coordinate(0, 1)
            }
        }
        throw IllegalArgumentException("Invalid input: $input")
    }

    data class Coordinate(val x: Int, val y: Int) {
        public fun plus(other: Coordinate): Coordinate {
            return Coordinate(x + other.x, y + other.y)
        }
    }
}