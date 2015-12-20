package com.koenv.adventofcode

object Day20 {
    fun getPresentsCount(house: Int, presentsForHouse: Int = 10): Int {
        return (1..house).map {
            if (house % it == 0) {
                return@map it * presentsForHouse
            }
            0
        }.sum()
    }

    fun getHouseWherePresentsCountIsAtLeast(atLeast: Int, presentsForHouse: Int = 10, stopAt: Int? = null): Int {
        val houses = IntArray(1000000)
        (1..999999).forEach { elf ->
            var elfStopsAt = if (stopAt == null) 999999 else stopAt * elf
            if (elfStopsAt > 999999) {
                elfStopsAt = 999999
            }
            (elf..elfStopsAt step elf).forEach {
                houses[it] += elf * presentsForHouse
            }
        }

        houses.forEachIndexed { i, house ->
            if (house >= atLeast) {
                return i
            }
        }

        throw IllegalStateException("Could not find house")
    }
}