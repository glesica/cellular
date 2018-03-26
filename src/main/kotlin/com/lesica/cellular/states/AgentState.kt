package com.lesica.cellular.states

interface AgentState {

    /**
     * An identifier unique to a single logical agent
     * within a simulation. This value should never be
     * mutated. It is used internally to keep track of
     * agent state and to log simulation state.
     */
    val id: Long
}