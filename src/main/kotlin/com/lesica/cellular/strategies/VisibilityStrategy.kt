package com.lesica.cellular.strategies

import com.lesica.cellular.spatial.Cell

interface VisibilityStrategy {

    /**
     * Whether or not an arbitrary agent present in the origin
     * cell would be able to observe other agents in the
     * destination cell.
     */
    fun hasLineOfSight(origin: Cell, destination: Cell): Boolean
}