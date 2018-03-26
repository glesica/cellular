package com.lesica.cellular.population

import com.lesica.cellular.spatial.BasicCell
import com.lesica.cellular.states.BasicAgentState
import org.junit.jupiter.api.Assertions.assertEquals
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
            pop.placeAgent(agent01, cell0)
            pop.placeAgent(agent02, cell1)

            assertEquals(cell0, pop.agentCell(agent01));
            assertEquals(cell1, pop.agentCell(agent02));
            // TODO: Population assumes agent state is a data class, doh...
        }
    }
}