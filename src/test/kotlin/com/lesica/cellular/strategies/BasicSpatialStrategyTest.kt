package com.lesica.cellular.strategies

import com.lesica.cellular.spatial.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class BasicSpatialStrategyTest {

    val center = BasicCell(1, 1)
    val north = BasicCell(1, 0)
    val south = BasicCell(1, 2)
    val west = BasicCell(0, 1)
    val east = BasicCell(2, 1)
    val northwest = BasicCell(0, 0)
    val northeast = BasicCell(2, 0)
    val southwest = BasicCell(0, 2)
    val southeast = BasicCell(2, 2)

    fun strategy(isPeriodic: Boolean = false): SpatialStrategy {
        return BasicSpatialStrategy(
                cellFactory = defaultCellFactory,
                grid = BasicGrid(3, 3),
                isPeriodic = isPeriodic
        )
    }

    fun assertContains(iterable: Iterable<Cell>, element: Cell) {
        val found = iterable
                .firstOrNull { it == element }
        assertEquals(element, found)
    }

    fun assertContainsOnly(iterable: Iterable<Cell>, elements: Iterable<Cell>) {
        assertEquals(elements.count(), iterable.count())
        elements.forEach { assertContains(iterable, it) }
    }

    @Nested
    inner class CardinalNeighbors {

        fun assertCardinalNeighbors(origin: Cell, neighbors: Iterable<Cell>, isPeriodic: Boolean) {
            val cells = strategy(isPeriodic = isPeriodic).cardinalNeighbors(origin)
            assertContainsOnly(cells, neighbors)
        }

        @Test
        fun `should return correct cells at center`() {
            assertCardinalNeighbors(
                    center,
                    listOf(
                            north,
                            south,
                            west,
                            east
                    ), isPeriodic = true)
            assertCardinalNeighbors(
                    center,
                    listOf(
                            north,
                            south,
                            west,
                            east
                    ), isPeriodic = false)
        }

        @Test
        fun `should return correct cells at north edge`() {
            assertCardinalNeighbors(
                    north,
                    listOf(
                            south,
                            center,
                            northwest,
                            northeast
                    ), isPeriodic = true)
            assertCardinalNeighbors(
                    north,
                    listOf(
                            center,
                            northwest,
                            northeast
                    ), isPeriodic = false)
        }

        @Test
        fun `should return correct cells at south edge`() {
            assertCardinalNeighbors(
                    south,
                    listOf(
                            center,
                            north,
                            southwest,
                            southeast
                    ), isPeriodic = true)
            assertCardinalNeighbors(
                    south,
                    listOf(
                            center,
                            southwest,
                            southeast
                    ), isPeriodic = false)
        }

        @Test
        fun `should return correct cells at west edge`() {
            assertCardinalNeighbors(
                    west,
                    listOf(
                            northwest,
                            southwest,
                            east,
                            center
                    ), isPeriodic = true)
            assertCardinalNeighbors(
                    west,
                    listOf(
                            northwest,
                            southwest,
                            center
                    ), isPeriodic = false)
        }

        @Test
        fun `should return correct cells at east edge`() {
            assertCardinalNeighbors(
                    east,
                    listOf(
                            northeast,
                            southeast,
                            center,
                            west
                    ), isPeriodic = true)
            assertCardinalNeighbors(
                    east,
                    listOf(
                            northeast,
                            southeast,
                            center
                    ), isPeriodic = false)
        }

        @Test
        fun `should return correct cells at northwest corner`() {
            assertCardinalNeighbors(
                    northwest,
                    listOf(
                            southwest,
                            west,
                            northeast,
                            north
                    ), isPeriodic = true)
            assertCardinalNeighbors(
                    northwest,
                    listOf(
                            west,
                            north
                    ), isPeriodic = false)
        }

        @Test
        fun `should return correct cells at northeast corner`() {
            assertCardinalNeighbors(
                    northeast,
                    listOf(
                            southeast,
                            east,
                            north,
                            northwest
                    ), isPeriodic = true)
            assertCardinalNeighbors(
                    northeast,
                    listOf(
                            east,
                            north
                    ), isPeriodic = false)
        }

        @Test
        fun `should return correct cells at southwest corner`() {
            assertCardinalNeighbors(
                    southwest,
                    listOf(
                            west,
                            northwest,
                            southeast,
                            south
                    ), isPeriodic = true)
            assertCardinalNeighbors(
                    southwest,
                    listOf(
                            west,
                            south
                    ), isPeriodic = false)
        }

        @Test
        fun `should return correct cells at southeast corner`() {
            assertCardinalNeighbors(
                    southeast,
                    listOf(
                            east,
                            northeast,
                            south,
                            southwest
                    ), isPeriodic = true)
            assertCardinalNeighbors(
                    southeast,
                    listOf(
                            east,
                            south
                    ), isPeriodic = false)
        }
    }

    @Nested
    inner class Cells {

        @Test
        fun `should return correct cells`() {
            val cells = strategy().cells()
            assertContainsOnly(
                    cells,
                    listOf(
                            center,
                            north,
                            south,
                            west,
                            east,
                            northwest,
                            northeast,
                            southwest,
                            southeast
                    ))
        }
    }

    @Nested
    inner class DiagonalNeighbors {

        fun assertDiagonalNeighbors(origin: Cell, neighbors: Iterable<Cell>, isPeriodic: Boolean) {
            val cells = strategy(isPeriodic = isPeriodic).diagonalNeighbors(origin)
            assertContainsOnly(cells, neighbors)
        }

        @Test
        fun `should return correct cells at center`() {
            assertDiagonalNeighbors(
                    center,
                    listOf(
                            northwest,
                            northeast,
                            southwest,
                            southeast
                    ),
                    isPeriodic = true
            )
            assertDiagonalNeighbors(
                    center,
                    listOf(
                            northwest,
                            northeast,
                            southwest,
                            southeast
                    ),
                    isPeriodic = false
            )
        }

        @Test
        fun `should return correct cells at north`() {
            assertDiagonalNeighbors(
                    north,
                    listOf(
                            southwest,
                            southeast,
                            west,
                            east
                    ),
                    isPeriodic = true
            )
            assertDiagonalNeighbors(
                    north,
                    listOf(
                            west,
                            east
                    ),
                    isPeriodic = false
            )
        }

        @Test
        fun `should return correct cells at south`() {
            assertDiagonalNeighbors(
                    south,
                    listOf(
                            west,
                            east,
                            northwest,
                            northeast
                    ),
                    isPeriodic = true
            )
            assertDiagonalNeighbors(
                    south,
                    listOf(
                            west,
                            east
                    ),
                    isPeriodic = false
            )
        }

        @Test
        fun `should return correct cells at west`() {
            assertDiagonalNeighbors(
                    west,
                    listOf(
                            northeast,
                            north,
                            southeast,
                            south
                    ),
                    isPeriodic = true
            )
            assertDiagonalNeighbors(
                    west,
                    listOf(
                            north,
                            south
                    ),
                    isPeriodic = false
            )
        }

        @Test
        fun `should return correct cells at east`() {
            assertDiagonalNeighbors(
                    east,
                    listOf(
                            north,
                            northwest,
                            south,
                            southwest
                    ),
                    isPeriodic = true
            )
            assertDiagonalNeighbors(
                    east,
                    listOf(
                            north,
                            south
                    ),
                    isPeriodic = false
            )
        }

        @Test
        fun `should return correct cells at northwest`() {
            assertDiagonalNeighbors(
                    northwest,
                    listOf(
                            southeast,
                            south,
                            east,
                            center
                    ),
                    isPeriodic = true
            )
            assertDiagonalNeighbors(
                    northwest,
                    listOf(
                            center
                    ),
                    isPeriodic = false
            )
        }

        @Test
        fun `should return correct cells at northeast`() {
            assertDiagonalNeighbors(
                    northeast,
                    listOf(
                            south,
                            southwest,
                            center,
                            west
                    ),
                    isPeriodic = true
            )
            assertDiagonalNeighbors(
                    northeast,
                    listOf(
                            center
                    ),
                    isPeriodic = false
            )
        }

        @Test
        fun `should return correct cells at southwest`() {
            assertDiagonalNeighbors(
                    southwest,
                    listOf(
                            east,
                            center,
                            northeast,
                            north
                    ),
                    isPeriodic = true
            )
            assertDiagonalNeighbors(
                    southwest,
                    listOf(
                            center
                    ),
                    isPeriodic = false
            )
        }

        @Test
        fun `should return correct cells at southeast`() {
            assertDiagonalNeighbors(
                    southeast,
                    listOf(
                            center,
                            west,
                            north,
                            northwest
                    ),
                    isPeriodic = true
            )
            assertDiagonalNeighbors(
                    southeast,
                    listOf(
                            center
                    ),
                    isPeriodic = false
            )
        }
    }
}
