package com.koenv.adventofcode

object Day6 {
    fun countCharactersPerColumn(input: String): List<Map<Char, Int>> {
        val result = mutableMapOf<Int, MutableMap<Char, Int>>()

        input.lines()
                .filter(String::isNotBlank)
                .map(String::toCharArray)
                .forEach {
                    it.filter(Char::isLetter)
                            .forEachIndexed { i, c ->
                                if (!result.contains(i)) {
                                    result[i] = mutableMapOf()
                                }
                                val map = result[i]!!
                                if (!map.containsKey(c)) {
                                    map[c] = 0
                                }
                                map[c] = map[c]!! + 1
                            }
                }

        return result.values.toList()
    }

    fun getPassword(input: String): String {
        val counts = countCharactersPerColumn(input)

        return String(counts.map {
            it.toList()
                    .sortedByDescending { it.second }
                    .first()
                    .first
        }.toCharArray())
    }

    fun getPasswordPart2(input: String): String {
        val counts = countCharactersPerColumn(input)

        return String(counts.map {
            it.toList()
                    .sortedBy { it.second }
                    .first()
                    .first
        }.toCharArray())
    }
}