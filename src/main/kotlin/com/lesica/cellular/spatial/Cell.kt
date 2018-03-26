package com.lesica.cellular.spatial

/**
 * A single grid cell.
 */
interface Cell {
    /**
     * The grid column of the cell.
     */
    val column: Int

    /**
     * The grid row of the cell.
     */
    val row: Int
}