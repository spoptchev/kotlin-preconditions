package com.spas.kotlin.preconditions.conditions

import org.junit.Test
import kotlin.test.assertEquals


class IntPreconditionsTest {

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

    @Test fun `test lte valid`() {
        val result = precondition.lte(2).test(2)
        val expectedResult = Result(true, "expected 2 to be <= 2")

        assertEquals(result, expectedResult)
    }

    @Test fun `test lte invalid`() {
        val result = precondition.lte(2).test(3)
        val expectedResult = Result(false, "expected 3 to be <= 2")

        assertEquals(result, expectedResult)
    }

    @Test fun `test gt valid`() {
        val result = precondition.gt(1).test(2)
        val expectedResult = Result(true, "expected 2 to be > 1")

        assertEquals(result, expectedResult)
    }

    @Test fun `test gt invalid`() {
        val result = precondition.gt(2).test(1)
        val expectedResult = Result(false, "expected 1 to be > 2")

        assertEquals(result, expectedResult)
    }

    @Test fun `test gte valid`() {
        val result = precondition.gte(2).test(2)
        val expectedResult = Result(true, "expected 2 to be >= 2")

        assertEquals(result, expectedResult)
    }

    @Test fun `test gte invalid`() {
        val result = precondition.gte(2).test(1)
        val expectedResult = Result(false, "expected 1 to be >= 2")

        assertEquals(result, expectedResult)
    }

    @Test fun `test between valid`() {
        val result = precondition.between(1, 3).test(2)
        val expectedResult = Result(true, "expected 2 to be between (1, 3)")

        assertEquals(result, expectedResult)
    }

    @Test fun `test between invalid`() {
        val result = precondition.between(1, 3).test(4)
        val expectedResult = Result(false, "expected 4 to be between (1, 3)")

        assertEquals(result, expectedResult)
    }

}
