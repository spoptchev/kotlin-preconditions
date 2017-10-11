package com.github.spoptchev.kotlin.preconditions

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class ConditionTest {

    @Test fun `test expectedTo with negated = true`() {
        val condition = condition(1, negated = true)

        assertEquals("expected value 1 not to", condition.expectedTo)
    }

    @Test fun `test expectedTo with negated = false`() {
        val condition = condition(1, negated = false)

        assertEquals("expected value 1 to", condition.expectedTo)
    }

    @Test fun `test withResult with negated = true and valid result`() {
        val result = condition(1, negated = true).withResult(true) { "some message" }

        assertFalse(result.valid)
    }

    @Test fun `test withResult with negated = true and invalid result`() {
        val result = condition(1, negated = true).withResult(false) { "some message" }

        assertTrue(result.valid)
    }

    @Test fun `test withResult with negated = false and valid result`() {
        val result = condition(1, negated = false).withResult(true) { "some message" }

        assertTrue(result.valid)
    }

    @Test fun `test withResult with negated = false and invalid result`() {
        val result = condition(1, negated = false).withResult(false) { "some message" }

        assertFalse(result.valid)
    }

}
