package com.koenv.adventofcode

class Day23(val input: String) {
    val registers = IntArray(2, { 0 })

    val instructions = hashMapOf<String, (Array<String>) -> Unit>()

    var position = 0
    var skipNextIncrement = false

    init {
        instructions["hlf"] = {
            registers.apply(it[0][0], { it / 2 })
        }

        instructions["tpl"] = {
            registers.apply(it[0][0], { it * 3 })
        }

        instructions["inc"] = {
            registers.apply(it[0][0], { it + 1 })
        }

        instructions["jmp"] = {
            position += it[0].toInt()
            skipNextIncrement = true
        }

        instructions["jie"] = {
            if (registers[it[0][0]] % 2 == 0) {
                position += it[1].toInt()
                skipNextIncrement = true
            }
        }

        instructions["jio"] = {
            if (registers[it[0][0]] == 1) {
                position += it[1].toInt()
                skipNextIncrement = true
            }
        }
    }

    fun parse() {
        val lines = input.lines()
        while (position < lines.size) {
            val line = lines[position].split(' ', limit = 2)
            val instruction = instructions[line[0]] ?: throw IllegalStateException("Invalid instruction: ${line[0]}")
            val args = line[1].split(',').map { it.trim() }.toTypedArray()
            instruction(args)
            if (!skipNextIncrement) {
                position++
            } else {
                skipNextIncrement = false
            }
        }
    }

    operator fun IntArray.get(r: Char): Int {
        return this[(r - 'a').toInt()]
    }

    fun IntArray.apply(r: Char, operation: (Int) -> Int) {
        val index = (r - 'a').toInt()
        val old = this[index]
        val new = operation(old)
        this[index] = new
    }
}