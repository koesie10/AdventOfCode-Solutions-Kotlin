package com.koenv.adventofcode

object Day14 {
    public fun getWinningDistanceTravelled(input: String, atTime: Int): Int {
        return getReindeerAfterSeconds(input, atTime).map {
            it.distance
        }.max()!!
    }

    public fun getWinningScore(input: String, atTime: Int): Int {
        return getReindeerAfterSeconds(input, atTime).map {
            it.score
        }.max()!!
    }

    private fun getReindeerAfterSeconds(input: String, atTime: Int): List<Reindeer> {
        val reindeer = arrayListOf<Reindeer>()
        input.lines().forEach {
            val regexResult = INSTRUCTION_REGEX.find(it)!!
            val name = regexResult.groups[1]!!.value
            val speed = regexResult.groups[2]!!.value.toInt()
            val travelTime = regexResult.groups[3]!!.value.toInt()
            val restTime = regexResult.groups[4]!!.value.toInt()
            reindeer.add(Reindeer(name, speed, travelTime, restTime))
        }

        for (t in 1..atTime) {
            reindeer.forEach {
                it.tick()
            }
            val max = reindeer.map {
                it.distance
            }.max()!!
            reindeer.filter { it.distance == max }.forEach { it.score++ }
        }

        return reindeer
    }

    data class Reindeer(val name: String, val speed: Int, val travelTime: Int, val restTime: Int) {
        var flying: Boolean = true
        var counter: Int = 0
        var distance: Int = 0
        var score: Int = 0

        fun tick() {
            if (flying) {
                distance += speed
                if (++counter == travelTime) {
                    flying = false
                    counter = 0
                }
            } else {
                if (++counter == restTime) {
                    flying = true
                    counter = 0
                }
            }
        }
    }

    val INSTRUCTION_REGEX = "(.*) can fly (\\d+) km\\/s for (\\d+) seconds, but then must rest for (\\d+) seconds.".toRegex()
}