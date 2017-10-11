package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.condition
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class ObjectMatcherTest {

    private val matcher = object : ObjectMatcher {}

    @Test fun `test beNull valid`() {
        val result = matcher.beNull<String?>().test(condition(null))

        assertTrue(result.valid)
    }

    @Test fun `test beNull invalid`() {
        val result = matcher.beNull<String?>().test(condition("test"))

        assertFalse(result.valid)
        assertEquals("expected value test to be null", result.lazyMessage())
    }

    @Test fun `test equal valid`() {
        val result = matcher.equal("x").test(condition("x"))

        assertTrue(result.valid)
    }

    @Test fun `test equal invalid`() {
        val result = matcher.equal("x").test(condition("y"))

        assertFalse(result.valid)
        assertEquals("expected value y to be equal to x", result.lazyMessage())
    }

    @Test fun `test sameInstanceAs valid`() {
        val result = matcher.sameInstanceAs("x").test(condition("x"))

        assertTrue(result.valid)
    }

    @Test fun `test sameInstanceAs invalid`() {
        val result = matcher.sameInstanceAs("y").test(condition("x"))

        assertFalse(result.valid)
        assertEquals("expected value x to have the same reference as y", result.lazyMessage())
    }

}
