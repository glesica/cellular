package com.lesica.cellular.spatial

/**
 * A factory that produces a Cell given a column
 * and a row, respectively.
 */
typealias CellFactory = (Int, Int) -> Cell

val basicCellFactory = { column: Int, row: Int -> BasicCell(column, row) }
