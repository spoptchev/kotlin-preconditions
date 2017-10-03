package com.spas.kotlin.preconditions.conditions

import org.junit.Test
import kotlin.test.assertEquals


class IntConditionsTest {

    private val precondition = object : IntPreconditions {}

    @Test fun `test lt valid`() {
        val result = precondition.lt(2).test(1)
        val expectedResult = Result(true, "expected 1 to be < 2")

        assertEquals(result, expectedResult)
    }

    @Test fun `test lt invalid`() {
        val result = precondition.lt(1).test(2)
        val expectedResult = Result(false, "expected 2 to be < 1")

        assertEquals(result, expectedResult)
    }

}
