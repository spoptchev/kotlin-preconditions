package com.github.spoptchev.kotlin.preconditions

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.fail


class AssertionTest {

    private val boolMatcher = object : Matcher<Boolean>() {
        override fun test(condition: Condition<Boolean>): Result = Result(condition.value) { "message" }
    }

    @Test fun `test run with valid result`() {
        val assertion = Assertion(true, "value", ::require)
        val result = assertion.run(boolMatcher)

        assertEquals(true, result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test run with invalid result`() {
        val assertion = Assertion(false, "value", ::require)

        assertion.run(boolMatcher)

        fail("should not be executed")
    }

}
