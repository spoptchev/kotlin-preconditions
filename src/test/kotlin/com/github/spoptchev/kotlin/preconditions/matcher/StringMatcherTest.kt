package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.condition
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class StringMatcherTest {

    private val matcher = object : StringMatcher {}

    @Test fun `test match valid`() {
        val result = matcher.match("hello").test(condition("hello"))

        assertTrue(result.valid)
    }

    @Test fun `test match invalid`() {
        val result = matcher.match("invalid").test(condition("something"))

        assertFalse(result.valid)
        assertEquals("expected value something to match 'invalid'", result.lazyMessage())
    }

    @Test fun `test startWith valid`() {
        val result = matcher.startWith("he").test(condition("hello"))

        assertTrue(result.valid)
    }

    @Test fun `test startWith invalid`() {
        val result = matcher.startWith("eh").test(condition("hello"))

        assertFalse(result.valid)
        assertEquals("expected value hello to start with 'eh'", result.lazyMessage())
    }

    @Test fun `test include valid`() {
        val result = matcher.include("el").test(condition("hello"))

        assertTrue(result.valid)
    }

    @Test fun `test include invalid`() {
        val result = matcher.include("le").test(condition("hello"))

        assertFalse(result.valid)
        assertEquals("expected value hello to include 'le'", result.lazyMessage())
    }

    @Test fun `test endWith valid`() {
        val result = matcher.endWith("lo").test(condition("hello"))

        assertTrue(result.valid)
    }

    @Test fun `test endWith invalid`() {
        val result = matcher.endWith("ol").test(condition("hello"))

        assertFalse(result.valid)
        assertEquals("expected value hello to end with 'ol'", result.lazyMessage())
    }

    @Test fun `test haveLength valid`() {
        val result = matcher.haveLength(5).test(condition("hello"))

        assertTrue(result.valid)
    }

    @Test fun `test haveLength invalid`() {
        val result = matcher.haveLength(2).test(condition("hello"))

        assertEquals("expected value hello to have length 2", result.lazyMessage())
    }

}
