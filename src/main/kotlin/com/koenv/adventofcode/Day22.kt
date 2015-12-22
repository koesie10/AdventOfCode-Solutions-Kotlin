package com.koenv.adventofcode

import java.util.*

object Day22 {
    public fun leastAmountAndWin(input: String, hardMode: Boolean = false): Int {
        val hero = Player("Hero", 50, 0, 500, hpLostPerTurn = if (hardMode) 1 else 0)
        val result = BOSS_REGEX.find(input)!!
        val boss = Player("Boss", result[1].toInt(), result[2].toInt(), 0)

        val game = Game(hero, boss)

        game.bruteforce(listOf())

        return game.lowestMana
    }

    data class Player(val name: String, var hp: Int, var attack: Int, var mana: Int, var armor: Int = 0, var manaUsed: Int = 0, val hpLostPerTurn: Int = 0) {
        var shieldEffect = 0
        var poisonEffect = 0
        var rechargeEffect = 0

        val isDead: Boolean
            get() = hp <= 0

        fun action(enemy: Player, name: String) {
            if (isDead || enemy.isDead) {
                return
            }

            hp -= hpLostPerTurn

            if (isDead || enemy.isDead) {
                return
            }

            this.runEffect()
            enemy.runEffect()

            if (isDead || enemy.isDead) {
                return
            }

            when (name) {
                "Attack" -> attack(enemy)
                "Magic Missile" -> magicMissile(enemy)
                "Drain" -> drain(enemy)
                "Shield" -> shield()
                "Poison" -> poison(enemy)
                "Recharge" -> recharge()
            }
        }

        fun spend(points: Int): Boolean {
            if (mana >= points) {
                mana -= points
                manaUsed += points
                return true
            }
            return false
        }

        fun attack(enemy: Player) {
            val damage = attack - enemy.armor
            enemy.hp -= Math.max(damage, 1)
        }

        fun magicMissile(enemy: Player) {
            if (spend(53)) {
                enemy.hp -= 4
            }
        }

        fun drain(enemy: Player) {
            if (spend(73)) {
                hp += 2
                enemy.hp -= 2
            }
        }

        fun shield() {
            if (shieldEffect == 0 && spend(113)) {
                shieldEffect = 6
                armor += 7
            }
        }

        fun poison(enemy: Player) {
            if (enemy.poisonEffect == 0 && spend(173)) {
                enemy.poisonEffect = 6
            }
        }

        fun recharge() {
            if (rechargeEffect == 0 && spend(229)) {
                rechargeEffect = 5
            }
        }

        fun runEffect() {
            if (shieldEffect > 0) {
                if (shieldEffect == 1) {
                    armor = 0
                }
                shieldEffect--
            }
            if (poisonEffect > 0) {
                hp -= 3
                poisonEffect--
            }
            if (rechargeEffect > 0) {
                mana += 101
                rechargeEffect--
            }
        }
    }

    class Game(val originalHero: Player, val originalBoss: Player) {
        var hero: Player
        var boss: Player

        val instructions = listOf("Recharge", "Poison", "Magic Missile", "Shield", "Drain")
        var lowestMana = Integer.MAX_VALUE

        init {
            this.hero = originalHero
            this.boss = originalBoss
        }

        fun bruteforce(baseInstructions: List<String>) {
            for (instruction in instructions) {
                this.hero = originalHero.copy()
                this.boss = originalBoss.copy()

                // try a new combination
                val newInstructions = ArrayList(baseInstructions)
                newInstructions.add(instruction)

                val result = this.fight(newInstructions)
                if (result == 1) {
                    if (hero.manaUsed < lowestMana) {
                        lowestMana = hero.manaUsed
                    }
                } else if (result == -1) {
                    if (hero.manaUsed <= lowestMana && newInstructions.size < 9) {
                        this.bruteforce(newInstructions)
                    }
                }
            }
        }

        fun fight(instructions: ArrayList<String>): Int {
            for (instruction in instructions) {
                hero.action(boss, instruction)

                if (hero.shieldEffect != 6 && instruction == "Shield") {
                    return 0 // hero loses
                }
                if (boss.poisonEffect != 6 && instruction == "Poison") {
                    return 0 // hero loses
                }
                if (hero.rechargeEffect != 5 && instruction == "Recharge") {
                    return 0 // hero loses
                }

                boss.action(hero, "Attack")

                if (hero.isDead) {
                    return 0 // hero loses
                }
                if (boss.isDead) {
                    return 1 // hero wins
                }
            }

            return -1
        }
    }

    val BOSS_REGEX = "Hit Points: (\\d+)\\s+Damage: (\\d+)".toRegex()
}