package com.lesica.cellular.spatial

interface Grid {
    /**
     * Grid capacity, which could be less than the
     * total number of available cells if the design
     * of the simulation calls for certain cells to
     * be off-limits.
     *
     * TODO: Decide how the "map" should work
     *
     * We might want to implement the cell map as part of the
     * cell delegate or even as a new strategy. Keeping it in
     * the grid is probably a mistake since it needs to be
     * dynamic during a simulation run.
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