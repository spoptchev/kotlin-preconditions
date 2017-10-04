package com.spas.kotlin.preconditions.conditions

import org.junit.Test
import kotlin.test.assertEquals


class ComparablePreconditionsTest {

    private val precondition = object : ComparablePreconditions {}

    @Test
    fun `test lt valid`() {
        val result = precondition.lt(2.0).test(1.0)
        val expectedResult = Result(true, "expected 1.0 to be < 2.0")

        assertEquals(result, expectedResult)
    }

    @Test
    fun `test lt invalid`() {
        val result = precondition.lt(1.0).test(2.0)
        val expectedResult = Result(false, "expected 2.0 to be < 1.0")

        assertEquals(result, expectedResult)
    }

    @Test
    fun `test lte valid`() {
        val result = precondition.lte(2.0).test(2.0)
        val expectedResult = Result(true, "expected 2.0 to be <= 2.0")

        assertEquals(result, expectedResult)
    }

    @Test
    fun `test lte invalid`() {
        val result = precondition.lte(2.0).test(3.0)
        val expectedResult = Result(false, "expected 3.0 to be <= 2.0")

        assertEquals(result, expectedResult)
    }

    @Test
    fun `test gt valid`() {
        val result = precondition.gt(1.0).test(2.0)
        val expectedResult = Result(true, "expected 2.0 to be > 1.0")

        assertEquals(result, expectedResult)
    }

    @Test
    fun `test gt invalid`() {
        val result = precondition.gt(2.0).test(1.0)
        val expectedResult = Result(false, "expected 1.0 to be > 2.0")

        assertEquals(result, expectedResult)
    }

    @Test
    fun `test gte valid`() {
        val result = precondition.gte(2.0).test(2.0)
        val expectedResult = Result(true, "expected 2.0 to be >= 2.0")

        assertEquals(result, expectedResult)
    }

    @Test
    fun `test gte invalid`() {
        val result = precondition.gte(2.0).test(1.0)
        val expectedResult = Result(false, "expected 1.0 to be >= 2.0")

        assertEquals(result, expectedResult)
    }

}
