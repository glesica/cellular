package com.lesica.cellular.strategies

import com.lesica.cellular.spatial.Cell

interface SpatialStrategy {

    /**
     * An iterable of the cardinal (north, south, east, west)
     * neighbors of the given cell. If a particular direction
     * would not result in a valid move for whatever reason,
     * then the return value of this method should not include
     * that cell.
     */
    fun cardinalNeighbors(cell: Cell): Iterable<Cell>

    /**
     * All valid cells in the grid. The resulting iterable
     * should not contain cells that are off-limits to
     * agents for whatever reason.
     */
    fun cells(): Iterable<Cell>

    /**
     * An iterable of the diagonal (northeast, southeast,
     * southwest, northwest) of the given cell. If a
     * particular direction would not result in a valid move
     * for whatever reason then the return value of this method
     * should not include that cell.
     */
    fun diagonalNeighbors(cell: Cell): Iterable<Cell>

    /**
     * The cell east of the given cell, or null if an eastward
     * movement from the given cell would be invalid.
     */
    fun eastOf(cell: Cell): Cell?

    /**
     * The cell east of the given cell, or null if a northward
     * movement from the given cell would be invalid.
     */
    fun northOf(cell: Cell): Cell?

    /**
     * The cell east of the given cell, or null if a southward
     * movement from the given cell would be invalid.
     */
    fun southOf(cell: Cell): Cell?

    /**
     * The cell east of the given cell, or null if a westward
     * movement from the given cell would be invalid.
     */
    fun westOf(cell: Cell): Cell?
}