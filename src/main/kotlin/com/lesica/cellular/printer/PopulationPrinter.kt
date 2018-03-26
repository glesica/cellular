package com.lesica.cellular.printer

import com.lesica.cellular.population.Population
import com.lesica.cellular.states.AgentState

/**
 * A simple printer that displays some basic population data
 */
class PopulationPrinter<TAgentState : AgentState> : Printer<TAgentState> {

    override fun print(tick: Int, population: Population<TAgentState>) {
        println("Tick: $tick")
        println("Population: ${population.agentCount()}")
        println("Occupied Cells: ${population.occupiedCellCount()}")
    }
}