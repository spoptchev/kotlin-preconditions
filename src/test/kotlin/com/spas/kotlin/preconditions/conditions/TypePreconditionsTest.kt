package com.spas.kotlin.preconditions.conditions

import org.junit.Test
import kotlin.test.assertEquals


class TypePreconditionsTest {

    private val precondition = object : TypePreconditions {}

    @Test fun `test sameInstanceAs valid`() {
        val result = precondition.sameInstanceAs("x").test("x")
        val expectedResult = Result(true, "expected x to have the same reference as x")

        assertEquals(result, expectedResult)
    }

    @Test fun `test sameInstanceAs invalid`() {
        val result = precondition.sameInstanceAs("y").test("x")
        val expectedResult = Result(false, "expected x to have the same reference as y")

        assertEquals(result, expectedResult)
    }

}
