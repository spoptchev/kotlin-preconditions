package com.github.spoptchev.kotlin.preconditions.conditions

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class ComparablePreconditionsTest {


    private val precondition = object : ComparablePreconditions {}

    @Test
    fun `test lt valid`() {
        val result = precondition.lt(2.0).test(1.0)

        assertTrue(result.valid)
    }

    @Test
    fun `test lt invalid`() {
        val result = precondition.lt(1.0).test(2.0)

        assertFalse(result.valid)
        assertEquals("expected 2.0 to be < 1.0", result.lazyMessage())
    }

    @Test
    fun `test lte valid`() {
        val result = precondition.lte(2.0).test(2.0)

        assertTrue(result.valid)
    }

    @Test
    fun `test lte invalid`() {
        val result = precondition.lte(2.0).test(3.0)

        assertFalse(result.valid)
        assertEquals("expected 3.0 to be <= 2.0", result.lazyMessage())
    }

    @Test
    fun `test gt valid`() {
        val result = precondition.gt(1.0).test(2.0)

        assertTrue(result.valid)
    }

    @Test
    fun `test gt invalid`() {
        val result = precondition.gt(2.0).test(1.0)

        assertFalse(result.valid)
        assertEquals( "expected 1.0 to be > 2.0", result.lazyMessage())
    }

    @Test
    fun `test gte valid`() {
        val result = precondition.gte(2.0).test(2.0)

        assertTrue(result.valid)
    }

    @Test
    fun `test gte invalid`() {
        val result = precondition.gte(2.0).test(1.0)

        assertFalse(result.valid)
        assertEquals( "expected 1.0 to be >= 2.0", result.lazyMessage())
    }

    @Test
    fun `test between valid`() {
        val result = precondition.between(2.0..3.0).test(2.5)

        assertTrue(result.valid)
    }

    @Test
    fun `test between invalid`() {
        val result = precondition.between(2.0..3.0).test(1.0)

        assertFalse(result.valid)
        assertEquals( "expected 1.0 to be in 2.0..3.0", result.lazyMessage())
    }

}
