package com.koenv.adventofcode

import java.util.*

object Day24 {
    fun getCombinations(choices: List<Int>, used: MutableSet<Int>, neededValue: Int, numberOfItems: Int, startIndex: Int = 0): MutableList<MutableList<Int>>? {
        if (numberOfItems == 1) {
            var maxSoFar = Int.MIN_VALUE
            if (used.any()) {
                maxSoFar = used.max()!!
            }

            if (neededValue > maxSoFar && choices.contains(neededValue)) {
                return arrayListOf(arrayListOf(neededValue))
            }

            return null
        }

        val results: MutableList<MutableList<Int>> = arrayListOf()
        for (i in startIndex..(choices.size - 1)) {
            val choice = choices[i]

            if (used.contains(choice)) {
                continue
            }

            if (neededValue <= choice) {
                continue;
            }

            used.add(choice)

            var innerResults = getCombinations(choices, used, neededValue - choice, numberOfItems - 1, i + 1)
            if (innerResults != null) {
                innerResults.forEach {
                    it.add(0, choice)
                    results.add(it)
                }
            }

            used.remove(choice)
        }

        if (results.any()) {
            return results
        }

        return null
    }

    fun getValidCombinationsWithLowestAmountOfItems(choices: List<Int>, groups: Int): MutableList<MutableList<Int>>? {
        val maxDepth = choices.size / groups

        var depth = 0
        var validResults: MutableList<MutableList<Int>> = arrayListOf()
        do {
            depth++
            val combinations = getCombinations(choices, hashSetOf(), choices.sum() / groups, depth)

            if (combinations != null) {
                validResults = ArrayList(combinations.map { ArrayList(it) })
            }
        } while (depth < maxDepth && !validResults.any())

        if (validResults.any()) {
            return validResults
        }

        return null
    }

    fun quantumEntanglement(combinations: List<Int>): Long {
        var result = 1L
        combinations.forEach {
            result *= it
        }
        return result
    }

    public fun getQuantumEntanglementOfIdealConfiguration(input: String, groups: Int): Long {
        val choices = input.lines().map { it.toInt() }

        val combinations = getValidCombinationsWithLowestAmountOfItems(choices, groups)!!

        return quantumEntanglement(combinations.filter { it.isNotEmpty() }.minBy { quantumEntanglement(it) }!!)
    }
}