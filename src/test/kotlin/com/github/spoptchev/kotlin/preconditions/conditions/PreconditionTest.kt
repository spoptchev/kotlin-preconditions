package com.github.spoptchev.kotlin.preconditions.conditions

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class PreconditionTest {

    val precondition = object : Precondition<String> {
        override fun test(value: String): Result = verify(true) { value }
    }

    @Test fun `test test result`() {
        val result = precondition.test("hello")

        assertTrue(result.valid)
        assertEquals("hello", result.lazyMessage())
    }

}
