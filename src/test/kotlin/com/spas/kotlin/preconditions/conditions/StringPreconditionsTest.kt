package com.spas.kotlin.preconditions.conditions

import org.junit.Test
import kotlin.test.assertEquals


class StringPreconditionsTest {

    private val precondition = object : StringPreconditions {}

    @Test fun `test match valid`() {
        val result = precondition.match("hello").test("hello")
        val expectedResult = Result(true, "expected 'hello' to match 'hello'")

        assertEquals(result, expectedResult)
    }

    @Test fun `test match invalid`() {
        val result = precondition.match("invalid").test("something")
        val expectedResult = Result(false, "expected 'something' to match 'invalid'")

        assertEquals(result, expectedResult)
    }

    @Test fun `test startWith valid`() {
        val result = precondition.startWith("he").test("hello")
        val expectedResult = Result(true, "expected 'hello' to start with 'he'")

        assertEquals(result, expectedResult)
    }

    @Test fun `test startWith invalid`() {
        val result = precondition.startWith("eh").test("hello")
        val expectedResult = Result(false, "expected 'hello' to start with 'eh'")

        assertEquals(result, expectedResult)
    }

    @Test fun `test include valid`() {
        val result = precondition.include("el").test("hello")
        val expectedResult = Result(true, "expected 'hello' to include 'el'")

        assertEquals(result, expectedResult)
    }

    @Test fun `test include invalid`() {
        val result = precondition.include("le").test("hello")
        val expectedResult = Result(false, "expected 'hello' to include 'le'")

        assertEquals(result, expectedResult)
    }

    @Test fun `test endWith valid`() {
        val result = precondition.endWith("lo").test("hello")
        val expectedResult = Result(true, "expected 'hello' to end with 'lo'")

        assertEquals(result, expectedResult)
    }

    @Test fun `test endWith invalid`() {
        val result = precondition.endWith("ol").test("hello")
        val expectedResult = Result(false, "expected 'hello' to end with 'ol'")

        assertEquals(result, expectedResult)
    }

    @Test fun `test haveLength valid`() {
        val result = precondition.haveLength(5).test("hello")
        val expectedResult = Result(true, "expected 'hello' to have length 5")

        assertEquals(result, expectedResult)
    }

    @Test fun `test haveLength invalid`() {
        val result = precondition.haveLength(2).test("hello")
        val expectedResult = Result(false, "expected 'hello' to have length 2")

        assertEquals(result, expectedResult)
    }

}
