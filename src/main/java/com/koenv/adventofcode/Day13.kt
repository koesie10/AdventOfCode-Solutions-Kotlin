package com.koenv.adventofcode

object Day13 {
    public fun getTotalChangeInHappiness(input: String, seatSelf: Boolean = false): Int {
        val people = hashSetOf<String>()
        val happinessChanges = hashMapOf<Pair<String, String>, Int>()

        input.lines().forEach {
            val groups = INSTRUCTION_REGEX.find(it)!!.groups

            val name1 = groups[1]!!.value
            val type = if (groups[2]!!.value == "gain") +1 else -1
            val change = groups[3]!!.value.toInt()
            val name2 = groups[4]!!.value

            people.add(name1)
            people.add(name2)

            happinessChanges.put(name1 to name2, type * change)
        }

        if (seatSelf) {
            people.forEach {
                happinessChanges.put("Me" to it, 0)
                happinessChanges.put(it to "Me", 0)
            }
            people.add("Me")
        }

        val result = people.permutations().map {
            it.mapIndexed { i, name ->
                if (i == 0) {
                    return@mapIndexed happinessChanges[it.last() to name]!! + happinessChanges[name to it.last()]!!
                }
                happinessChanges[name to it[i - 1]]!! + happinessChanges[it[i - 1] to name]!!
            }.sum()
        }
        return result.max()!!
    }

    val INSTRUCTION_REGEX = "(.*) would (gain|lose) (\\d+) happiness units by sitting next to (.*).".toRegex()
}