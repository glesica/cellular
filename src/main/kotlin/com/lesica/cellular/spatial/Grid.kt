package com.lesica.cellular.spatial

interface Grid {
    /**
     * Grid capacity, which could be less than the
     * total number of available cells if the design
     * of the simulation calls for certain cells to
     * be off-limits.
     */
    val capacity: Int

    /**
     * The height of the grid, in cells.
     */
    val height: Int

    /**
     * The width of the grid, in cells.
     */
    val width: Int
}