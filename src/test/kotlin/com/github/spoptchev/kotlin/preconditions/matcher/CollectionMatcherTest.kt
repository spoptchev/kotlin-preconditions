package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.condition
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class CollectionMatcherTest {

    private val matcher = object : CollectionMatcher {}

    @Test fun `test have size valid`() {
        val result = matcher
                .haveSize<String>(2)
                .test(condition(listOf("1", "2")))

        assertTrue(result.valid)
    }


    @Test fun `test have size invalid`() {
        val result = matcher
                .haveSize<String>(3)
                .test(condition(listOf("1", "2")))

        assertFalse(result.valid)
        assertEquals("expected value [1, 2] to have size 3 but has size 2", result.lazyMessage())
    }

    @Test fun `test contain valid`() {
        val result = matcher
                .contain("1")
                .test(condition(listOf("1", "2")))

        assertTrue(result.valid)
    }

    @Test fun `test contain invalid`() {
        val result = matcher
                .contain("3")
                .test(condition(listOf("1", "2")))

        assertFalse(result.valid)
        assertEquals("expected value [1, 2] to contain element 3", result.lazyMessage())
    }

    @Test fun `test empty valid`() {
        val result = matcher
                .empty<String>()
                .test(condition(emptyList()))

        assertTrue(result.valid)
    }

    @Test fun `test empty invalid`() {
        val result = matcher
                .empty<String>()
                .test(condition(listOf("1", "2")))

        assertFalse(result.valid)
        assertEquals("expected value [1, 2] to be empty", result.lazyMessage())
    }

    @Test fun `test containAll valid`() {
        val result = matcher
                .containAll("1", "2")
                .test(condition(listOf("2", "1")))

        assertTrue(result.valid)
    }

    @Test fun `test containAll invalid`() {
        val result = matcher
                .containAll("1", "2", "4")
                .test(condition(listOf("3", "2", "1")))

        assertFalse(result.valid)
        assertEquals("expected value [3, 2, 1] to contain all values of [1, 2, 4]", result.lazyMessage())
    }

    @Test fun `test sorted valid`() {
        val result = matcher
                .sorted<Int>()
                .test(condition(listOf(1, 2, 3)))

        assertTrue(result.valid)
    }

    @Test fun `test sorted invalid`() {
        val result = matcher
                .sorted<Int>()
                .test(condition(listOf(1, 3, 2)))

        assertFalse(result.valid)
        assertEquals("expected value [1, 3, 2] to be sorted", result.lazyMessage())
    }

}
