package com.koenv.adventofcode

import java.util.*
import java.util.regex.Pattern

/**
 * This one is quite slow, probably due to a lot of allocations in getAdjacentStates, which allocates
 * a new list for every possible state
 */
object Day11 {
    fun getRequiredSteps(input: String): Int {
        val types = hashSetOf<String>()

        val floors = input.lines()
                .filter(String::isNotBlank)
                .mapIndexed { i, line ->
                    val lineMatcher = linePattern.matcher(line)

                    if (!lineMatcher.find()) {
                        throw IllegalArgumentException("Invalid input: `$line`")
                    }

                    if (lineMatcher.group(2) == "nothing relevant") {
                        return@mapIndexed listOf<FloorItem>()
                    }

                    lineMatcher.group(2).split(",").map {
                        val matcher = itemPattern.matcher(it.trim())

                        if (!matcher.find()) {
                            throw IllegalArgumentException("Invalid item: `$it`")
                        }

                        val type = matcher.group(2)

                        types.add(type)

                        FloorItem(i, matcher.group(4), type)
                    }
                }
                .flatten()

        val pairs = arrayListOf<Pair<Int, Int>>()

        floors.map { item ->
            // find the beloning microchip
            if (item.device == "generator") {
                val microchip = floors.find {
                    it.element == item.element && it.device == "microchip"
                }!!

                pairs.add(item.floor to microchip.floor)
            }
        }

        val startState = State(null, 0, pairs)

        return findRequiredStepsBfs(startState)
    }

    fun findRequiredStepsBfs(startState: State): Int {
        val seenStates = hashSetOf<Pair<Int, List<Pair<Int, Int>>>>()

        seenStates.add(startState.elevator to startState.pairs)

        var currentState = startState

        val moves: Queue<State> = LinkedList<State>()

        moves.add(currentState)

        while (moves.isNotEmpty()) {
            currentState = moves.poll()

            if (isFinalMove(currentState)) {
                return currentState.depth
            } else {
                // add all states to possible moves
                getAdjacentStates(currentState, seenStates).forEach {
                    moves.offer(it)
                    seenStates.add(it.elevator to it.pairs)
                }
            }
        }

        throw IllegalStateException("No final move found")
    }

    private fun printTree(state: State) {
        state.parent?.let { printTree(it) }

        state.print()
    }

    private fun getAdjacentStates(currentState: State, seenStates: HashSet<Pair<Int, List<Pair<Int, Int>>>>): List<State> {
        val possibleMoves = arrayListOf<State>()

        currentState.pairs.forEach { pair ->
            val newPairs = currentState.pairs.removeOne(pair)

            // this is quite ugly, TODO: Clean it up
            if (pair.first == currentState.elevator) {
                possibleMoves.add(State(currentState, currentState.elevator + 1, newPairs + (pair.first + 1 to pair.second), "1"))
                possibleMoves.add(State(currentState, currentState.elevator - 1, newPairs + (pair.first - 1 to pair.second), "2"))

                newPairs.forEach { other ->
                    if (other.first == currentState.elevator) {
                        val newestPairs = newPairs.removeOne(other)
                        possibleMoves.add(State(currentState, currentState.elevator + 1, newestPairs + (pair.first + 1 to pair.second) + (other.first + 1 to other.second), "3"))
                        possibleMoves.add(State(currentState, currentState.elevator - 1, newestPairs + (pair.first - 1 to pair.second) + (other.first - 1 to other.second), "4"))
                    }

                    if (other.second == currentState.elevator) {
                        val newestPairs = newPairs.removeOne(other)
                        possibleMoves.add(State(currentState, currentState.elevator + 1, newestPairs + (pair.first + 1 to pair.second) + (other.first to other.second + 1), "5"))
                        possibleMoves.add(State(currentState, currentState.elevator - 1, newestPairs + (pair.first - 1 to pair.second) + (other.first to other.second - 1), "6"))
                    }
                }

                if (pair.second == currentState.elevator) {
                    possibleMoves.add(State(currentState, currentState.elevator + 1, newPairs + (pair.first + 1 to pair.second + 1), "7"))
                    possibleMoves.add(State(currentState, currentState.elevator - 1, newPairs + (pair.first - 1 to pair.second - 1), "8"))
                }
            }
            if (pair.second == currentState.elevator) {
                possibleMoves.add(State(currentState, currentState.elevator + 1, newPairs + (pair.first to pair.second + 1), "8"))
                possibleMoves.add(State(currentState, currentState.elevator - 1, newPairs + (pair.first to pair.second - 1), "9"))

                newPairs.forEach { other ->
                    if (other.first == currentState.elevator) {
                        val newestPairs = newPairs.removeOne(other)
                        possibleMoves.add(State(currentState, currentState.elevator + 1, newestPairs + (pair.first to pair.second + 1) + (other.first + 1 to other.second), "10"))
                        possibleMoves.add(State(currentState, currentState.elevator - 1, newestPairs + (pair.first to pair.second - 1) + (other.first - 1 to other.second), "11"))
                    }

                    if (other.second == currentState.elevator) {
                        val newestPairs = newPairs.removeOne(other)
                        possibleMoves.add(State(currentState, currentState.elevator + 1, newestPairs + (pair.first to pair.second + 1) + (other.first to other.second + 1), "12"))
                        possibleMoves.add(State(currentState, currentState.elevator - 1, newestPairs + (pair.first to pair.second - 1) + (other.first to other.second - 1), "13"))
                    }
                }
            }
        }

        return possibleMoves.filter { isValid(it) }.filter { !seenStates.contains(it.elevator to it.pairs) }
    }

    fun isFinalMove(state: State): Boolean {
        return state.pairs.all { it.first == 3 && it.second == 3 } // fourth floor
    }

    fun isValid(state: State): Boolean {
        if (state.elevator < 0 || state.elevator > 3) {
            return false
        }

        val generators = state.pairs.mapIndexed { i, pair -> pair.first }.toSet()

        return state.pairs.all {
            it.first >= 0 && it.first <= 3 && it.second >= 0 && it.second <= 3 &&
                    (it.first == it.second || !generators.contains(it.second))
        }
    }

    val linePattern: Pattern = Pattern.compile("The ([a-z]*) floor contains (.*).")
    val itemPattern: Pattern = Pattern.compile("(and )?a ([a-z]*)(-compatible)? ([a-z]*)")

    data class State(
            val parent: State?,
            val elevator: Int,
            /**
             * The first item in the pair is the generator, the second is the microchip
             */
            val pairs: List<Pair<Int, Int>>,
            var payload: String? = null
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

        fun print() {
            println("Depth = $depth, Valid = ${isValid(this)}, Final = ${isFinalMove(this)}, Payload = $payload")

            println("=".repeat(7 + pairs.size * 6))

            for (i in 0..3) {
                val floor = 3 - i
                print("F$floor = ")
                if (elevator == floor) {
                    print("E  ")
                } else {
                    print(".  ")
                }

                pairs.forEachIndexed { pairIndex, pair ->
                    if (pair.first == floor) {
                        print((pairIndex + 'A'.toInt()).toChar())
                        print("G ")
                    } else {
                        print(".  ")
                    }

                    if (pair.second == floor) {
                        print((pairIndex + 'A'.toInt()).toChar())
                        print("M ")
                    } else {
                        print(".  ")
                    }
                }
                println()
            }

            println()
        }
    }

    data class FloorItem(
            val floor: Int,
            val device: String,
            val element: String
    )

    fun <T> Iterable<T>.removeOne(element: T): List<T> {
        var found = false
        return filter {
            if (it == element) {
                if (found) {
                    return@filter true
                }
                found = true
                return@filter false
            }

            true
        }
    }
}