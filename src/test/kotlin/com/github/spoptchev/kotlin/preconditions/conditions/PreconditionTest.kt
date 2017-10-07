package com.github.spoptchev.kotlin.preconditions.conditions

import com.github.spoptchev.kotlin.preconditions.conditions.Precondition
import com.github.spoptchev.kotlin.preconditions.conditions.Result
import com.github.spoptchev.kotlin.preconditions.conditions.verify
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
