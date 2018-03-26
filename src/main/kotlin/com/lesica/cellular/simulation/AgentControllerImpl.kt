package com.lesica.cellular.simulation

import com.lesica.cellular.delegates.AgentDelegate
import com.lesica.cellular.delegates.CellDelegate
import com.lesica.cellular.population.Population
import com.lesica.cellular.spatial.Cell
import com.lesica.cellular.states.AgentState
import com.lesica.cellular.strategies.MovementStrategy
import com.lesica.cellular.strategies.SpatialStrategy
import com.lesica.cellular.strategies.VisibilityStrategy
import com.lesica.cellular.utilities.randomElement

// TODO: Provide test versions of all dependencies so we can write some tests

class AgentControllerImpl<TAgentState: AgentState>(
        private val agentDelegate: AgentDelegate<TAgentState>,
        private val cellDelegate: CellDelegate<TAgentState>,
        me: TAgentState,
        private val movementStrategy: MovementStrategy<TAgentState>,
        private val population: Population<TAgentState>,
        private val spatialStrategy: SpatialStrategy,
        private val visibilityStrategy: VisibilityStrategy)
    : AgentController<TAgentState> {

    override var here = population.agentCell(me)
        private set

    override var me = me
        private set

    override var willBeRemoved = false
        private set

    override fun agentsHere() = agentsIn(here)

    override fun agentsIn(cell: Cell): Iterable<TAgentState> {
        if (visibilityStrategy.hasLineOfSight(here, cell)) {
            return population
                    .agentsIn(cell)
                    .filter { it != me }
        }

        return listOf()
    }

    override fun allAdjacentAgents() = allAdjacentAgents(here)

    override fun allAdjacentAgents(cell: Cell) =
            cardinalAgents(cell) + diagonalAgents(cell)

    override fun allAdjacentCells() = allAdjacentCells(here)

    override fun allAdjacentCells(cell: Cell) =
            cardinalCells(cell) + diagonalCells(cell)

    override fun cardinalAgents() = cardinalAgents(here)

    override fun cardinalAgents(cell: Cell) =
            cardinalCells(cell).flatMap { agentsIn(it) }

    override fun cardinalCells() = cardinalCells(here)

    override fun cardinalCells(cell: Cell) =
            spatialStrategy
                    .cardinalNeighbors(cell)
                    .filter { visibilityStrategy.hasLineOfSight(cell, it) }

    override fun diagonalAgents() = diagonalAgents(here)

    override fun diagonalAgents(cell: Cell) =
            diagonalCells(cell).flatMap { agentsIn(it) }

    override fun diagonalCells() = diagonalCells(here)

    override fun diagonalCells(cell: Cell) =
            spatialStrategy
                    .diagonalNeighbors(cell)
                    .filter { visibilityStrategy.hasLineOfSight(cell, it) }

    override fun remove() {
        if (willBeRemoved) {
            return
        }

        willBeRemoved = true
        me = agentDelegate.onDied(me)
    }

    override fun move(destination: Cell) {
        if (!movementStrategy.isAllowedToMove(me, here)) {
            return
        }

        if (!movementStrategy.isLegalMove(me, here, destination)) {
            return
        }

        me = cellDelegate.onDeparted(me, here, destination)
        me = agentDelegate.onMoved(me, here, destination)
        me = cellDelegate.onArrived(me, here, destination)

        here = destination
    }

    override fun randomVisibleCell(): Cell? {
        try {
            return visibleCells().randomElement()
        } catch (e: UnsupportedOperationException) {
            return null
        }
    }

    override fun randomVisibleEmptyCell(): Cell? {
        try {
            return visibleEmptyCells().randomElement()
        } catch (e: UnsupportedOperationException) {
            return null
        }
    }

    override fun reproduce() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun reproduce(partner: TAgentState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(newMe: TAgentState) {
        if (newMe.id != me.id) {
            throw IllegalArgumentException("New agent state has different ID")
        }

        me = newMe
    }

    override fun visibleAgents() =
            visibleCells()
                    .flatMap { population.agentsIn(it) }

    override fun visibleCells() =
            spatialStrategy
                    .cells()
                    .filter { visibilityStrategy.hasLineOfSight(here, it) }

    override fun visibleEmptyCells() =
            visibleCells()
                    .filter { !population.agentsIn(it).none() }
}