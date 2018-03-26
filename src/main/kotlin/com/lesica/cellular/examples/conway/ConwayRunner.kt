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
import com.lesica.cellular.spatial.defaultCellFactory
import com.lesica.cellular.strategies.BasicMovementStrategy
import com.lesica.cellular.strategies.BasicSpatialStrategy
import com.lesica.cellular.strategies.BasicVisibilityStrategy
import java.util.*

private val random = Random();

public class ConwayRunner {
    var gridSize = 5

    private var simulation: Simulation<ConwayAgentState>

    init {
        val grid = BasicGrid(gridSize, gridSize);
        val spatialStrategy = BasicSpatialStrategy(
                cellFactory = defaultCellFactory,
                grid = grid,
                isPeriodic = false)

        val generator = PopulationGenerator<ConwayAgentState>(
                grid = grid,
                randomDouble = { random.nextDouble() }
        )
        generator.addAgentFactory({ ConwayAgentState() });
        generator.addAgentFactory({ ConwayAgentState().dead() })
        val population = generator.generate();

        simulation = SimulationImpl(
                agentDelegate = ConwayAgentDelegate(),
                cellDelegate = InertCellDelegate(),
                configuration = StaticConfiguration<ConwayAgentState>(Grouping.Simultaneous),
                movementStrategy = BasicMovementStrategy(),
                population = population,
                spatialStrategy = spatialStrategy,
                visibilityStrategy = BasicVisibilityStrategy()
        )
        simulation.addPrinter(GridPrinter<ConwayAgentState>(grid), Frequency.Tick);
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
