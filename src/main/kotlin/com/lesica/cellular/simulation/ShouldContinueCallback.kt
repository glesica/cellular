package com.lesica.cellular.simulation

import com.lesica.cellular.population.Population

/**
 * A callback invoked to determine whether an additional
 * simulation tick should be run.
 *
 * The first parameter is the current tick, the second
 * is the current population.
 */
typealias ShouldContinueCallback<TAgentState> =
        (Int, Population<TAgentState>) -> Boolean
