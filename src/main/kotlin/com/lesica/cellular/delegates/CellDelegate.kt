package com.lesica.cellular.delegates

import com.lesica.cellular.spatial.Cell
import com.lesica.cellular.states.AgentState

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