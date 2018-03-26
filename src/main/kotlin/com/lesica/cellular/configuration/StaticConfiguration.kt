package com.lesica.cellular.configuration

import com.lesica.cellular.population.Population
import com.lesica.cellular.states.AgentState

class StaticConfiguration<TAgentState : AgentState>(
        val grouping: Grouping
) : Configuration<TAgentState> {

    override fun groupingForTick(tick: Int, population: Population<TAgentState>) =
            grouping

    override fun orderForTick(tick: Int, population: Population<TAgentState>) =
            population.agents()

    override fun turnsForTick(tick: Int, population: Population<TAgentState>) =
            population.agents().count()
}