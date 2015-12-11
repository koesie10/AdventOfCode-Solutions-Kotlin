package com.koenv.adventofcode

object Day11 {
    public fun satisfiesRequirements(input: String): Boolean {
        val satisfies1 = input.mapIndexed { i, c ->
            if (i == 0 || i == 1) {
                return@mapIndexed false
            }
            val previous1 = input[i - 2].toInt()
            val previous2 = input[i - 1].toInt()
            val current = c.toInt()
            return@mapIndexed ((previous1 + 1) == previous2 && (previous2 + 1) == current)
        }.any { it }

        val satisfies2 = input.map { it != 'i' && it != 'o' && it != 'l' }.all { it }

        var pairs: MutableSet<Char> = hashSetOf()

        input.forEachIndexed { i, c ->
            if (i == 0) {
                return@forEachIndexed
            }
            if (c == input[i - 1] && input[i - 2] != c) {
                pairs.add(c)
            }
        }

        val satisfies3 = pairs.size >= 2

        return satisfies1 && satisfies2 && satisfies3
    }

    public fun nextPassword(input: String): String {
        val result = CharArray(input.length)
        for (i in (input.length - 1) downTo 0) {
            var c = input[i]
            if (i == (input.length - 1)) {
                c++
            } else if (result.getOrElse(i + 1, { 'a' }) > 'z') {
                c++
            }

            result[i] = c
        }

        return String(
                result.map {
                    if (it > 'z') {
                        return@map 'a'
                    }
                    it
                }.toCharArray()
        )
    }

    public fun findNextPasswordSatisfyingRequirements(currentPassword: String): String {
        var newPassword: String = currentPassword
        do {
            newPassword = nextPassword(newPassword)
        } while (!satisfiesRequirements(newPassword))

        return newPassword
    }
}