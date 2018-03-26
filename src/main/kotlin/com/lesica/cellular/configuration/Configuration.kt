package com.lesica.cellular.configuration

import com.lesica.cellular.population.Population
import com.lesica.cellular.states.AgentState

/**
 * A bundle of configuration and other behaviors provided
 * to a simulation at runtime.
 */
interface Configuration<TAgentState: AgentState> {

    /**
     * Determines the grouping used for a particular simulation
     * tick.
     */
    fun groupingForTick(tick: Int, population: Population<TAgentState>): Grouping

    /**
     * Determines the order in which agents will be given turns
     * during the given simulation tick. The order in which the
     * agents are returned is the order they will take turns.
     */
    fun orderForTick(tick: Int, population: Population<TAgentState>): Iterable<TAgentState>

    /**
     * Determines the number of agents that will have turns
     * during the given simulation tick.
     */
    fun turnsForTick(tick: Int, population: Population<TAgentState>): Int
}