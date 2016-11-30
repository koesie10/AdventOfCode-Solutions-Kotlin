package com.koenv.adventofcode

import java.util.*

object Day17 {
    public fun getCombinationsThatFit(required: Int, buckets: List<Int>): Int {
        return findValidCombinations(required, buckets).size
    }

    public fun getCombinationsThatFit(required: Int, buckets: String): Int {
        return getCombinationsThatFit(required, buckets.lines().map { it.toInt() })
    }

    public fun getSizeOfMinimumContainers(required: Int, buckets: List<Int>): Int {
        val combinations = findValidCombinations(required, buckets)
        val minimum = combinations.minBy { it.size }!!.size
        return combinations.filter { it.size == minimum }.size
    }

    public fun getSizeOfMinimumContainers(required: Int, buckets: String): Int {
        return getSizeOfMinimumContainers(required, buckets.lines().map { it.toInt() })
    }

    public fun findValidCombinations(required: Int, buckets: List<Int>): List<List<Int>> {
        val combinations = ArrayList<Boolean>()
        buckets.forEach {
            combinations.add(true)
        }
        val validCombinations = arrayListOf<List<Int>>()
        while (combinations.contains(true)) {
            val thisIterationCombinations = combinations.mapIndexed { i, b -> if (b) buckets[i] else 0 }
            if (thisIterationCombinations.sum() == required) {
                validCombinations.add(thisIterationCombinations.filter { it != 0 })
            }

            for (i in (buckets.size - 1 ) downTo 0) {
                if (combinations[i]) {
                    combinations[i] = false
                    break
                } else {
                    combinations[i] = true
                }
            }
        }

        return validCombinations
    }
}