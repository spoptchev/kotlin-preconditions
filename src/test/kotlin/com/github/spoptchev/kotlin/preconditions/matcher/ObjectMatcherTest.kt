package com.github.spoptchev.kotlin.preconditions.matcher

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class ObjectMatcherTest {

    private val matcher = object : ObjectMatcher {}

    @Test fun `test beNull valid`() {
        val result = matcher.beNull<String?>().test(null)

        assertTrue(result.valid)
    }

    @Test fun `test beNull invalid`() {
        val result = matcher.beNull<String?>().test("test")

        assertFalse(result.valid)
        assertEquals("expected test to be null", result.lazyMessage())
    }

    @Test fun `test equal valid`() {
        val result = matcher.equal("x").test("x")

        assertTrue(result.valid)
    }

    @Test fun `test equal invalid`() {
        val result = matcher.equal("x").test("y")

        assertFalse(result.valid)
        assertEquals("expected y to be equal to x", result.lazyMessage())
    }

    @Test fun `test sameInstanceAs valid`() {
        val result = matcher.sameInstanceAs("x").test("x")

        assertTrue(result.valid)
    }

    @Test fun `test sameInstanceAs invalid`() {
        val result = matcher.sameInstanceAs("y").test("x")

        assertFalse(result.valid)
        assertEquals("expected x to have the same reference as y", result.lazyMessage())
    }

}
