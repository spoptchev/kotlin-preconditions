package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.not
import com.github.spoptchev.kotlin.preconditions.requireThat
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails


class CollectionMatcherTest {

    private val list = listOf("1", "2")

    @Test
    fun `test hasSize valid`() {
        requireThat(list) { hasSize(2) }
    }


    @Test
    fun `test hasSize invalid`() {
        val exception = assertFails {
            requireThat(list) { hasSize(3) }
        }

        assertEquals("expected value [1, 2] to have size 3 but has size 2", exception.message)
    }

    @Test
    fun `test contains valid`() {
        requireThat(list) { contains("1") }
    }

    @Test
    fun `test contains invalid`() {
        val exception = assertFails {
            requireThat(list) { contains("3") }
        }

        assertEquals("expected value [1, 2] to contain element 3", exception.message)
    }

    @Test
    fun `test isEmpty valid`() {
        requireThat(list) { not(isEmpty()) }
    }

    @Test
    fun `test isEmpty invalid`() {
        val exception = assertFails {
            requireThat(list) { isEmpty() }
        }

        assertEquals("expected value [1, 2] to be empty", exception.message)
    }

    @Test
    fun `test containsAll valid`() {
        requireThat(list) { containsAll("1", "2") }
    }

    @Test
    fun `test containsAll invalid`() {
        val exception = assertFails {
            requireThat(list) { containsAll("1", "2", "4") }
        }

        assertEquals("expected value [1, 2] to contain all values of [1, 2, 4]", exception.message)
    }

    @Test
    fun `test isSorted valid`() {
        requireThat(listOf(1, 2, 3)) { isSorted() }
    }

    @Test
    fun `test isSorted invalid`() {
        val exception = assertFails {
            requireThat(listOf(1, 3, 2)) { isSorted() }
        }

        assertEquals("expected value [1, 3, 2] to be sorted", exception.message)
    }

}
