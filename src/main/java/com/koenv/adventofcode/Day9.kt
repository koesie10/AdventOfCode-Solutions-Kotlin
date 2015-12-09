package com.koenv.adventofcode

import com.google.common.collect.Collections2

object Day9 {
    public fun getDistance(input: String, shortest: Boolean = true): Int {
        val places = hashSetOf<String>()
        val distances = hashMapOf<Pair<String, String>, Int>()

        input.lines().forEach {
            val groups = ROUTE_REGEX.find(it)!!.groups

            val place1 = groups[1]!!.value
            val place2 = groups[2]!!.value
            val distance = groups[3]!!.value.toInt()

            places.add(place1)
            places.add(place2)

            distances.put(place1 to place2, distance)
            distances.put(place2 to place1, distance)
        }

        val result = places.permutations().map {
            it.mapIndexed { i, place ->
                if (i == 0) {
                    return@mapIndexed 0
                }
                distances[place to it[i - 1]]!!
            }.sum()
        }
        if (shortest) {
            return result.min()!!
        } else {
            return result.max()!!
        }
    }

    fun <E> Collection<E>.permutations() = Collections2.permutations(this)

    val ROUTE_REGEX = "(.*) to (.*) = (\\d+)".toRegex()
}