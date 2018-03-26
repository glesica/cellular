package com.lesica.cellular.simulation

import com.lesica.cellular.configuration.Configuration
import com.lesica.cellular.configuration.Frequency
import com.lesica.cellular.configuration.Grouping
import com.lesica.cellular.delegates.AgentDelegate
import com.lesica.cellular.delegates.CellDelegate
import com.lesica.cellular.population.MutablePopulation
import com.lesica.cellular.population.Population
import com.lesica.cellular.printer.Printer
import com.lesica.cellular.states.AgentState
import com.lesica.cellular.strategies.MovementStrategy
import com.lesica.cellular.strategies.SpatialStrategy
import com.lesica.cellular.strategies.VisibilityStrategy

class SimulationImpl<TAgentState : AgentState>(
        private val agentDelegate: AgentDelegate<TAgentState>,
        private val cellDelegate: CellDelegate<TAgentState>,
        private val configuration: Configuration<TAgentState>,
        private val movementStrategy: MovementStrategy<TAgentState>,
        private var population: MutablePopulation<TAgentState>,
        private val spatialStrategy: SpatialStrategy,
        private val visibilityStrategy: VisibilityStrategy) : Simulation<TAgentState> {

    private val printers = ArrayList<Pair<Frequency, Printer<TAgentState>>>()

    override var tick = 0
        private set

    override fun addPrinter(printer: Printer<TAgentState>, frequency: Frequency) {
        printers.add(Pair(frequency, printer))
    }

    private fun advanceTick() {
        tick++
    }

    private fun print(frequency: Frequency, population: Population<TAgentState> = this.population) {
        printers
                .filter { it.first == frequency }
                .forEach { it.second.print(tick, population) }
    }

    private fun replacePopulation(nextPopulation: MutablePopulation<TAgentState>) {
        population = nextPopulation
    }

    override fun run(shouldContinue: ShouldContinueCallback<TAgentState>) {
        if (tick == 0) {
            print(Frequency.Tick)
        }

        while (shouldContinue(tick, population)) {
            runTick()
            advanceTick()
            print(Frequency.Tick)
        }
    }

    private fun runTick() {
        if (tick == 0) {
            print(Frequency.Turn)
        }

        val nextPopulation = when (configuration.groupingForTick(tick, population)) {
            Grouping.OneAtATime -> population
            Grouping.Simultaneous -> population.clone()
        }

        // TODO: Make sure we can modify this during iteration, may need to copy list first
        configuration.orderForTick(tick, population)
                .take(configuration.turnsForTick(tick, nextPopulation))
                .forEach {
                    val controller = AgentControllerImpl(
                            agentDelegate = agentDelegate,
                            cellDelegate = cellDelegate,
                            me = it,
                            movementStrategy = movementStrategy,
                            population = population,
                            spatialStrategy = spatialStrategy,
                            visibilityStrategy = visibilityStrategy
                    )

                    agentDelegate.tick(controller)

                    if (controller.willBeRemoved) {
                        nextPopulation.removeAgent(controller.me)
                    } else {
                        nextPopulation.placeAgent(controller.me, controller.here)
                    }

                    print(Frequency.Turn, nextPopulation)
                }

        replacePopulation(nextPopulation)
    }
}