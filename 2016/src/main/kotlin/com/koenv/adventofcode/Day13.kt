package com.koenv.adventofcode

import java.util.*

object Day13 {
    fun getDistance(input: String, destination: Pair<Int, Int>): Int {
        val favoriteNumber = input.toInt()

        var currentState = State(null, 1 to 1)

        val moves: Queue<State> = ArrayDeque<State>()

        moves.add(currentState)

        val seen = hashSetOf<Pair<Int, Int>>()

        while (moves.isNotEmpty()) {
            currentState = moves.poll()

            if (isFinalMove(currentState, destination)) {
                return currentState.depth
            } else {
                // add all states to possible moves
                getAdjacentStates(currentState, favoriteNumber).forEach {
                    if (!seen.contains(it.position)) {
                        moves.offer(it)
                        seen.add(it.position)
                    }
                }
            }
        }

        throw IllegalStateException("No final move found")
    }

    fun findUniqueLocations(input: String, maxSteps: Int): Int {
        val favoriteNumber = input.toInt()

        var currentState = State(null, 1 to 1)

        val moves: Queue<State> = ArrayDeque<State>()

        moves.add(currentState)

        val seen = hashSetOf<Pair<Int, Int>>()

        while (moves.isNotEmpty()) {
            currentState = moves.poll()

            getAdjacentStates(currentState, favoriteNumber).forEach {
                if (!seen.contains(it.position) && it.depth <= maxSteps) {
                    moves.offer(it)
                    seen.add(it.position)
                }
            }
        }

        return seen.size
    }

    private fun getAdjacentStates(currentState: State, favoriteNumber: Int): List<State> {
        val possibleMoves = arrayListOf<State>()

        possibleMoves.add(State(currentState, currentState.position.first + 1 to currentState.position.second))
        possibleMoves.add(State(currentState, currentState.position.first - 1 to currentState.position.second))
        possibleMoves.add(State(currentState, currentState.position.first to currentState.position.second + 1))
        possibleMoves.add(State(currentState, currentState.position.first to currentState.position.second - 1))

        return possibleMoves.filter { isValid(it, favoriteNumber) }
    }

    fun isFinalMove(state: State, destination: Pair<Int, Int>): Boolean {
        return state.position == destination
    }

    fun isValid(state: State, favoriteNumber: Int): Boolean {
        val x = state.position.first
        val y = state.position.second

        if (x < 0 || y < 0) {
            return false
        }

        val result = x * x + 3 * x + 2 * x * y + y + y * y + favoriteNumber

        return Integer.bitCount(result).mod(2) == 0
    }

    fun printMap() {
        print("  ")
        for (x in 0..9) {
            print(x)
        }

        println()

        for (y in 0..9) {
            print(y)
            print(" ")
            for (x in 0..9) {
                if (isValid(State(null, x to y), 10)) {
                    print('.')
                } else {
                    print('#')
                }
            }
            println()
        }
    }

    data class State(
            val parent: State?,
            val position: Pair<Int, Int>
    ) {
        val depth: Int
            get() {
                var currentParent = parent
                var i = 0
                while (currentParent != null) {
                    i++
                    currentParent = currentParent.parent
                }
                return i
            }
    }
}