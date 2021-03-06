package com.lesica.cellular.delegates

import com.lesica.cellular.simulation.AgentController
import com.lesica.cellular.spatial.Cell
import com.lesica.cellular.states.AgentState

/**
 * An agent delegate is responsible for updating agent state
 * based on various simulation events. This allows behavior
 * to be isolated for easier testing and re-use.
 */
interface AgentDelegate<TAgentState: AgentState> {

    /**
     * Method to provide a single character representation of
     * a given agent. Can be used in printers and such to easily
     * display an agent.
     */
    fun agentCharacter(agent: TAgentState): Char

    /**
     * Method called on an agent when it is first "born" through
     * cloning or combination. Note that this is not invoked for
     * agents present in the simulation when it starts.
     */
    fun onBorn(agent: TAgentState): TAgentState

    /**
     * Method called on an agent when it requests to be cloned.
     * The state returned will become the child agent. It will
     * be placed in the same cell as its parent.
     */
    fun onCloned(agent: TAgentState): TAgentState

    /**
     * Method called on the parents when an agent is combined with
     * another agent. The state returned will become the child
     * agent. It will be placed in the same cell as the parent
     * that initiated the combination.
     */
    fun onCombined(agent0: TAgentState, agent1: TAgentState): TAgentState

    /**
     * Method called when an agent dies. It may update its
     * final state, that is, the state it will be left in
     * after it has been removed from the population.
     */
    fun onDied(agent: TAgentState): TAgentState

    /**
     * Method called when an agent moves. It may update the
     * agent state based on the origin and destination of its
     * movement. For example, it might reduce stamina.
     */
    fun onMoved(agent: TAgentState, origin: Cell, destination: Cell): TAgentState

    /**
     * The method that should implement agent logic. Called
     * each time an agent takes a turn. The controller provided
     * includes getters and methods for various aspects of
     * simulation state as well as methods that allow the agent
     * to move, and so on.
     *
     * TODO: Rename to `turn`
     */
    fun tick(controller: AgentController<TAgentState>)
}