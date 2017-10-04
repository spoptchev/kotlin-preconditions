package com.spas.kotlin.preconditions.conditions

import org.junit.Test
import kotlin.test.assertEquals


class CollectionPreconditionsTest {

    private val precondition = object : CollectionPreconditions {}

    @Test fun `test have size valid`() {
        val result = precondition.haveSize<String>(2).test(listOf("1", "2"))
        val expectedResult = Result(true, "expected collection to have size 2 but has size 2")

        assertEquals(result, expectedResult)
    }

    @Test fun `test have size invalid`() {
        val result = precondition.haveSize<String>(3).test(listOf("1", "2"))
        val expectedResult = Result(false, "expected collection to have size 3 but has size 2")

        assertEquals(result, expectedResult)
    }

    @Test fun `test contain valid`() {
        val result = precondition.contain("1").test(listOf("1", "2"))
        val expectedResult = Result(true, "expected collection [1, 2] to contain element 1")

        assertEquals(result, expectedResult)
    }

    @Test fun `test contain invalid`() {
        val result = precondition.contain("3").test(listOf("1", "2"))
        val expectedResult = Result(false, "expected collection [1, 2] to contain element 3")

        assertEquals(result, expectedResult)
    }

    @Test fun `test empty valid`() {
        val result = precondition.empty<String>().test(emptyList())
        val expectedResult = Result(true, "expected collection [] to be empty")

        assertEquals(result, expectedResult)
    }

    @Test fun `test empty invalid`() {
        val result = precondition.empty<String>().test(listOf("1", "2"))
        val expectedResult = Result(false, "expected collection [1, 2] to be empty")

        assertEquals(result, expectedResult)
    }

    @Test fun `test containAll valid`() {
        val result = precondition.containAll("1", "2").test(listOf("2", "1"))
        val expectedResult = Result(true, "expected collection [2, 1] to contain all values of [1, 2]")

        assertEquals(result, expectedResult)
    }

    @Test fun `test containAll invalid`() {
        val result = precondition.containAll("1", "2", "4").test(listOf("3", "2", "1"))
        val expectedResult = Result(false, "expected collection [3, 2, 1] to contain all values of [1, 2, 4]")

        assertEquals(result, expectedResult)
    }

    @Test fun `test sorted valid`() {
        val result = precondition.sorted<Int>().test(listOf(1, 2, 3))
        val expectedResult = Result(true, "expected collection [1, 2, 3] to be sorted")

        assertEquals(result, expectedResult)
    }

    @Test fun `test sorted invalid`() {
        val result = precondition.sorted<Int>().test(listOf(1, 3, 2))
        val expectedResult = Result(false, "expected collection [1, 3, 2] to be sorted")

        assertEquals(result, expectedResult)
    }

}
