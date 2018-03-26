package com.lesica.cellular.delegates

import com.lesica.cellular.simulation.AgentController
import com.lesica.cellular.spatial.Cell
import com.lesica.cellular.states.AgentState

/**
 * An agent delegate that does nothing. May be useful
 * as a base class when a simulation only requires one
 * or two methods.
 */
open class InertAgentDelegate<TAgentState: AgentState>: AgentDelegate<TAgentState> {
    override fun onBorn(agent: TAgentState) = agent

    override fun onCloned(agent: TAgentState) = agent

    override fun onCombined(agent0: TAgentState, agent1: TAgentState) = agent0

    override fun onDied(agent: TAgentState) = agent

    override fun onMoved(agent: TAgentState, origin: Cell, destination: Cell) = agent

    override fun tick(controller: AgentController<TAgentState>) {}
}