package com.lesica.cellular.spatial

/**
 * A basic Grid implementation that represents an open
 * rectangular grid.
 */
data class BasicGrid(override val height: Int, override val width: Int) : Grid {
    override val capacity: Int = height * width
}