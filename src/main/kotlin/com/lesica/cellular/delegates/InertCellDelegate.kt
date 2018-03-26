package com.lesica.cellular.delegates

import com.lesica.cellular.spatial.Cell
import com.lesica.cellular.states.AgentState

/**
 * A cell delegate that does nothing. May be useful
 * as a base class or on its own for simpler simulations.
 */
open class InertCellDelegate<TAgentState: AgentState>: CellDelegate<TAgentState> {
    override fun onArrived(agent: TAgentState, origin: Cell, destination: Cell) = agent

    override fun onDeparted(agent: TAgentState, origin: Cell, destination: Cell) = agent
}