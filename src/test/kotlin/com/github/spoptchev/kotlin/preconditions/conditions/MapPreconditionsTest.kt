package com.github.spoptchev.kotlin.preconditions.conditions

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class MapPreconditionsTest {

    private val precondition = object : MapPreconditions {}

    @Test fun `test haveKey valid`() {
        val result = precondition.haveKey(1).test(mapOf(1 to "one"))

        assertTrue(result.valid)
    }

    @Test fun `test haveKey invalid`() {
        val result = precondition.haveKey(1).test(mapOf(2 to "one"))

        assertFalse(result.valid)
        assertEquals("expected {2=one} to contain key 1", result.lazyMessage())
    }

    @Test fun `test haveValue valid`() {
        val result = precondition.haveValue(1).test(mapOf("one" to 1))

        assertTrue(result.valid)
    }

    @Test fun `test haveValue invalid`() {
        val result = precondition.haveValue(1).test(mapOf("one" to 2))

        assertFalse(result.valid)
        assertEquals("expected {one=2} to contain value 1", result.lazyMessage())
    }

    @Test fun `test contain valid`() {
        val result = precondition.contain(1, "one").test(mapOf(1 to "one"))

        assertTrue(result.valid)
    }

    @Test fun `test contain invalid`() {
        val result = precondition.contain(1, "two").test(mapOf(1 to "one"))

        assertEquals("expected {1=one} to contain 1=two", result.lazyMessage())
    }

}
