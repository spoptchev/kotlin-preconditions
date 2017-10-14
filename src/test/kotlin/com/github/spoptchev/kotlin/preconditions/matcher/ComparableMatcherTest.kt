package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.condition
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class ComparableMatcherTest {

    private val matcher = object : ComparableMatcher {}

    @Test
    fun `test isLt valid`() {
        val result = matcher.isLt(2.0).test(condition(1.0))

        assertTrue(result.valid)
    }

    @Test
    fun `test isLt invalid`() {
        val result = matcher.isLt(1.0).test(condition(2.0))

        assertFalse(result.valid)
        assertEquals("expected value 2.0 to be < 1.0", result.lazyMessage())
    }

    @Test
    fun `test isLte valid`() {
        val result = matcher.isLte(2.0).test(condition(2.0))

        assertTrue(result.valid)
    }

    @Test
    fun `test isLte invalid`() {
        val result = matcher.isLte(2.0).test(condition(3.0))

        assertFalse(result.valid)
        assertEquals("expected value 3.0 to be <= 2.0", result.lazyMessage())
    }

    @Test
    fun `test isGt valid`() {
        val result = matcher.isGt(1.0).test(condition(2.0))

        assertTrue(result.valid)
    }

    @Test
    fun `test isGt invalid`() {
        val result = matcher.isGt(2.0).test(condition(1.0))

        assertFalse(result.valid)
        assertEquals( "expected value 1.0 to be > 2.0", result.lazyMessage())
    }

    @Test
    fun `test isGte valid`() {
        val result = matcher.isGte(2.0).test(condition(2.0))

        assertTrue(result.valid)
    }

    @Test
    fun `test isGte invalid`() {
        val result = matcher.isGte(2.0).test(condition(1.0))

        assertFalse(result.valid)
        assertEquals( "expected value 1.0 to be >= 2.0", result.lazyMessage())
    }

    @Test
    fun `test isBetween valid`() {
        val result = matcher.isBetween(2.0..3.0).test(condition(2.5))

        assertTrue(result.valid)
    }

    @Test
    fun `test isBetween invalid`() {
        val result = matcher.isBetween(2.0..3.0).test(condition(1.0))

        assertFalse(result.valid)
        assertEquals( "expected value 1.0 to be in 2.0..3.0", result.lazyMessage())
    }

}
