package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.condition
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class ObjectMatcherTest {

    private val matcher = object : ObjectMatcher {}

    @Test fun `test isNull valid`() {
        val result = matcher.isNull<String?>().test(condition(null))

        assertTrue(result.valid)
    }

    @Test fun `test isNull invalid`() {
        val result = matcher.isNull<String?>().test(condition("test"))

        assertFalse(result.valid)
        assertEquals("expected value test to be null", result.lazyMessage())
    }

    @Test fun `test isEqualTo valid`() {
        val result = matcher.isEqualTo("x").test(condition("x"))

        assertTrue(result.valid)
    }

    @Test fun `test isEqualTo invalid`() {
        val result = matcher.isEqualTo("x").test(condition("y"))

        assertFalse(result.valid)
        assertEquals("expected value y to be equal to x", result.lazyMessage())
    }

    @Test fun `test isSameInstanceAs valid`() {
        val result = matcher.isSameInstanceAs("x").test(condition("x"))

        assertTrue(result.valid)
    }

    @Test fun `test isSameInstanceAs invalid`() {
        val result = matcher.isSameInstanceAs("y").test(condition("x"))

        assertFalse(result.valid)
        assertEquals("expected value x to have the same reference as y", result.lazyMessage())
    }

}
