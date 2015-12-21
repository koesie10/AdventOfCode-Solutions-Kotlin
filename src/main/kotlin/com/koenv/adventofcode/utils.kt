package com.koenv.adventofcode

import kotlin.text.MatchResult

// from https://github.com/kotlin-projects/kotlin-euler/blob/master/src/main/kotlin/euler/Iterators.kt#L106
fun <T : Any> Collection<T>.permutations(): Sequence<List<T>> = if (size == 1) sequenceOf(this.toList()) else {
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

operator fun MatchResult?.get(index: Int): String {
    return this!!.groups[index]!!.value
}