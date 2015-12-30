package com.koenv.adventofcode

object Day25 {
    class NumberGenerator(var n: Long) : AbstractIterator<Long>() {
        override fun computeNext() {
            n = (n * 252533) % 33554393
            setNext(n)
        }
    }

    public fun coordinateToPosition(row: Int, column: Int): Int {
        var add = 1
        var pos = 1
        for (i in 1..row - 1) {
            pos += add
            add += 1
        }

        add += 1

        for (i in 1..column - 1) {
            pos += add
            add += 1
        }

        return pos
    }

    public fun getValueAt(initialValue: Long, input: String): Long {
        val obj = NumberGenerator(initialValue)

        val result = INSTRUCTION_REGEX.find(input)
        val row = result[1].toInt()
        val column = result[2].toInt()

        return obj.nth(coordinateToPosition(row, column) - 2)!!
    }

    val INSTRUCTION_REGEX = ".* row (\\d+), column (\\d+).".toRegex()
}