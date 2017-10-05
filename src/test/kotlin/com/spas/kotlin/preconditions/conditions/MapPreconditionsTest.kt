package com.spas.kotlin.preconditions.conditions

import org.junit.Test
import kotlin.test.assertEquals


class MapPreconditionsTest {

    private val precondition = object : MapPreconditions {}

    @Test fun `test haveKey valid`() {
        val result = precondition.haveKey(1).test(mapOf(1 to "one"))
        val expectedResult = Result(true, message = "expected {1=one} to contain key 1")

        assertEquals(result, expectedResult)
    }

    @Test fun `test haveKey invalid`() {
        val result = precondition.haveKey(1).test(mapOf(2 to "one"))
        val expectedResult = Result(false, message = "expected {2=one} to contain key 1")

        assertEquals(result, expectedResult)
    }

    @Test fun `test haveValue valid`() {
        val result = precondition.haveValue(1).test(mapOf("one" to 1))
        val expectedResult = Result(true, message = "expected {one=1} to contain value 1")

        assertEquals(result, expectedResult)
    }

    @Test fun `test haveValue invalid`() {
        val result = precondition.haveValue(1).test(mapOf("one" to 2))
        val expectedResult = Result(false, message = "expected {one=2} to contain value 1")

        assertEquals(result, expectedResult)
    }

    @Test fun `test contain valid`() {
        val result = precondition.contain(1, "one").test(mapOf(1 to "one"))
        val expectedResult = Result(true, message = "expected {1=one} to contain 1=one")

        assertEquals(result, expectedResult)
    }

    @Test fun `test contain invalid`() {
        val result = precondition.contain(1, "two").test(mapOf(1 to "one"))
        val expectedResult = Result(false, message = "expected {1=one} to contain 1=two")

        assertEquals(result, expectedResult)
    }

}
