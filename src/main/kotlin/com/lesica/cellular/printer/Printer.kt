package com.lesica.cellular.printer

import com.lesica.cellular.population.Population
import com.lesica.cellular.states.AgentState

/**
 * A printer can be used to print or otherwise display the
 * current state of a simulation.
 *
 * Printers are intended for "live" visualization and debugging,
 * for more comprehensive data logging use a Recorder.
 */
interface Printer<TAgentState: AgentState> {
    fun print(tick: Int, population: Population<TAgentState>)
}