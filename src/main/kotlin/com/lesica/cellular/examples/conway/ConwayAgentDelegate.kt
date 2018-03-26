package com.lesica.cellular.examples.conway

import com.lesica.cellular.delegates.InertAgentDelegate
import com.lesica.cellular.simulation.AgentController

class ConwayAgentDelegate: InertAgentDelegate<ConwayAgentState>() {

    override fun tick(controller: AgentController<ConwayAgentState>) {
        val me = controller.me;
        val liveNeighbors = controller
                .allAdjacentAgents()
                .count { it.isAlive }

        if (me.isAlive)
        {
            if (liveNeighbors < 2 || liveNeighbors > 3)
            {
                controller.update(me.dead());
            }
        }
        else
        {
            if (liveNeighbors == 3)
            {
                controller.update(me.alive());
            }
        }
    }
}