package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.requireThat
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails


class AnyMatcherTest {

    @Test
    fun `test isNull valid`() {
        requireThat(null) { isNull() }
    }

    @Test
    fun `test isNull invalid`() {
        val exception = assertFails {
            requireThat("test") { isNull() }
        }

        assertEquals("expected value test to be null", exception.message)
    }

    @Test
    fun `test isEqualTo valid`() {
        requireThat(Pair(1, 2)) { isEqualTo(Pair(1, 2)) }
    }

    @Test
    fun `test isEqualTo invalid`() {
        val exception = assertFails {
            requireThat(Pair(1, 2)) { isEqualTo(Pair(2, 1)) }
        }

        assertEquals("expected value (1, 2) to be equal to (2, 1)", exception.message)
    }

    @Test
    fun `test isSameInstanceAs valid`() {
        requireThat("x") { isSameInstanceAs("x") }
    }

    @Test
    fun `test isSameInstanceAs invalid`() {
        val exception = assertFails {
            requireThat("x") { isSameInstanceAs("y") }
        }

        assertEquals("expected value x to have the same reference as y", exception.message)
    }

}
