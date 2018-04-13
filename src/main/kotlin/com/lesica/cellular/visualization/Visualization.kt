package com.lesica.cellular.visualization

import com.lesica.cellular.population.Population
import com.lesica.cellular.states.AgentState

/**
 * A visualization of the current state of the simulation.
 */
interface Visualization<TAgentState: AgentState> {

    fun update(tick: Int, population: Population<TAgentState>)
}