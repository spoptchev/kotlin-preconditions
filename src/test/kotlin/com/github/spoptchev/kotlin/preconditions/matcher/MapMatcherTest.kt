package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.condition
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class MapMatcherTest {

    private val matcher = object : MapMatcher {}

    @Test fun `test hasKey valid`() {
        val result = matcher.hasKey(1).test(condition(mapOf(1 to "one")))

        assertTrue(result.valid)
    }

    @Test fun `test hasKey invalid`() {
        val result = matcher.hasKey(1).test(condition(mapOf(2 to "one")))

        assertFalse(result.valid)
        assertEquals("expected value {2=one} to contain key 1", result.lazyMessage())
    }

    @Test fun `test hasValue valid`() {
        val result = matcher.hasValue(1).test(condition(mapOf("one" to 1)))

        assertTrue(result.valid)
    }

    @Test fun `test hasValue invalid`() {
        val result = matcher.hasValue(1).test(condition(mapOf("one" to 2)))

        assertFalse(result.valid)
        assertEquals("expected value {one=2} to contain value 1", result.lazyMessage())
    }

    @Test fun `test contains valid`() {
        val result = matcher.contains(1, "one").test(condition(mapOf(1 to "one")))

        assertTrue(result.valid)
    }

    @Test fun `test contains invalid`() {
        val result = matcher.contains(1, "two").test(condition(mapOf(1 to "one")))

        assertEquals("expected value {1=one} to contain 1=two", result.lazyMessage())
    }

}
