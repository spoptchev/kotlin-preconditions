package com.spas.kotlin.preconditions.conditions

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class TypePreconditionsTest {

    private val precondition = object : TypePreconditions {}

    @Test fun `test beNull valid`() {
        val result = precondition.beNull<String?>().test(null)

        assertTrue(result.valid)
    }

    @Test fun `test beNull invalid`() {
        val result = precondition.beNull<String?>().test("test")

        assertFalse(result.valid)
        assertEquals("expected test to be null", result.lazyMessage())
    }

    @Test fun `test equal valid`() {
        val result = precondition.equal("x").test("x")

        assertTrue(result.valid)
    }

    @Test fun `test equal invalid`() {
        val result = precondition.equal("x").test("y")

        assertFalse(result.valid)
        assertEquals("expected y to be equal to x", result.lazyMessage())
    }

    @Test fun `test sameInstanceAs valid`() {
        val result = precondition.sameInstanceAs("x").test("x")

        assertTrue(result.valid)
    }

    @Test fun `test sameInstanceAs invalid`() {
        val result = precondition.sameInstanceAs("y").test("x")

        assertFalse(result.valid)
        assertEquals("expected x to have the same reference as y", result.lazyMessage())
    }

}
