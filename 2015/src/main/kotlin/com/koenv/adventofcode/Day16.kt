package com.koenv.adventofcode

object Day16 {
    public fun getIdOfMatchedSue(input: String, rangedValues: Boolean = false): Int {
        val aunts = input.lines().map {
            val result = INSTRUCTION_REGEX.find(it)!!
            val id = result.groups[1]!!.value.toInt()
            var children: Int? = null
            var cats: Int? = null
            var samoyeds: Int? = null
            var pomeranians: Int? = null
            var akitas: Int? = null
            var vizslas: Int? = null
            var goldfish: Int? = null
            var trees: Int? = null
            var cars: Int? = null
            var perfumes: Int? = null
            val rest = result.groups[2]!!.value.trim().split(", ")
            rest.forEach {
                val parts = it.split(": ")
                val key = parts[0]
                val value = parts[1].toInt()
                when (key) {
                    "children" -> children = value
                    "cats" -> cats = value
                    "samoyeds" -> samoyeds = value
                    "pomeranians" -> pomeranians = value
                    "akitas" -> akitas = value
                    "vizslas" -> vizslas = value
                    "goldfish" -> goldfish = value
                    "trees" -> trees = value
                    "cars" -> cars = value
                    "perfumes" -> perfumes = value
                }
            }

            Sue(id, children, cats, samoyeds, pomeranians, akitas, vizslas, goldfish, trees, cars, perfumes)
        }

        return aunts.find {
            3.matches(it.children)
                    && 7.matches(it.cats, greaterThan = rangedValues)
                    && 2.matches(it.samoyeds)
                    && 3.matches(it.pomeranians, fewerThan = rangedValues)
                    && 0.matches(it.akitas)
                    && 0.matches(it.vizslas)
                    && 5.matches(it.goldfish, fewerThan = rangedValues)
                    && 3.matches(it.trees, greaterThan = rangedValues)
                    && 2.matches(it.cars)
                    && 1.matches(it.perfumes)
        }!!.id
    }

    fun Int.matches(actual: Int?, greaterThan: Boolean = false, fewerThan: Boolean = false): Boolean {
        if (actual == null) {
            return true
        }
        if (greaterThan) {
            return actual > this
        }
        if (fewerThan) {
            return this > actual
        }
        return this == actual
    }

    data class Sue(
            val id: Int,
            val children: Int?,
            val cats: Int?,
            val samoyeds: Int?,
            val pomeranians: Int?,
            val akitas: Int?,
            val vizslas: Int?,
            val goldfish: Int?,
            val trees: Int?,
            val cars: Int?,
            val perfumes: Int?
    )

    val INSTRUCTION_REGEX = "Sue (\\d+): (.*)".toRegex()
}