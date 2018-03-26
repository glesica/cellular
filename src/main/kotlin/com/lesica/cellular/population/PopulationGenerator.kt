package com.lesica.cellular.population

import com.lesica.cellular.spatial.CellFactory
import com.lesica.cellular.spatial.Grid
import com.lesica.cellular.spatial.defaultCellFactory
import com.lesica.cellular.states.AgentState
import com.lesica.cellular.states.AgentStateFactory

/**
 * A utility class used to generate random populations.
 */
class PopulationGenerator<TAgentState: AgentState>(
        private val cellFactory: CellFactory = defaultCellFactory,
        private val grid: Grid,
        private val populationFactory: PopulationFactory<TAgentState> = defaultPopulationFactory(),
        private val randomDouble: () -> Double
) {
    private val _agentFactories = ArrayList<FactoryContainer>()

    fun addAgentFactory(factory: AgentStateFactory<TAgentState>, weight: Double = 1.0)
    {
        _agentFactories.add(FactoryContainer(factory, weight));
        adjustWeights()
    }

    fun generate(): MutablePopulation<TAgentState> {
        if (_agentFactories.isEmpty()) {
            throw IllegalStateException("No factories specified")
        }

        val population = populationFactory();

        for (col in 0 until grid.width) {
            for (row in 0 until grid.height) {
                val roll = randomDouble()
                var accumulator = 0.0;

                for (factory in _agentFactories) {
                    accumulator += factory.adjustedWeight
                    if (accumulator <= roll) {
                        continue
                    }

                    val agent = factory.factory()
                    if (agent != null) {
                        population.placeAgent(agent, cellFactory(col, row))
                    }

                    break
                }
            }
        }

        return population;
    }

    private fun adjustWeights() {
        val totalWeight = _agentFactories.sumByDouble { it.weight }
        for (factory in _agentFactories) {
            factory.adjustedWeight = factory.weight / totalWeight
        }
    }

    private inner class FactoryContainer(val factory: AgentStateFactory<TAgentState>, val weight: Double) {
        var adjustedWeight = weight
    }
}
