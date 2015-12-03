package com.koenv.adventofcode

internal fun getInput(day: Int): String {
    return TestUtils::class.java.classLoader.getResourceAsStream("day$day.txt").reader().readText()
}

private class TestUtils