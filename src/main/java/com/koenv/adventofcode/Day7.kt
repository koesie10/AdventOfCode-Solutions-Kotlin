package com.koenv.adventofcode

class Day7 {
    val numberOfWires: Int
        get() = wires.size

    private val wires = hashMapOf<String, Int>()
    private val unsatisfiedDependencies = arrayListOf<Dependency>()

    public fun parseCommand(input: String) {
        val inputParts = input.trim().split(" -> ", ignoreCase = true)
        val command = inputParts[0]
        val into = inputParts[1]
        if (command.isDigits()) {
            wires.put(into, command.toInt())
            unsatisfiedDependencies.removeIf { it.into == into }
            satisfyDependencies(into)
        } else if (command.isLetters()) {
            val satisfiable = wires.containsKey(command)
            if (!satisfiable) {
                unsatisfiedDependencies.add(Dependency(arrayListOf(command), input, into))
            } else {
                wires.put(into, this[command])
                unsatisfiedDependencies.removeIf { it.into == into }
                satisfyDependencies(into)
            }
        } else {
            val commandParts = command.split(" ", ignoreCase = true)
            if (commandParts.size == 2 && commandParts[0] == "NOT") { // NOT
                val first = commandParts[1]
                val satisfiable = (first.isDigits() || wires.containsKey(first))
                if (!satisfiable) {
                    unsatisfiedDependencies.add(Dependency(arrayListOf(first), input, into))
                } else {
                    wires.put(into, getValue(first).toInt().inv().toUnsignedShort())
                    unsatisfiedDependencies.removeIf { it.into == into }
                    satisfyDependencies(into)
                }
            } else if (commandParts.size == 3) {
                val first = commandParts[0]
                val operator = commandParts[1]
                val second = commandParts[2]

                val satisfiable = (first.isDigits() || wires.containsKey(first)) && (second.isDigits() || wires.containsKey(second))
                if (!satisfiable) {
                    val dependsOn = arrayListOf<String>()
                    if (!first.isDigits()) {
                        dependsOn.add(first)
                    }
                    if (!second.isDigits()) {
                        dependsOn.add(second)
                    }
                    unsatisfiedDependencies.add(Dependency(dependsOn, input, into))
                } else {
                    val firstValue = if (first.isDigits()) first.toInt() else this[first]
                    val secondValue = if (second.isDigits()) second.toInt() else this[second]
                    val resultValue = executeOperation(operator, firstValue, secondValue)
                    wires.put(into, resultValue.toUnsignedShort())
                    unsatisfiedDependencies.removeIf { it.into == into }
                    satisfyDependencies(into)
                }
            } else {
                throw IllegalArgumentException("Invalid command: $input")
            }
        }
    }

    public fun parseCommands(input: String) {
        return input.lines().forEach {
            if (!it.isNullOrBlank()) {
                parseCommand(it)
            }
        }
    }

    public operator fun get(wireName: String): Int {
        return wires[wireName]!!
    }

    private fun satisfyDependencies(wireName: String) {
        unsatisfiedDependencies
                .filter { it.dependsOn.contains(wireName) }
                .forEach{
                    val dependsOn = it.dependsOn.filter { !wires.containsKey(it) }
                    if (dependsOn.isEmpty()) {
                        parseCommand(it.input)
                    }
                }
    }

    private fun getValue(wireNameOrValue: String): Int {
        if (wireNameOrValue.isDigits()) {
            return wireNameOrValue.toInt()
        }
        return wires[wireNameOrValue]!!
    }

    private fun executeOperation(operator: String, first: Int, second: Int): Int {
        when (operator) {
            "AND" -> return first and second
            "OR" -> return first or second
            "LSHIFT" -> return first shl second
            "RSHIFT" -> return first shr second
        }
        throw IllegalArgumentException("Invalid operator: $operator")
    }

    fun String.isDigits(): Boolean {
        return all { it.isDigit() }
    }

    fun String.isLetters(): Boolean {
        return all { it.isLetter() }
    }

    fun Int.toUnsignedShort(): Int {
        if (this < 0) {
            return 65536 + this
        }
        return this
    }

    data class Dependency(val dependsOn: MutableList<String>, val input: String, val into: String)
}