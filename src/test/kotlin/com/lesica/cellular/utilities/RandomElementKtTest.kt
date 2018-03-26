package com.lesica.cellular.utilities

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class RandomElementKtTest {

    @Nested
    inner class RandomElement {

        @Test
        fun `should throw on empty iterable`() {
            val iter = listOf<Any>()
            assertThrows(UnsupportedOperationException::class.java, { iter.randomElement() })
        }

        @Test
        fun `should return sole element from single-element iterable`() {
            val item = "item"
            val iter = listOf(item)
            val rand = iter.randomElement()
            assertEquals(item, rand)
        }

        @Test
        fun `should return element from multi-element iterable`() {
            val item0 = "item0"
            val item1 = "item1"
            val iter = listOf(item0, item1)
            val rand = iter.randomElement()
            assertTrue(iter.contains(rand))
        }
    }
}