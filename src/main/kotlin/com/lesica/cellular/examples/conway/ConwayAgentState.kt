package com.lesica.cellular.examples.conway

import com.lesica.cellular.states.AgentState

private val aliveChar = '\u25A0'
private val deadChar = '\u25A1'
private var nextID = 0L

class ConwayAgentState: AgentState {

    constructor() {
        this.char = aliveChar
        this.id = nextID++
        this.isAlive = true
    }

    private constructor(id: Long, isAlive: Boolean) {
        this.char = if (isAlive) aliveChar else deadChar
        this.id = id
        this.isAlive = isAlive
    }

    override val char: Char

    override val id: Long

    val isAlive: Boolean

    fun alive(): ConwayAgentState = ConwayAgentState(id, true)

    fun dead(): ConwayAgentState = ConwayAgentState(id, false)
}