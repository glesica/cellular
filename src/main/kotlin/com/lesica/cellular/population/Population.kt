package com.lesica.cellular.population

import com.lesica.cellular.spatial.Cell
import com.lesica.cellular.states.AgentState

/**
 * The read-only portion of the population interface.
 */
interface Population<TAgentState: AgentState> {

    /**
     * The cell occupied by the given agent.
     */
    fun agentCell(agent: TAgentState): Cell

    /**
     * The number of agents in the population.
     */
    fun agentCount(): Int

    /**
     * All agents in the population.
     */
    fun agents(): Iterable<TAgentState>

    /**
     * All agents in the given cell.
     */
    fun agentsIn(cell: Cell): Iterable<TAgentState>

    /**
     * The number of cells occupied by at least min agents.
     */
    fun occupiedCellCount(min: Int = 1): Int
}