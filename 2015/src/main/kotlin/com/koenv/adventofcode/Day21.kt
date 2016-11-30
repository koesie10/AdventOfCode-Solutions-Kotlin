package com.koenv.adventofcode

object Day21 {
    public fun getMaxGoldSpentAndLose(input: String): Int {
        return getGoldSpent(input, 0, { lhs, rhs -> lhs > rhs}, { it <= 0})
    }

    public fun getMinGoldSpent(input: String): Int {
        return getGoldSpent(input, Integer.MAX_VALUE, { lhs, rhs -> lhs < rhs}, { it > 0})
    }

    private fun getGoldSpent(input: String, initialValue: Int, operator: (Int, Int) -> Boolean, checkOperator: (Int) -> Boolean): Int {
        val result = BOSS_REGEX.find(input)!!
        val boss = Boss(result[1].toInt(), result[2].toInt(), result[3].toInt())

        val weapons = listOf(
                Weapon(8, 4),
                Weapon(10, 5),
                Weapon(25, 6),
                Weapon(40, 7),
                Weapon(74, 8)
        )
        val armors = listOf(
                Armor(0, 0),
                Armor(13, 1),
                Armor(31, 2),
                Armor(53, 3),
                Armor(75, 4),
                Armor(102, 5)
        )
        val damageRings = listOf(
                Ring(0, 0, 0),
                Ring(25, 1, 0),
                Ring(50, 2, 0),
                Ring(100, 3, 0)
        )
        val armorRings = listOf(
                Ring(0, 0, 0),
                Ring(20, 0, 1),
                Ring(40, 0, 2),
                Ring(80, 0, 3)
        )

        var best = initialValue
        for (weapon in weapons) {
            for (armor in armors) {
                for (ring1 in damageRings) {
                    for (ring2 in armorRings) {
                        if (ring1 != ring2) {
                            val playerDamage = weapon.damage + ring1.damage
                            val playerArmor = armor.armor + ring2.armor
                            if (execute(boss, playerDamage, playerArmor, checkOperator)) {
                                val cost = weapon.cost + armor.cost + ring1.cost + ring2.cost
                                if (operator(cost, best)) {
                                    best = cost
                                }
                            }
                        }
                    }
                }
            }
        }

        return best
    }

    private fun execute(boss: Boss, playerDamage: Int, playerArmor: Int, checkOperator: (Int) -> Boolean): Boolean {
        var bossHp = boss.hp;
        var playerHp = 100;
        while (bossHp > 0 && playerHp > 0) {
            bossHp -= Math.max(playerDamage - boss.armor, 1)
            if (bossHp <= 0) {
                break;
            }
            playerHp -= Math.max(boss.damage - playerArmor, 1)
        }
        return checkOperator(playerHp)
    }

    data class Boss(val hp: Int, val damage: Int, val armor: Int)
    data class Weapon(val cost: Int, val damage: Int)
    data class Armor(val cost: Int, val armor: Int)
    data class Ring(val cost: Int, val damage: Int, val armor: Int)

    val BOSS_REGEX = "Hit Points: (\\d+)\\s+Damage: (\\d+)\\s+Armor: (\\d+)".toRegex()
}