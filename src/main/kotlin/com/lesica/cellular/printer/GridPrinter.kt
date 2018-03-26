package com.lesica.cellular.printer

import com.lesica.cellular.delegates.AgentDelegate
import com.lesica.cellular.population.Population
import com.lesica.cellular.spatial.BasicCell
import com.lesica.cellular.spatial.Grid
import com.lesica.cellular.states.AgentState

/**
 * A simple printer that displays the current grid and prints
 * the character corresponding to one of the agents in each
 * cell.
 */
class GridPrinter<TAgentState : AgentState>(
        private val agentDelegate: AgentDelegate<TAgentState>,
        private val grid: Grid
) : Printer<TAgentState> {

    override fun print(tick: Int, population: Population<TAgentState>) {
        for (row in 0 until grid.height) {
            for (col in 0 until grid.width) {
                val cell = BasicCell(col, row)
                val agent = population.agentsIn(cell).firstOrNull()

                if (agent == null) {
                    print(" ")
                } else {
                    print(agentDelegate.agentCharacter(agent))
                }

                print(" ")
            }

            println("")
        }

        println("")
    }
}