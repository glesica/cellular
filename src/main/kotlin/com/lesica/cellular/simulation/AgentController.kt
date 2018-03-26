package com.lesica.cellular.simulation

import com.lesica.cellular.spatial.Cell
import com.lesica.cellular.states.AgentState

interface AgentController<TAgentState: AgentState> {

    val here: Cell

    val willBeRemoved: Boolean

    val me: TAgentState

    fun agentsHere(): Iterable<TAgentState>

    fun agentsIn(cell: Cell): Iterable<TAgentState>

    fun allAdjacentAgents(): Iterable<TAgentState>

    fun allAdjacentAgents(cell: Cell): Iterable<TAgentState>

    fun allAdjacentCells(): Iterable<Cell>

    fun allAdjacentCells(cell: Cell): Iterable<Cell>

    fun cardinalAgents(): Iterable<TAgentState>

    fun cardinalAgents(cell: Cell): Iterable<TAgentState>

    fun cardinalCells(): Iterable<Cell>

    fun cardinalCells(cell: Cell): Iterable<Cell>

    fun diagonalAgents(): Iterable<TAgentState>

    fun diagonalAgents(cell: Cell): Iterable<TAgentState>

    fun diagonalCells(): Iterable<Cell>

    fun diagonalCells(cell: Cell): Iterable<Cell>

    /**
     * Requests that the current agent be removed from the population.
     */
    fun remove()

    fun move(destination: Cell)

    fun randomVisibleCell(): Cell?

    fun randomVisibleEmptyCell(): Cell?

    fun reproduce()

    fun reproduce(partner: TAgentState)

    /**
     * Update the agent with a new state.
     */
    fun update(newMe: TAgentState)

    fun visibleAgents(): Iterable<TAgentState>

    fun visibleCells(): Iterable<Cell>

    fun visibleEmptyCells(): Iterable<Cell>
}