package com.lesica.cellular.population

import com.lesica.cellular.states.AgentState

typealias PopulationFactory<TAgentState> = () -> MutablePopulation<TAgentState>

fun <TAgentState: AgentState> basicPopulationFactory() =
        { BasicPopulation<TAgentState>() }
