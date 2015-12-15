package com.koenv.adventofcode

import java.util.*

object Day15 {
    fun getHighestScore(input: String): Int {
        val (ingredients, combinations) = getCombinations(input)
        return combinations.map { ingredients.sumAll(it) }.max()!!
    }

    fun getBestCalories(input: String): Int {
        val (ingredients, combinations) = getCombinations(input)
        return combinations.filter { ingredients.sum(it, { it.calories }) == 500 }.map { ingredients.sumAll(it) }.max()!!
    }

    private fun getCombinations(input: String): Pair<List<Ingredient>, List<List<Int>>> {
        val ingredients = input.lines().map {
            val result = INGREDIENT_REGEX.find(it)!!
            val name = result.groups[1]!!.value
            val capacity = result.groups[2]!!.value.toInt()
            val durability = result.groups[3]!!.value.toInt()
            val flavor = result.groups[4]!!.value.toInt()
            val texture = result.groups[5]!!.value.toInt()
            val calories = result.groups[6]!!.value.toInt()
            Ingredient(name, capacity, durability, flavor, texture, calories)
        }

        val combinations = arrayListOf<List<Int>>()
        val counts = ingredients.map { 0 }

        combinations(100, 0, combinations, counts)

        return ingredients to combinations
    }

    data class Ingredient(
            val name: String,
            val capacity: Int,
            val durability: Int,
            val flavor: Int,
            val texture: Int,
            val calories: Int
    )

    fun List<Ingredient>.sumAll(amounts: List<Int>): Int {
        return sum(amounts, { it.capacity }) * sum(amounts, { it.durability }) * sum(amounts, { it.flavor }) * sum(amounts, { it.texture })
    }

    fun List<Ingredient>.sum(amounts: List<Int>, property: (Ingredient) -> Int): Int {
        var sum = 0
        forEachIndexed { i, ingredient ->
            sum += property(ingredient) * amounts[i]
        }

        return Math.max(sum, 0)
    }

    fun combinations(target: Int, index: Int, results: MutableList<List<Int>>, list: List<Int>) {
        for (i in 0..target) {
            val newList = ArrayList(list)
            newList[index] = i

            if (index < list.size - 1 && newList.subList(0, index).sum() <= target) {
                combinations(target, index + 1, results, newList)
            }

            if (index == list.size - 1 && newList.sum() == target) {
                results.add(newList)
            }
        }
    }

    val INGREDIENT_REGEX = "(.*): capacity (-?\\d+), durability (-?\\d+), flavor (-?\\d+), texture (-?\\d+), calories (-?\\d+)".toRegex()
}