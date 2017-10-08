package com.github.spoptchev.kotlin.preconditions.matcher

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class StringMatcherTest {

    private val matcher = object : StringMatcher {}

    @Test fun `test match valid`() {
        val result = matcher.match("hello").test("hello")

        assertTrue(result.valid)
    }

    @Test fun `test match invalid`() {
        val result = matcher.match("invalid").test("something")

        assertFalse(result.valid)
        assertEquals("expected 'something' to match 'invalid'", result.lazyMessage())
    }

    @Test fun `test startWith valid`() {
        val result = matcher.startWith("he").test("hello")

        assertTrue(result.valid)
    }

    @Test fun `test startWith invalid`() {
        val result = matcher.startWith("eh").test("hello")

        assertFalse(result.valid)
        assertEquals("expected 'hello' to start with 'eh'", result.lazyMessage())
    }

    @Test fun `test include valid`() {
        val result = matcher.include("el").test("hello")

        assertTrue(result.valid)
    }

    @Test fun `test include invalid`() {
        val result = matcher.include("le").test("hello")

        assertFalse(result.valid)
        assertEquals("expected 'hello' to include 'le'", result.lazyMessage())
    }

    @Test fun `test endWith valid`() {
        val result = matcher.endWith("lo").test("hello")

        assertTrue(result.valid)
    }

    @Test fun `test endWith invalid`() {
        val result = matcher.endWith("ol").test("hello")

        assertFalse(result.valid)
        assertEquals("expected 'hello' to end with 'ol'", result.lazyMessage())
    }

    @Test fun `test haveLength valid`() {
        val result = matcher.haveLength(5).test("hello")

        assertTrue(result.valid)
    }

    @Test fun `test haveLength invalid`() {
        val result = matcher.haveLength(2).test("hello")

        assertEquals("expected 'hello' to have length 2", result.lazyMessage())
    }

}
