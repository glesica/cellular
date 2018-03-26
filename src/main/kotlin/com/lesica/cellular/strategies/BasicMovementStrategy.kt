package com.lesica.cellular.strategies

import com.lesica.cellular.spatial.Cell
import com.lesica.cellular.states.AgentState

class BasicMovementStrategy<TAgentState: AgentState>: MovementStrategy<TAgentState> {

    override fun isAllowedToMove(agent: TAgentState, cell: Cell) = true

    override fun isLegalMove(agent: TAgentState, origin: Cell, destination: Cell) = true
}