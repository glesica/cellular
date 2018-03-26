package com.lesica.cellular.examples.conway

import com.lesica.cellular.states.AgentState

private var nextID = 0L

class ConwayAgentState: AgentState {

    constructor() {
        this.id = nextID++
        this.isAlive = true
    }

    private constructor(id: Long, isAlive: Boolean) {
        this.id = id
        this.isAlive = isAlive
    }

    override val id: Long

    val isAlive: Boolean

    fun alive(): ConwayAgentState = ConwayAgentState(id, true)

    fun dead(): ConwayAgentState = ConwayAgentState(id, false)
}