package com.koenv.adventofcode

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

    // from https://github.com/kotlin-projects/kotlin-euler/blob/master/src/main/kotlin/euler/Iterators.kt#L106
    fun <T : Any> Collection<T>.permutations() : Sequence<List<T>> = if (size == 1) sequenceOf(this.toList()) else {
        val iterator = iterator()
        var head = iterator.next()
        var permutations = (this - head).permutations().iterator()

        fun nextPermutation(): List<T>? = if (permutations.hasNext()) permutations.next() + head else {
            if (iterator.hasNext()) {
                head = iterator.next()
                permutations = (this - head).permutations().iterator()
                nextPermutation()
            } else null
        }

        sequence { nextPermutation() }
    }

    val ROUTE_REGEX = "(.*) to (.*) = (\\d+)".toRegex()
}