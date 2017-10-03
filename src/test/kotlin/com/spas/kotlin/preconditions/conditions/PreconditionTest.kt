package com.spas.kotlin.preconditions.conditions

import org.junit.Test
import kotlin.test.assertEquals


class PreconditionTest {

    val precondition = object : Precondition<String> {
        override fun test(value: String): Result = Result(true, value)
    }

    @Test fun `test test result`() {
        val result = precondition.test("hello")
        val expectedResult = Result(true, "hello")

        assertEquals(result, expectedResult)
    }

}
