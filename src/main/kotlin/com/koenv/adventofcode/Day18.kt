package com.koenv.adventofcode

class Day18(val width: Int, val height: Int) {
    private val grid: Array<Array<Int>>

    public val numberOfLightsInOnState: Int
        get() = grid.sumBy {
            it.sum()
        }

    public val numberOfLightsInOffState: Int
        get() = width * height - numberOfLightsInOnState

    init {
        this.grid = Array(width, { Array(height, { STATE_OFF }) }) // Initialize an array of int[width][height] with all states set to 0
    }

    public fun setState(input: String) {
        input.lines().forEachIndexed { column, row ->
            row.forEachIndexed { i, state ->
                grid[column][i] = if (state == '#') STATE_ON else STATE_OFF
            }
        }
    }

    public fun turnCornersOn() {
        grid[0][0] = STATE_ON
        grid[width - 1][0] = STATE_ON
        grid[0][height -1] = STATE_ON
        grid[width - 1][height -1] = STATE_ON
    }

    public fun tick() {
        val oldGrid = grid.map { it.map { it } }

        for (x in 0..grid.size - 1) {
            for (y in 0..grid[x].size - 1) {
                val neighboursStates = getNumberOfNeighboursInOnState(oldGrid, x, y)
                if (oldGrid[x][y] == STATE_ON && neighboursStates != 2 && neighboursStates != 3) {
                    grid[x][y] = STATE_OFF
                } else if (oldGrid[x][y] == STATE_OFF && neighboursStates == 3) {
                    grid[x][y] = STATE_ON
                }
            }
        }
    }

    public val image: String
        get() {
            val builder = StringBuilder()
            grid.forEach {
                builder.appendln(it.joinToString(separator = "", transform = { if (it == 1) "#" else "." }))
            }

            return builder.toString()
        }

    private fun getNumberOfNeighboursInOnState(oldGrid: List<List<Int>>, x: Int, y: Int): Int {
        val neighbours = arrayOf(
                /*
                 * +--------+----------+--------+
                 * | -1, -1 |   0, -1  |  1, -1 |
                 * +--------+----------+--------+
                 * | -1, 0  | CURRENT! |  1, 0  |
                 * +--------+----------+--------+
                 * | -1, 1  |   0, 1   |  1, 1  |
                 * +--------+----------+--------+
                 */
                oldGrid.getOrNull(x - 1)?.getOrNull(y - 1),
                oldGrid.getOrNull(x)?.getOrNull(y - 1),
                oldGrid.getOrNull(x + 1)?.getOrNull(y - 1),

                oldGrid.getOrNull(x - 1)?.getOrNull(y),
                oldGrid.getOrNull(x + 1)?.getOrNull(y),

                oldGrid.getOrNull(x - 1)?.getOrNull(y + 1),
                oldGrid.getOrNull(x)?.getOrNull(y + 1),
                oldGrid.getOrNull(x + 1)?.getOrNull(y + 1)
        )

        return neighbours.filter { it == STATE_ON }.size
    }

    companion object {
        const val STATE_OFF = 0
        const val STATE_ON = 1
    }
}