package com.lesica.cellular.examples.conway

import com.lesica.cellular.configuration.Frequency
import com.lesica.cellular.configuration.Grouping
import com.lesica.cellular.configuration.StaticConfiguration
import com.lesica.cellular.delegates.InertCellDelegate
import com.lesica.cellular.population.PopulationGenerator
import com.lesica.cellular.printer.GridPrinter
import com.lesica.cellular.simulation.Simulation
import com.lesica.cellular.simulation.SimulationImpl
import com.lesica.cellular.spatial.BasicGrid
import com.lesica.cellular.spatial.Cell
import com.lesica.cellular.spatial.basicCellFactory
import com.lesica.cellular.strategies.BasicMovementStrategy
import com.lesica.cellular.strategies.BasicSpatialStrategy
import com.lesica.cellular.strategies.BasicVisibilityStrategy
import com.lesica.cellular.visualization.swing.GridVisualization
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.util.*
import javax.swing.JFrame

private val random = Random();

public class ConwayRunner {
    var gridSize = 5

    private var simulation: Simulation<ConwayAgentState>

    init {
        val grid = BasicGrid(gridSize, gridSize);
        val spatialStrategy = BasicSpatialStrategy(
                cellFactory = basicCellFactory,
                grid = grid,
                isPeriodic = false)

        val generator = PopulationGenerator<ConwayAgentState>(
                grid = grid,
                randomDouble = { random.nextDouble() }
        )
        generator.addAgentFactory({ ConwayAgentState() });
        generator.addAgentFactory({ ConwayAgentState().dead() })
        val population = generator.generate();

        val agentDelegate = ConwayAgentDelegate()

        simulation = SimulationImpl(
                agentDelegate = agentDelegate,
                cellDelegate = InertCellDelegate(),
                configuration = StaticConfiguration<ConwayAgentState>(Grouping.Simultaneous),
                movementStrategy = BasicMovementStrategy(),
                population = population,
                spatialStrategy = spatialStrategy,
                visibilityStrategy = BasicVisibilityStrategy()
        )
        simulation.addPrinter(GridPrinter<ConwayAgentState>(agentDelegate, grid), Frequency.Tick);

        val visualization = object: GridVisualization<ConwayAgentState>(grid, population, simulation.tick) {
            override fun drawCell(cell: Cell, graphics: Graphics) {
                val agent = this.population.agentsIn(cell).first()
                val color = if (agent.isAlive) {
                    Color.GREEN
                } else {
                    Color.DARK_GRAY
                }
                graphics.color = color
                graphics.fillRect(0, 0, graphics.clipBounds.width, graphics.clipBounds.height)
            }

        }
        simulation.addVisualization(visualization, Frequency.Tick)

        val window = JFrame()
        window.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        window.size = Dimension(400, 300)
        window.contentPane.add(visualization, BorderLayout.CENTER)
        window.isVisible = true
    }

    fun run(maxNumberOfTicks: Int) {
        simulation.run { tick, population ->
            val anyAlive = population
                    .agents()
                    .any { it.isAlive }
            anyAlive && tick < maxNumberOfTicks;
        }
    }
}
