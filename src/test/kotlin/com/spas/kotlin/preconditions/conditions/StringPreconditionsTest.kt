package com.spas.kotlin.preconditions.conditions

import org.junit.Test
import kotlin.test.assertEquals


class StringPreconditionsTest {

    private val precondition = object : StringPreconditions {}

    @Test
    fun `test match valid`() {
        val result = precondition.match("hello").test("hello")
        val expectedResult = Result(true, "expected hello to match hello")

        assertEquals(result, expectedResult)
    }

    @Test fun `test match invalid`() {
        val result = precondition.match("invalid").test("something")
        val expectedResult = Result(false, "expected invalid to match something")

        assertEquals(result, expectedResult)
    }

}
