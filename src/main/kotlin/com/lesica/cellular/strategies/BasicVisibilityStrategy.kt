package com.lesica.cellular.strategies

import com.lesica.cellular.spatial.Cell

class BasicVisibilityStrategy: VisibilityStrategy {

    override fun hasLineOfSight(origin: Cell, destination: Cell) = true
}