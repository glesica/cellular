package com.lesica.cellular.simulation

import com.lesica.cellular.configuration.Frequency
import com.lesica.cellular.printer.Printer
import com.lesica.cellular.states.AgentState

interface Simulation<TAgentState: AgentState> {

    /**
     * The current simulation tick, which represents the number of
     * generations the population has been through. There is one
     * snapshot taken per simulation tick. The order in which agents
     * take turns during a given tick is determined by the turnOrder
     * callback. The number of agents that are given a turn during a
     * given tick is determined by the turnsPerTick callback.
     */
    val tick: Int

    /**
     * Add a printer to the simulation to be run with the given
     * frequency.
     */
    fun addPrinter(printer: Printer<TAgentState>, frequency: Frequency)

    /**
     * Continue to run the simulation as long as the provided callback
     * returns true. The arguments given to the callback will be the
     * the tick number and a read-only population instance.
     */
    fun run(shouldContinue: ShouldContinueCallback<TAgentState>)
}