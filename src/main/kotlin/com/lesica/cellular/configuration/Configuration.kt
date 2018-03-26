package com.lesica.cellular.configuration

import com.lesica.cellular.population.Population
import com.lesica.cellular.states.AgentState

interface Configuration<TAgentState: AgentState> {

    fun groupingForTick(tick: Int, population: Population<TAgentState>): Grouping

    fun orderForTick(tick: Int, population: Population<TAgentState>): Iterable<TAgentState>

    fun turnsForTick(tick: Int, population: Population<TAgentState>): Int
}