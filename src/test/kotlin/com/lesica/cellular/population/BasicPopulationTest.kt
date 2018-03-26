package com.lesica.cellular.population

import com.lesica.cellular.spatial.BasicCell
import com.lesica.cellular.states.BasicAgentState
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BasicPopulationTest {

    val agent01 = BasicAgentState('0', 0)
    val agent02 = BasicAgentState('0', 0)
    val agent1 = BasicAgentState('1', 1)
    val agent2 = BasicAgentState('2', 2)

    val cell0 = BasicCell(0, 0)
    val cell1 = BasicCell(0, 1)
    val cell2 = BasicCell(0, 2)

    @Nested
    inner class AgentsIn {

        @Test
        fun `should return agents in cell`() {
            val pop = BasicPopulation<BasicAgentState>()
            pop.placeAgent(agent1, cell0)
            pop.placeAgent(agent2, cell0)

            // TODO: Technically this might not work since we used a set
            assertIterableEquals(listOf(agent1, agent2), pop.agentsIn(cell0))
        }
    }

    @Nested
    inner class PlaceAgent {

        @Test
        fun `should associate agent with cell`() {
            val pop = BasicPopulation<BasicAgentState>()
            pop.placeAgent(agent1, cell0)
            pop.placeAgent(agent2, cell1)

            assertEquals(cell0, pop.agentCell(agent1))
            assertEquals(cell1, pop.agentCell(agent2))
        }

        @Test
        fun `should move agent with same id`() {
            val pop = BasicPopulation<BasicAgentState>()
            pop.placeAgent(agent01, cell0)
            pop.placeAgent(agent02, cell1)

            assertEquals(1, pop.agentCount())
            assertEquals(cell1, pop.agentCell(agent01))
            assertEquals(cell1, pop.agentCell(agent02))
        }
    }
}