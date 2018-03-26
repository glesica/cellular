package com.lesica.cellular.configuration

/**
 * The method of grouping agent turns.
 */
enum class Grouping {

    /**
     * Agents will take their turns one at a time and the state
     * of the simulation will be updated after each turn. This
     * means that, for example, if agent N were to move from
     * position A to position B, when agent N+1 gets its turn
     * it would observe agent N at position B if agent N+1 has
     * its turn after agent N, or position A if agent N+1 had
     * its turn before agent N.
     */
    OneAtATime,

    /**
     * Agents will take their turns simultaneously and the state
     * of the simulation will only be updated once per tick,
     * after all turns are complete. This means that, for example,
     * if agent N wer to move from position A to position B, when
     * agent N+1 gets its turn it would observe agent N at position
     * A because simulation state has not yet been updated.
     *
     * Put another way, all agents see the same simulation state,
     * as it was at the beginning of the tick.
     */
    Simultaneous
}