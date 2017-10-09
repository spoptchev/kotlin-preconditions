package com.github.spoptchev.kotlin.preconditions

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals


class ResultTest {


    @Test fun `test negate`() {
        val result = Result(true) { "expected to" }.negate()

        assertFalse(result.valid)
        assertEquals("expected not to", result.lazyMessage())
    }

    @Test fun `test label with empty text and invalid result`() {
        val expected = Result(false) { "expected to" }
        val result = expected.label(null)

        assertEquals(expected, result)
    }

    @Test fun `test label with text and valid result`() {
        val expected = Result(true) { "expected to" }
        val result = expected.label("test")

        assertEquals(expected, result)
    }

    @Test fun `test label with text and invalid result`() {
        val expected = Result(false) { "expected to" }
        val result = expected.label("test")

        assertNotEquals(expected, result)
        assertFalse(result.valid)
        assertEquals("expected test with value(s) to", result.lazyMessage())
    }

}
