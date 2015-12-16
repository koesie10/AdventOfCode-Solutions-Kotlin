package com.koenv.adventofcode

class Day6Part1(val width: Int, val height: Int) {
    val grid: Array<Array<Int>>

    val numberOfLightsInOnState: Int
        get() = grid.sumBy {
            it.sum()
        }

    val numberOfLightsInOffState: Int
        get() = width * height - numberOfLightsInOnState

    init {
        this.grid = Array(width, { Array(height, { STATE_OFF }) }) // Initialize an array of int[width][height] with all states set to 0
    }

    fun setStateForAll(newState: Int) {
        grid.forEachIndexed { x, row ->
            row.forEachIndexed { y, column ->
                grid[x][y]= newState
            }
        }
    }

    fun parseCommand(input: String) {
        val matchResult = COMMAND_REGEX.find(input) ?: throw IllegalArgumentException("Not a valid command: $input")

        val command = matchResult.groups[1]!!.value
        val startX = matchResult.groups[2]!!.value.toInt()
        val startY = matchResult.groups[3]!!.value.toInt()
        val endX = matchResult.groups[4]!!.value.toInt()
        val endY = matchResult.groups[5]!!.value.toInt()

        val operator = getOperatorForCommand(command)

        for (x in startX..endX) {
            for (y in startY..endY) {
                grid[x][y] = operator(grid[x][y])
            }
        }
    }

    fun parseCommands(input: String) {
        return input.lines().forEach {
            parseCommand(it)
        }
    }

    private fun getOperatorForCommand(command: String): (Int) -> Int {
        when (command) {
            "turn on" -> return {
                STATE_ON
            }
            "turn off" -> return {
                STATE_OFF
            }
            "toggle" -> return {
                if (it == STATE_OFF) STATE_ON else STATE_OFF
            }
        }
        throw IllegalArgumentException("Invalid command: $command")
    }

    companion object {
        const val STATE_OFF = 0
        const val STATE_ON = 1

        val COMMAND_REGEX = "(turn on|toggle|turn off)\\s(\\d+),(\\d+)\\sthrough\\s(\\d+),(\\d+)".toRegex()
    }
}

class Day6Part2(val width: Int, val height: Int) {
    val grid: Array<Array<Int>>

    val totalBrightness: Int
        get() = grid.sumBy {
            it.sum()
        }

    init {
        this.grid = Array(width, { Array(height, { DEFAULT_BRIGHTNESS }) }) // Initialize an array of int[width][height] with all states set to 0
    }

    fun setStateForAll(newState: Int) {
        grid.forEachIndexed { x, row ->
            row.forEachIndexed { y, column ->
                grid[x][y]= newState
            }
        }
    }

    fun parseCommand(input: String) {
        val matchResult = COMMAND_REGEX.find(input) ?: throw IllegalArgumentException("Not a valid command: $input")

        val command = matchResult.groups[1]!!.value
        val startX = matchResult.groups[2]!!.value.toInt()
        val startY = matchResult.groups[3]!!.value.toInt()
        val endX = matchResult.groups[4]!!.value.toInt()
        val endY = matchResult.groups[5]!!.value.toInt()

        val operator = getOperatorForCommand(command)

        for (x in startX..endX) {
            for (y in startY..endY) {
                grid[x][y] = operator(grid[x][y])
            }
        }
    }

    fun parseCommands(input: String) {
        return input.lines().forEach {
            parseCommand(it)
        }
    }

    private fun getOperatorForCommand(command: String): (Int) -> Int {
        when (command) {
            "turn on" -> return {
                it + 1
            }
            "turn off" -> return {
                if (it > 0) it - 1 else it
            }
            "toggle" -> return {
                it + 2
            }
        }
        throw IllegalArgumentException("Invalid command: $command")
    }

    companion object {
        const val DEFAULT_BRIGHTNESS = 0

        val COMMAND_REGEX = "(turn on|toggle|turn off)\\s(\\d+),(\\d+)\\sthrough\\s(\\d+),(\\d+)".toRegex()
    }
}