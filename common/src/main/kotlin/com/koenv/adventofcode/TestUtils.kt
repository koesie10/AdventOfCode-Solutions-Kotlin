package com.koenv.adventofcode

fun getInput(day: Int): String {
    return TestUtils::class.java.classLoader.getResourceAsStream("day$day.txt").reader().readText()
}

private class TestUtils // required to have a class to get the resources from