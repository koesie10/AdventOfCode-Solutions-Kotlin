package com.koenv.adventofcode

import java.util.regex.Pattern

object Day10 {
    fun execute(input: String, compare1: Int, compare2: Int): Int {
        var finalBot: Int = 0
        execute(input, { bot, low, high ->
            if ((compare1 == low && compare2 == high) || (compare1 == high && compare2 == low)) {
                finalBot = bot
            }
        })
        return finalBot
    }

    fun part2(input: String): Int {
        val inventories = execute(input, { bot, low, high -> })

        return inventories.outputs[0]!! * inventories.outputs[1]!! * inventories.outputs[2]!!
    }

    fun execute(input: String, until: (bot: Int, low: Int, high: Int) -> Unit): Inventories {
        val inventories = Inventories()

        val instructions = input.lines()
                .filter(String::isNotBlank)
                .map {
                    return@map if (it.startsWith("value")) {
                        Instruction.ValueInstruction.create(it)
                    } else if (it.startsWith("bot")) {
                        Instruction.GiveInstruction.create(it)
                    } else {
                        throw IllegalArgumentException("Invalid instruction: $it")
                    }
                }.toMutableList()

        val removable = mutableListOf<Instruction>()

        var lastCount = -1

        while (!instructions.isEmpty()) {
            instructions.forEach {
                if (it.isExecutable(inventories)) {
                    it.execute(inventories, until)
                    removable.add(it)
                }
            }

            instructions.removeAll(removable)

            if (lastCount == instructions.size) {
                throw IllegalStateException("The instructions cannot be diminished any more, stuck at $lastCount")
            }

            lastCount = instructions.size
        }

        return inventories
    }

    val valuePattern: Pattern = Pattern.compile("value (\\d+) goes to bot (\\d+)")
    val givePattern: Pattern = Pattern.compile("bot (\\d+) gives low to (bot|output) (\\d+) and high to (bot|output) (\\d+)")

    sealed class Instruction {
        class ValueInstruction(val bot: Int, val value: Int) : Instruction() {
            override fun execute(inventories: Inventories, until: (Int, Int, Int) -> Unit) {
                val inventory = inventories[bot]
                inventory.put(value)
            }

            override fun isExecutable(inventories: Inventories) = true

            companion object {
                fun create(input: String): ValueInstruction {
                    val matcher = valuePattern.matcher(input)

                    if (!matcher.find()) {
                        throw IllegalArgumentException("Invalid value instruction `$input`")
                    }

                    return ValueInstruction(matcher.group(2).toInt(), matcher.group(1).toInt())
                }
            }
        }

        class GiveInstruction(
                val sender: Int, val receiverLow: Int, val receiverLowOutput: Boolean,
                val receiverHigh: Int, val receiverHighOutput: Boolean
        ) : Instruction() {
            override fun execute(inventories: Inventories, until: (Int, Int, Int) -> Unit) {
                val senderBot = inventories[sender]

                if (receiverLowOutput) {
                    inventories[receiverLow] = senderBot.low!!
                } else {
                    val receiverLowBot = inventories[receiverLow]
                    receiverLowBot.put(senderBot.low!!)
                }

                if (receiverHighOutput) {
                    inventories[receiverHigh] = senderBot.high!!
                } else {
                    val receiverHighBot = inventories[receiverHigh]
                    receiverHighBot.put(senderBot.high!!)
                }

                until(sender, senderBot.low!!, senderBot.high!!)

                senderBot.clear()
            }

            override fun isExecutable(inventories: Inventories): Boolean {
                return inventories[sender].isComplete
            }

            companion object {
                fun create(input: String): GiveInstruction {
                    val matcher = givePattern.matcher(input)

                    if (!matcher.find()) {
                        throw IllegalArgumentException("Invalid give instruction `$input`")
                    }

                    val sender = matcher.group(1).toInt()
                    val receiverLow = matcher.group(3).toInt()
                    val receiverLowOutput = matcher.group(2) == "output"
                    val receiverHigh = matcher.group(5).toInt()
                    val receiverHighOutput = matcher.group(4) == "output"

                    return GiveInstruction(sender, receiverLow, receiverLowOutput, receiverHigh, receiverHighOutput)
                }
            }
        }

        abstract fun execute(inventories: Inventories, until: (Int, Int, Int) -> Unit)

        abstract fun isExecutable(inventories: Inventories): Boolean
    }

    class Inventories {
        val bots = hashMapOf<Int, BotInventory>()
        val outputs = hashMapOf<Int, Int>()

        operator fun get(bot: Int): BotInventory {
            var inventory = bots[bot]
            if (inventory == null) {
                inventory = BotInventory(null, null)
                bots[bot] = inventory
            }
            return inventory
        }

        operator fun set(output: Int, value: Int) {
            outputs[output] = value
        }
    }

    data class BotInventory(var low: Int?, var high: Int?) {
        val isComplete: Boolean
            get() = low != null && high != null

        fun put(value: Int) {
            if (low == null) {
                low = value
            } else if (high == null) {
                if (value < low!!) {
                    high = low
                    low = value
                } else {
                    high = value
                }
            } else {
                throw IllegalStateException("Low and high have both been set")
            }
        }

        fun clear() {
            low = null
            high = null
        }
    }
}