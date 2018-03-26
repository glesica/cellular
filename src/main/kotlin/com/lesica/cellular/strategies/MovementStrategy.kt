package com.lesica.cellular.strategies

import com.lesica.cellular.spatial.Cell
import com.lesica.cellular.states.AgentState

/**
 * A strategy that governs agent movement.
 */
interface MovementStrategy<in TAgentState: AgentState> {

    /**
     * Whether the given agent in the given cell is allowed to
     * move at all.
     */
    fun isAllowedToMove(agent: TAgentState, origin: Cell): Boolean

    /**
     * Whether the given agent is allowed to move from the
     * given origin cell to the given destination cell.
     */
    fun isLegalMove(agent: TAgentState, origin: Cell, destination: Cell): Boolean
}