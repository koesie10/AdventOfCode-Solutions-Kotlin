package com.koenv.adventofcode

object Day2 {
    public fun getSquareFeetOfWrappingPaper(input: String): Int {
        val (l, w, h) = getDimensions(input)
        val areas = arrayOf(l * w, w * h, h * l)
        val minArea = areas.min()!!
        return areas.map { it * 2 }.sum() + minArea
    }

    public fun getTotalSquareFeetOfWrappingPaper(input: String): Int {
        return input.lines().map {
            getSquareFeetOfWrappingPaper(it)
        }.sum()
    }

    public fun getSquareFeetOfRibbon(input: String): Int {
        val (l, w, h) = getDimensions(input)
        val dimensions = listOf(l, w, h)
        val shortest = dimensions.min()!!
        val noShortestDimensions = dimensions.minus(shortest)
        val minShortest = noShortestDimensions.min()!!
        return shortest * 2 + minShortest * 2 + l * w * h
    }

    public fun getTotalSquareFeetOfRibbon(input: String): Int {
        return input.lines().map {
            getSquareFeetOfRibbon(it)
        }.sum()
    }

    private fun getDimensions(input: String): Day2Dimensions {
        val dimensions = input.split('x')
        val l = dimensions[0].toInt()
        val w = dimensions[1].toInt()
        val h = dimensions[2].toInt()
        return Day2Dimensions(l, w, h)
    }

    data class Day2Dimensions(val l: Int, val w: Int, val h: Int)
}