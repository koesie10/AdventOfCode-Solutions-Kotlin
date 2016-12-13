package com.koenv.adventofcode

class Day12 {
    val registers = Registers()

    val possibleInstructions = hashMapOf<String, (Array<String>) -> Unit>()

    var position = 0
    var skipNextIncrement = false

    val instructions = arrayListOf<Pair<(Array<String>) -> Unit, Array<String>>>()

    init {
        possibleInstructions["cpy"] = {
            registers[it[1]] = registers[it[0]]
        }

        possibleInstructions["inc"] = {
            registers[it[0]] = registers[it[0]] + 1
        }

        possibleInstructions["dec"] = {
            registers[it[0]] = registers[it[0]] - 1
        }

        possibleInstructions["jnz"] = {
            if (registers[it[0]] != 0) {
                position += registers[it[1]]
                skipNextIncrement = true
            }
        }
    }

    fun parse(input: String) {
        instructions.addAll(input.trim().lines().map {
            val line = it.split(' ', limit = 2)
            val instruction = line[0]
            val args = line[1].split(' ').map(String::trim).toTypedArray()
            possibleInstructions[instruction]!! to args
        })

        while (position < instructions.size) {
            val instruction = instructions[position]
            instruction.first(instruction.second)
            if (!skipNextIncrement) {
                position++
            } else {
                skipNextIncrement = false
            }
        }
    }

    data class Registers(val registers: IntArray = IntArray(4, { 0})) {
        operator fun get(from: String): Int {
            if (from.length == 1 && from[0].isLetter()) {
                return registers[from[0] - 'a']
            }

            return from.toInt()
        }

        operator fun set(to: String, value: Int) {
            registers[to[0] - 'a'] = value
        }
    }
}