package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.condition
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class CollectionMatcherTest {

    private val matcher = object : CollectionMatcher {}

    @Test fun `test hasSize valid`() {
        val result = matcher
                .hasSize<String>(2)
                .test(condition(listOf("1", "2")))

        assertTrue(result.valid)
    }


    @Test fun `test hasSize invalid`() {
        val result = matcher
                .hasSize<String>(3)
                .test(condition(listOf("1", "2")))

        assertFalse(result.valid)
        assertEquals("expected value [1, 2] to have size 3 but has size 2", result.lazyMessage())
    }

    @Test fun `test contains valid`() {
        val result = matcher
                .contains("1")
                .test(condition(listOf("1", "2")))

        assertTrue(result.valid)
    }

    @Test fun `test contains invalid`() {
        val result = matcher
                .contains("3")
                .test(condition(listOf("1", "2")))

        assertFalse(result.valid)
        assertEquals("expected value [1, 2] to contain element 3", result.lazyMessage())
    }

    @Test fun `test isEmpty valid`() {
        val result = matcher
                .isEmpty<String>()
                .test(condition(emptyList()))

        assertTrue(result.valid)
    }

    @Test fun `test isEmpty invalid`() {
        val result = matcher
                .isEmpty<String>()
                .test(condition(listOf("1", "2")))

        assertFalse(result.valid)
        assertEquals("expected value [1, 2] to be empty", result.lazyMessage())
    }

    @Test fun `test containsAll valid`() {
        val result = matcher
                .containsAll("1", "2")
                .test(condition(listOf("2", "1")))

        assertTrue(result.valid)
    }

    @Test fun `test containsAll invalid`() {
        val result = matcher
                .containsAll("1", "2", "4")
                .test(condition(listOf("3", "2", "1")))

        assertFalse(result.valid)
        assertEquals("expected value [3, 2, 1] to contain all values of [1, 2, 4]", result.lazyMessage())
    }

    @Test fun `test isSorted valid`() {
        val result = matcher
                .isSorted<Int>()
                .test(condition(listOf(1, 2, 3)))

        assertTrue(result.valid)
    }

    @Test fun `test isSorted invalid`() {
        val result = matcher
                .isSorted<Int>()
                .test(condition(listOf(1, 3, 2)))

        assertFalse(result.valid)
        assertEquals("expected value [1, 3, 2] to be sorted", result.lazyMessage())
    }

}
