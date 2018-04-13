package com.lesica.cellular.visualization.swing

import com.lesica.cellular.population.Population
import com.lesica.cellular.states.AgentState
import com.lesica.cellular.visualization.Visualization
import java.awt.Graphics
import javax.swing.JPanel

/**
 * A visualization that uses a Swing panel as its canvas.
 */
abstract class SwingVisualization<TAgentState : AgentState>(
        population: Population<TAgentState>,
        tick: Int) : JPanel(), Visualization<TAgentState> {

    var population: Population<TAgentState> = population
        private set

    var tick: Int = tick
        private set

    abstract fun draw(graphics: Graphics)

    override fun paintComponent(graphics: Graphics) {
        super.paintComponent(graphics)
        draw(graphics)
    }

    override fun update(tick: Int, population: Population<TAgentState>) {
        this.population = population
        this.tick = tick
        repaint()
    }
}