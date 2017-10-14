package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.condition
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class StringMatcherTest {

    private val matcher = object : StringMatcher {}

    @Test fun `test matches valid`() {
        val result = matcher.matches("hello").test(condition("hello"))

        assertTrue(result.valid)
    }

    @Test fun `test matches invalid`() {
        val result = matcher.matches("invalid").test(condition("something"))

        assertFalse(result.valid)
        assertEquals("expected value something to match 'invalid'", result.lazyMessage())
    }

    @Test fun `test startsWithIgnoreCase valid`() {
        val result = matcher.startsWithIgnoreCase("He").test(condition("hello"))

        assertTrue(result.valid)
    }

    @Test fun `test startsWithIgnoreCase invalid`() {
        val result = matcher.startsWithIgnoreCase("Eh").test(condition("hello"))

        assertFalse(result.valid)
        assertEquals("expected value hello to start with 'Eh'", result.lazyMessage())
    }

    @Test fun `test startsWith valid`() {
        val result = matcher.startsWith("HE").test(condition("HEllo"))

        assertTrue(result.valid)
    }

    @Test fun `test startsWith invalid`() {
        val result = matcher.startsWith("EH").test(condition("HEllo"))

        assertFalse(result.valid)
        assertEquals("expected value HEllo to start with 'EH'", result.lazyMessage())
    }

    @Test fun `test includesIgnoreCase valid`() {
        val result = matcher.includesIgnoreCase("EL").test(condition("hello"))

        assertTrue(result.valid)
    }

    @Test fun `test includesIgnoreCase invalid`() {
        val result = matcher.includesIgnoreCase("LE").test(condition("hello"))

        assertFalse(result.valid)
        assertEquals("expected value hello to include 'LE'", result.lazyMessage())
    }

    @Test fun `test includes valid`() {
        val result = matcher.includes("el").test(condition("hello"))

        assertTrue(result.valid)
    }

    @Test fun `test includes invalid`() {
        val result = matcher.includes("le").test(condition("hello"))

        assertFalse(result.valid)
        assertEquals("expected value hello to include 'le'", result.lazyMessage())
    }

    @Test fun `test endsWithIgnoreCase valid`() {
        val result = matcher.endsWithIgnoreCase("Lo").test(condition("helLO"))

        assertTrue(result.valid)
    }

    @Test fun `test endsWithIgnoreCase invalid`() {
        val result = matcher.endsWithIgnoreCase("Ol").test(condition("helLO"))

        assertFalse(result.valid)
        assertEquals("expected value helLO to end with 'Ol'", result.lazyMessage())
    }

    @Test fun `test endsWith valid`() {
        val result = matcher.endsWith("Lo").test(condition("helLo"))

        assertTrue(result.valid)
    }

    @Test fun `test endsWith invalid`() {
        val result = matcher.endsWith("Ol").test(condition("helLo"))

        assertFalse(result.valid)
        assertEquals("expected value helLo to end with 'Ol'", result.lazyMessage())
    }

    @Test fun `test isEqualToIgnoreCase valid`() {
        val result = matcher.isEqualToIgnoreCase("HELLO").test(condition("helLO"))

        assertTrue(result.valid)
    }

    @Test fun `test isEqualToIgnoreCase invalid`() {
        val result = matcher.isEqualToIgnoreCase("EHLLO").test(condition("helLO"))

        assertFalse(result.valid)
        assertEquals("expected value helLO to be equal to 'EHLLO'", result.lazyMessage())
    }

    @Test fun `test isEqualTo valid`() {
        val result = matcher.isEqualTo("helLo").test(condition("helLo"))

        assertTrue(result.valid)
    }

    @Test fun `test isEqualTo invalid`() {
        val result = matcher.isEqualTo("hello").test(condition("helLo"))

        assertFalse(result.valid)
        assertEquals("expected value helLo to be equal to 'hello'", result.lazyMessage())
    }

    @Test fun `test hasLength valid`() {
        val result = matcher.hasLength(5).test(condition("hello"))

        assertTrue(result.valid)
    }

    @Test fun `test hasLength invalid`() {
        val result = matcher.hasLength(2).test(condition("hello"))

        assertEquals("expected value hello to have length 2", result.lazyMessage())
    }

    @Test fun `test isBlank valid`() {
        val result = matcher.isBlank().test(condition(""))

        assertTrue(result.valid)
    }

    @Test fun `test isBlank valid with null`() {
        val string: String? = null
        val result = matcher.isBlank().test(condition(string))

        assertTrue(result.valid)
    }

    @Test fun `test isBlank invalid`() {
        val result = matcher.isBlank().test(condition("hello"))

        assertEquals("expected value hello to be blank", result.lazyMessage())
    }

    @Test fun `test isEmptyString valid`() {
        val result = matcher.isEmptyString().test(condition(""))

        assertTrue(result.valid)
    }

    @Test fun `test isEmptyString invalid`() {
        val result = matcher.isEmptyString().test(condition("hello"))

        assertEquals("expected value hello to be empty", result.lazyMessage())
    }

}
