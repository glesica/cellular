package com.lesica.cellular.population

import com.lesica.cellular.spatial.Cell
import com.lesica.cellular.states.AgentState

/**
 * The mutable portion of the population interface.
 */
interface MutablePopulation<TAgentState: AgentState>: Population<TAgentState> {

    /**
     * Create a shallow copy of the population such that agents
     * can be added to or removed from the clone without any
     * effect on the original.
     */
    fun clone(): MutablePopulation<TAgentState>

    /**
     * Place the given agent in the given cell.
     */
    fun placeAgent(agent: TAgentState, destination: Cell)

    /**
     * Remove the given agent from the population.
     */
    fun removeAgent(agent: TAgentState)
}