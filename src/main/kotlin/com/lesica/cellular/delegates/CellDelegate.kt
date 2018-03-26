package com.lesica.cellular.delegates

import com.lesica.cellular.spatial.Cell
import com.lesica.cellular.states.AgentState

/**
 * A cell delegate implements behaviors that correspond to
 * various simulation events. Cells may update agent state
 * when these events occur.
 */
interface CellDelegate<TAgentState: AgentState> {
    /**
     * Method called on an agent when an it arrives in a new
     * cell. The state returned will become the new state of
     * the agent that moved.
     */
    fun onArrived(agent: TAgentState, origin: Cell, destination: Cell): TAgentState

    /**
     * Method called on an agent when an it departs for a new
     * cell. The state returned will become the new state of
     * the agent that moved.
     */
    fun onDeparted(agent: TAgentState, origin: Cell, destination: Cell): TAgentState
}