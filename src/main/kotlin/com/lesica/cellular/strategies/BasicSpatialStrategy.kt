package com.lesica.cellular.strategies

import com.lesica.cellular.spatial.Cell
import com.lesica.cellular.spatial.CellFactory
import com.lesica.cellular.spatial.Grid

class BasicSpatialStrategy(val cellFactory: CellFactory, val grid: Grid, val isPeriodic: Boolean): SpatialStrategy {

    override fun cardinalNeighbors(cell: Cell): Iterable<Cell> {
        val east = eastColumn(cell);
        val north = northRow(cell);
        val south = southRow(cell);
        val west = westColumn(cell);

        val list = ArrayList<Cell>()

        if (east.first) {
            list.add(cellFactory(east.second, cell.row))
        }

        if (north.first) {
            list.add(cellFactory(cell.column, north.second))
        }

        if (south.first) {
            list.add(cellFactory(cell.column, south.second))
        }

        if (west.first) {
            list.add(cellFactory(west.second, cell.row))
        }

        return list
    }

    override fun cells(): Iterable<Cell> {
        return (0 until grid.width).flatMap { col ->
            (0 until grid.height).map { row ->
                cellFactory(col, row)
            }
        }
    }

    override fun diagonalNeighbors(cell: Cell): Iterable<Cell> {
        val east = eastColumn(cell);
        val north = northRow(cell);
        val south = southRow(cell);
        val west = westColumn(cell);

        val list = ArrayList<Cell>()

        if (north.first)
        {
            if (east.first)
            {
                // Northeast
                list.add(cellFactory(east.second, north.second))
            }

            if (west.first)
            {
                // Northwest
                list.add(cellFactory(west.second, north.second))
            }
        }

        if (south.first)
        {
            if (east.first)
            {
                // Southeast
                list.add(cellFactory(east.second, south.second))
            }

            if (west.first)
            {
                // Southwest
                list.add(cellFactory(west.second, south.second))
            }
        }

        return list
    }

    fun eastColumn(cell: Cell): Pair<Boolean, Int> {
        var exists = false;
        var column = 0;

        if (cell.column < grid.width - 1) {
            exists = true;
            column = cell.column + 1;
        } else if (isPeriodic) {
            exists = true;
            column = 0;
        }

        return Pair(exists, column)
    }

    override fun eastOf(cell: Cell): Cell? {
        val east = eastColumn(cell)
        if (east.first) {
            return cellFactory(east.second, cell.row)
        }

        return null
    }

    override fun northOf(cell: Cell): Cell? {
        val north = northRow(cell)
        if (north.first) {
            return cellFactory(cell.column, north.second)
        }

        return null
    }

    fun northRow(cell: Cell): Pair<Boolean, Int> {
        var exists = false
        var row = 0

        if (cell.row > 0) {
            exists = true
            row = cell.row - 1
        } else if (isPeriodic) {
            exists = true
            row = grid.height - 1
        }

        return Pair(exists, row)
    }

    override fun southOf(cell: Cell): Cell? {
        val south = southRow(cell)
        if (south.first) {
            return cellFactory(cell.column, south.second)
        }

        return null
    }

    fun southRow(cell: Cell): Pair<Boolean, Int> {
        var exists = false;
        var row = 0;

        if (cell.row < grid.height - 1)
        {
            exists = true;
            row = cell.row + 1;
        } else if (isPeriodic)
        {
            exists = true;
            row = 0;
        }

        return Pair(exists, row);
    }

    fun westColumn(cell: Cell): Pair<Boolean, Int> {
        var exists = false;
        var column = 0;

        if (cell.column > 0)
        {
            exists = true;
            column = cell.column - 1;
        } else if (isPeriodic)
        {
            exists = true;
            column = grid.width - 1;
        }

        return Pair(exists, column);
    }

    override fun westOf(cell: Cell): Cell? {
        val west = westColumn(cell)
        if (west.first) {
            return cellFactory(west.second, cell.row)
        }

        return null
    }
}