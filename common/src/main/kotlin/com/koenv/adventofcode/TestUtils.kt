package com.koenv.adventofcode

fun getInput(day: Int): String {
    return TestUtils::class.java.classLoader.getResourceAsStream("day$day.txt").reader().readText()
}

fun getInput(day: Int, payload: String): String {
    return TestUtils::class.java.classLoader.getResourceAsStream("day${day}_$payload.txt").reader().readText()
}

private class TestUtils // required to have a class to get the resources from