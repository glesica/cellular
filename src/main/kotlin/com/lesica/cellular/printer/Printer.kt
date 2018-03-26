package com.lesica.cellular.printer

import com.lesica.cellular.population.Population
import com.lesica.cellular.states.AgentState

interface Printer<TAgentState: AgentState> {
    fun print(tick: Int, population: Population<TAgentState>)
}