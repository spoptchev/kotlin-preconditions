package com.spas.kotlin.preconditions.conditions

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class StringPreconditionsTest {

    private val precondition = object : StringPreconditions {}

    @Test fun `test match valid`() {
        val result = precondition.match("hello").test("hello")

        assertTrue(result.valid)
    }

    @Test fun `test match invalid`() {
        val result = precondition.match("invalid").test("something")

        assertFalse(result.valid)
        assertEquals("expected 'something' to match 'invalid'", result.lazyMessage())
    }

    @Test fun `test startWith valid`() {
        val result = precondition.startWith("he").test("hello")

        assertTrue(result.valid)
    }

    @Test fun `test startWith invalid`() {
        val result = precondition.startWith("eh").test("hello")

        assertFalse(result.valid)
        assertEquals("expected 'hello' to start with 'eh'", result.lazyMessage())
    }

    @Test fun `test include valid`() {
        val result = precondition.include("el").test("hello")

        assertTrue(result.valid)
    }

    @Test fun `test include invalid`() {
        val result = precondition.include("le").test("hello")

        assertFalse(result.valid)
        assertEquals("expected 'hello' to include 'le'", result.lazyMessage())
    }

    @Test fun `test endWith valid`() {
        val result = precondition.endWith("lo").test("hello")

        assertTrue(result.valid)
    }

    @Test fun `test endWith invalid`() {
        val result = precondition.endWith("ol").test("hello")

        assertFalse(result.valid)
        assertEquals("expected 'hello' to end with 'ol'", result.lazyMessage())
    }

    @Test fun `test haveLength valid`() {
        val result = precondition.haveLength(5).test("hello")

        assertTrue(result.valid)
    }

    @Test fun `test haveLength invalid`() {
        val result = precondition.haveLength(2).test("hello")

        assertEquals("expected 'hello' to have length 2", result.lazyMessage())
    }

}
