package com.koenv.adventofcode

import java.io.PrintStream
import java.util.regex.Pattern

object Day8 {
    val defaultScreenWidth = 50
    val defaultScreenHeight = 6

    fun getFinalPixels(input: String) = getFinalPixels(input, defaultScreenWidth, defaultScreenHeight)

    // array[x][y]
    fun getFinalPixels(input: String, screenWidth: Int, screenHeight: Int): Array<Array<Boolean>> {
        val screen = Array<Array<Boolean>>(screenWidth, {
            return@Array Array<Boolean>(screenHeight, { false })
        })

        input.lines()
                .filter(String::isNotBlank)
                .forEach {
                    val rectMatcher = rectPattern.matcher(it)

                    if (rectMatcher.find()) {
                        val a = rectMatcher.group(1).toInt()
                        val b = rectMatcher.group(2).toInt()

                        for (x in 0..(a - 1)) {
                            for (y in 0..(b - 1)) {
                                screen[x][y] = true
                            }
                        }
                    } else {
                        val rotateMatcher = rotatePattern.matcher(it)
                        if (rotateMatcher.find()) {
                            when (rotateMatcher.group(1)) {
                                "row y" -> {
                                    val y = rotateMatcher.group(2).toInt()
                                    val offset = rotateMatcher.group(3).toInt()
                                    val calc = OffsetCalculator(screen.map { it[y] })
                                    for (x in 0..(screenWidth - 1)) {
                                        screen[x][y] = calc.getPixel(x, offset)
                                    }
                                }
                                "column x" -> {
                                    val x = rotateMatcher.group(2).toInt()
                                    val offset = rotateMatcher.group(3).toInt()
                                    val calc = OffsetCalculator(screen[x])
                                    for (y in 0..(screenHeight - 1)) {
                                        screen[x][y] = calc.getPixel(y, offset)
                                    }
                                }
                            }
                        } else {
                            throw IllegalArgumentException("Invalid instruction: `$it`")
                        }
                    }
                }

        return screen
    }

    fun getFinalPixelCount(input: String) = getFinalPixelCount(getFinalPixels(input))

    fun getFinalPixelCount(input: Array<Array<Boolean>>) = input.map {
        it.filter { it }.size
    }.sum()

    fun printPixels(input: Array<Array<Boolean>>, output: PrintStream) {
        val screenWidth = input.size
        val screenHeight = input[0].size

        for (y in 0..(screenHeight - 1)) {
            for (x in 0..(screenWidth -1 )) {
                output.print(if (input[x][y]) '#' else '.')
            }
            output.println()
        }
    }

    class OffsetCalculator(val pixels: List<Boolean>) {
        constructor(array: Array<Boolean>) : this(array.toList())

        fun getPixel(i: Int, offset: Int): Boolean {
            var new = i - offset
            if (new < 0) {
                new += pixels.size
            } else if (new >= pixels.size) {
                new -= pixels.size
            }
            return pixels[new]
        }
    }

    val rectPattern: Pattern = Pattern.compile("rect (\\d+)x(\\d+)")
    val rotatePattern: Pattern = Pattern.compile("rotate (row y|column x)=(\\d+) by (\\d+)")
}