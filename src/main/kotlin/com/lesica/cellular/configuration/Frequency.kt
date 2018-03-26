package com.lesica.cellular.configuration

/**
 * Frequency with which a process, like a printer, should be run.
 */
enum class Frequency {
    /**
     * Once per turn. This means that if N agents take a turn
     * during a given tick, then a process that uses this frequency
     * will run N times per tick, immediately after each agent
     * takes its turn. In addition, it will run once at the
     * beginning of the simulation run.
     */
    Turn,

    /**
     * Once per tick. A process that uses this frequency will
     * run once for each simulation tick. The process will run
     * at the end of each tick, after all agents due a turn that
     * tick have had their turns. In addition it will run once
     * at the beginning of the simulation run.
     */
    Tick,
}