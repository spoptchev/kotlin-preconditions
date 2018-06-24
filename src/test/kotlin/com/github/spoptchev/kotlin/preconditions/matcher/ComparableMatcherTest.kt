package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.requireThat
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails


class ComparableMatcherTest {

    @Test
    fun `test isLt valid`() {
        requireThat(1.0) { isLt(2.0) }
    }

    @Test
    fun `test isLt invalid`() {
        val exception = assertFails {
            requireThat(2.0) { isLt(1.0) }
        }

        assertEquals("expected value 2.0 to be < 1.0", exception.message)
    }

    @Test
    fun `test isLte valid`() {
        requireThat(2.0) { isLte(2.0) }
    }

    @Test
    fun `test isLte invalid`() {
        val exception = assertFails {
            requireThat(3.0) { isLte(2.0) }
        }

        assertEquals("expected value 3.0 to be <= 2.0", exception.message)
    }

    @Test
    fun `test isGt valid`() {
        requireThat(2.0) { isGt(1.0) }
    }

    @Test
    fun `test isGt invalid`() {
        val exception = assertFails {
            requireThat(1.0) { isGt(2.0) }
        }

        assertEquals("expected value 1.0 to be > 2.0", exception.message)
    }

    @Test
    fun `test isGte valid`() {
        requireThat(2.0) { isGte(2.0) }
    }

    @Test
    fun `test isGte invalid`() {
        val exception = assertFails {
            requireThat(1.0) { isGte(2.0) }
        }

        assertEquals("expected value 1.0 to be >= 2.0", exception.message)
    }

    @Test
    fun `test isBetween valid`() {
        requireThat(2.5) { isBetween(2.0..3.0) }
    }

    @Test
    fun `test isBetween invalid`() {
        val exception = assertFails {
            requireThat(1.0) { isBetween(2.0..3.0) }
        }

        assertEquals("expected value 1.0 to be in 2.0..3.0", exception.message)
    }

}
