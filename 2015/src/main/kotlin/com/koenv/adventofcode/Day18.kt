package com.koenv.adventofcode

import javafx.animation.AnimationTimer
import javafx.application.Application
import javafx.event.EventHandler
import javafx.geometry.VPos
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.stage.Stage
import java.io.FileReader
import java.util.*

class Day18(val width: Int, val height: Int) {
    private val grid: Array<Array<Int>>

    val numberOfLightsInOnState: Int
        get() = grid.sumBy {
            it.sum()
        }

    val numberOfLightsInOffState: Int
        get() = width * height - numberOfLightsInOnState

    init {
        this.grid = Array(width, { Array(height, { STATE_OFF }) }) // Initialize an array of int[width][height] with all states set to 0
    }

    fun setState(input: String) {
        input.lines().forEachIndexed { y, row ->
            row.forEachIndexed { x, state ->
                grid[x][y] = if (state == '#') STATE_ON else STATE_OFF
            }
        }
    }

    fun turnCornersOn() {
        grid[0][0] = STATE_ON
        grid[width - 1][0] = STATE_ON
        grid[0][height - 1] = STATE_ON
        grid[width - 1][height - 1] = STATE_ON
    }

    fun tick() {
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

    fun toggle(x: Int, y: Int) {
        grid[x][y] = if (grid[x][y] == STATE_ON) STATE_OFF else STATE_ON
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

        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(ViewerApplication::class.java)
        }
    }

    class ViewerApplication : Application() {
        private val control = Day18(100, 100)

        private var currentTime: Long = 0L
        private var currentTick: Long = 0L
        private var speed: Long = 1L
        private var running = false

        override fun start(primaryStage: Stage) {
            primaryStage.title = "Advent of Code - Day 18"
            primaryStage.isResizable = false

            val root = StackPane()
            val canvas = Canvas(800.0, 900.0)
            val context = canvas.graphicsContext2D

            canvas.isFocusTraversable = true
            canvas.requestFocus()

            context.textBaseline = VPos.TOP
            context.font = Font.font(20.0)

            root.children.add(canvas)

            primaryStage.scene = Scene(root)
            primaryStage.show()

            initGrid()
            drawGrid(context, System.nanoTime())

            val loop = object : AnimationTimer() {
                override fun handle(now: Long) {
                    if (running && ++currentTime % speed == 0L) {
                        control.tick()
                        currentTick++
                    }
                    drawGrid(context, now)
                }
            }

            val mouseHandler = EventHandler<MouseEvent> {
                val y = (it.sceneX / canvas.width * control.width).toInt()
                val x = (it.sceneY / canvas.height * control.height).toInt()
                if (x < 0 || x >= control.width || y < 0 || y >= control.height) {
                    return@EventHandler
                }
                control.toggle(x, y)
            }

            canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseHandler)

            canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, mouseHandler)

            canvas.addEventHandler(KeyEvent.KEY_TYPED, {
                when (it.character) {
                    "c" -> {
                        control.grid.forEachIndexed { y, row ->
                            row.forEachIndexed { x, cell ->
                                control.grid[y][x] = if (cell == STATE_ON) STATE_OFF else STATE_ON
                            }
                        }
                    }
                    "r" -> {
                        val random = Random()
                        control.grid.forEachIndexed { y, row ->
                            row.forEachIndexed { x, cell ->
                                control.grid[y][x] = if (random.nextBoolean()) STATE_OFF else STATE_ON
                            }
                        }
                        currentTick = 0
                    }
                    "p" -> {
                        running = !running
                    }
                    "q" -> {
                        loop.stop()
                        primaryStage.close()
                    }
                    "=", "+", "." -> {
                        speed--
                        if (speed <= 0) {
                            speed = 1
                        }
                    }
                    "-", "," -> {
                        speed++
                    }
                }
            })

            loop.start()
        }

        fun initGrid() {
            control.setState(FileReader("2015/src/test/resources/day18.txt").buffered().readText())
        }

        private var prev: Long = 0L
        private var frameCount: Double = 0.0
        private var fps: Double = 0.0

        fun drawGrid(context: GraphicsContext, now: Long) {
            // a second
            if (now - prev > 1000000000) {
                fps = frameCount

                prev = now

                frameCount = 0.0
            } else {
                frameCount++
            }

            val gridSizeX = context.canvas.width / control.width
            val gridSizeY = context.canvas.height / control.height

            context.fill = Color.BLACK
            context.fillRect(0.0, 0.0, context.canvas.width, context.canvas.height)
            context.fill = Color.WHITE

            control.grid.forEachIndexed { x, row ->
                row.forEachIndexed { y, cell ->
                    if (cell == STATE_ON) {
                        context.fillRect(x * gridSizeX, y * gridSizeY + 100.0, gridSizeX, gridSizeY)
                    }
                }
            }

            val text = "FPS: $fps, Tick: $currentTick, Speed: ${1.0f / speed}"

            context.fill = Color.WHITE
            context.fillText(text, 0.0, 0.0)

            if (!running) {
                context.fill = Color.RED
                context.fillText("PAUSED", 0.0, 30.0)
            }
        }
    }
}