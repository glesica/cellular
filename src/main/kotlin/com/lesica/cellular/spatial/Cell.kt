package com.lesica.cellular.spatial

/**
 * A single grid cell.
 *
 * Note that cell implementations need to have meaningful `equals`
 * and `hashCode` implementations in order to work correctly with
 * other classes in the library such as Population.
 *
 * If using Kotlin, the easiest way to do this is to use a data
 * class for cell implementations.
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