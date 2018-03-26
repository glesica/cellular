package com.lesica.cellular.population

import com.lesica.cellular.spatial.Cell
import com.lesica.cellular.states.AgentState

/**
 * A basic MutablePopulation implementation.
 */
class BasicPopulation<TAgentState: AgentState>: MutablePopulation<TAgentState> {
    private val cellToIds = HashMap<Cell, MutableSet<Long>>()

    private val idToAgent = HashMap<Long, TAgentState>()

    private val idToCell = HashMap<Long, Cell>()

    override fun agentCell(agent: TAgentState) =
            idToCell[agent.id] ?: throw IllegalArgumentException()

    override fun agentCount() = idToAgent.size

    override fun agents() = idToAgent.values

    override fun agentsIn(cell: Cell) =
            cellToIds[cell]?.map { idToAgent[it]!! } ?: listOf()

    override fun clone(): MutablePopulation<TAgentState> {
        val population = BasicPopulation<TAgentState>()
        for (id in idToAgent.keys) {
            val agent = idToAgent[id]!!
            val cell = idToCell[id]!!
            population.placeAgent(agent, cell)
        }
        return population
    }

    override fun occupiedCellCount(min: Int) =
            cellToIds.values.filter { it.size >= min }.size

    override fun placeAgent(agent: TAgentState, destination: Cell) {
        removeAgent(agent)

        idToAgent[agent.id] = agent
        idToCell[agent.id] = destination

        // Update cellToAgents
        if (!cellToIds.containsKey(destination)) {
            cellToIds[destination] = LinkedHashSet()
        }
        cellToIds[destination]!!.add(agent.id)
    }

    override fun removeAgent(agent: TAgentState) {
        val cell = idToCell[agent.id] ?: return

        cellToIds[cell]?.remove(agent.id)
        idToAgent.remove(agent.id)
        idToCell.remove(agent.id)
    }
}