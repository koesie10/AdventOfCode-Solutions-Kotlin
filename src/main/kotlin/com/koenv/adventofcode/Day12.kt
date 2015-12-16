package com.koenv.adventofcode

import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener

object Day12 {
    public fun getSum(input: String, skipRed: Boolean = false): Int {
        return getSum(JSONTokener(input).nextValue(), skipRed)
    }

    fun getSum(input: Any, skipRed: Boolean = false): Int {
        var sum = 0
        when (input) {
            is JSONObject -> {
                sum += getSum(input, skipRed)
            }
            is JSONArray -> {
                sum += getSum(input, skipRed)
            }
            is Int -> {
                sum += input
            }
        }
        return sum
    }

    fun getSum(input: JSONObject, skipRed: Boolean = false): Int {
        var sum = 0
        input.keys().forEach {
            val value = input.get(it)
            if (skipRed) {
                if (value is String && value == "red") {
                    return@getSum 0
                }
            }
            sum += getSum(value, skipRed)
        }
        return sum
    }

    fun getSum(input: JSONArray, skipRed: Boolean = false): Int {
        var sum = 0
        input.forEach {
            sum += getSum(it, skipRed)
        }
        return sum
    }
}