package com.lesica.cellular.visualization.swing

import com.lesica.cellular.population.Population
import com.lesica.cellular.spatial.BasicCell
import com.lesica.cellular.spatial.Cell
import com.lesica.cellular.spatial.Grid
import com.lesica.cellular.states.AgentState
import java.awt.Color
import java.awt.Graphics
import kotlin.math.floor

/**
 * A Swing visualization based on a grid. The grid itself will be drawn (or not drawn) automatically,
 * the consumer can implement cell drawing however they see fit.
 */
abstract class GridVisualization<TAgentState: AgentState>(val grid: Grid, population: Population<TAgentState>, tick: Int)
    : SwingVisualization<TAgentState>(population, tick) {

    var drawGrid = true

    override fun draw(graphics: Graphics) {
        val windowHeight = graphics.clipBounds.height
        val windowWidth = graphics.clipBounds.width

        val windowRatio = 1.0 * windowWidth / windowHeight
        val gridRatio = 1.0 * grid.width / grid.height

        val cellDim = floor(if (gridRatio > windowRatio) {
            1.0 * (windowWidth - 1) / grid.width
        } else {
            1.0 * (windowHeight - 1) / grid.height
        }).toInt()

        graphics.color = Color.BLUE

        for (col in 0 until grid.width) {
            for (row in 0 until grid.height) {
                val firstX = col * cellDim
                val firstY = row * cellDim
                val cellGraphics = graphics.create(firstX, firstY, cellDim, cellDim)

                drawCell(BasicCell(col, row), cellGraphics)

                if (drawGrid) {
                    cellGraphics.color = Color.LIGHT_GRAY

                    val cellWidth = if (col == grid.width - 1) {
                        cellDim - 1
                    } else {
                        cellDim
                    }
                    val cellHeight = if (row == grid.height - 1) {
                        cellDim - 1
                    } else {
                        cellDim
                    }

                    cellGraphics.drawRect(0, 0, cellWidth, cellHeight)
                }
            }
        }
    }

    abstract fun drawCell(cell: Cell, graphics: Graphics)
}