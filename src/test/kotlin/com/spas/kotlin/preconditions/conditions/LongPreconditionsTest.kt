package com.spas.kotlin.preconditions.conditions

import org.junit.Test
import kotlin.test.assertEquals

class LongPreconditionsTest {

    private val precondition = object : LongPreconditions {}

    @Test
    fun `test lt valid`() {
        val result = precondition.lt(2L).test(1L)
        val expectedResult = Result(true, "expected 1 to be < 2")

        assertEquals(result, expectedResult)
    }

    @Test
    fun `test lt invalid`() {
        val result = precondition.lt(1L).test(2L)
        val expectedResult = Result(false, "expected 2 to be < 1")

        assertEquals(result, expectedResult)
    }

    @Test
    fun `test lte valid`() {
        val result = precondition.lte(2L).test(2L)
        val expectedResult = Result(true, "expected 2 to be <= 2")

        assertEquals(result, expectedResult)
    }

    @Test
    fun `test lte invalid`() {
        val result = precondition.lte(2L).test(3L)
        val expectedResult = Result(false, "expected 3 to be <= 2")

        assertEquals(result, expectedResult)
    }

    @Test
    fun `test gt valid`() {
        val result = precondition.gt(1L).test(2L)
        val expectedResult = Result(true, "expected 2 to be > 1")

        assertEquals(result, expectedResult)
    }

    @Test
    fun `test gt invalid`() {
        val result = precondition.gt(2L).test(1L)
        val expectedResult = Result(false, "expected 1 to be > 2")

        assertEquals(result, expectedResult)
    }

    @Test
    fun `test gte valid`() {
        val result = precondition.gte(2L).test(2L)
        val expectedResult = Result(true, "expected 2 to be >= 2")

        assertEquals(result, expectedResult)
    }

    @Test
    fun `test gte invalid`() {
        val result = precondition.gte(2L).test(1L)
        val expectedResult = Result(false, "expected 1 to be >= 2")

        assertEquals(result, expectedResult)
    }

    @Test
    fun `test between valid`() {
        val result = precondition.between(1L, 3L).test(2L)
        val expectedResult = Result(true, "expected 2 to be between (1, 3)")

        assertEquals(result, expectedResult)
    }

    @Test
    fun `test between invalid`() {
        val result = precondition.between(1L, 3L).test(4L)
        val expectedResult = Result(false, "expected 4 to be between (1, 3)")

        assertEquals(result, expectedResult)
    }

}
