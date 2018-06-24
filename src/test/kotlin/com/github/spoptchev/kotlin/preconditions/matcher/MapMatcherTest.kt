package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.requireThat
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails


class MapMatcherTest {

    @Test
    fun `test hasKey valid`() {
        requireThat(mapOf(1 to "one")) { hasKey(1) }
    }

    @Test
    fun `test hasKey invalid`() {
        val exception = assertFails {
            requireThat(mapOf(2 to "one")) { hasKey(1) }
        }

        assertEquals("expected value {2=one} to contain key 1", exception.message)
    }

    @Test
    fun `test hasValue valid`() {
        requireThat(mapOf("one" to 1)) { hasValue(1) }
    }

    @Test
    fun `test hasValue invalid`() {
        val exception = assertFails {
            requireThat(mapOf("one" to 2)) { hasValue(1) }
        }

        assertEquals("expected value {one=2} to contain value 1", exception.message)
    }

    @Test
    fun `test contains valid`() {
        requireThat(mapOf(1 to "one")) { contains(1, "one") }
    }

    @Test
    fun `test contains invalid`() {
        val exception = assertFails {
            requireThat(mapOf(1 to "one")) { contains(1, "two") }
        }

        assertEquals("expected value {1=one} to contain 1=two", exception.message)
    }

}
