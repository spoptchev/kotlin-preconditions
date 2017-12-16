package com.github.spoptchev.kotlin.preconditions

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.fail


class PreconditionDSLTest {

    @Test(expected = IllegalStateException::class)
    fun `test invalid check`() {
        checkThat("hello") { matches("olleh") }
        fail("should not be executed")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test invalid require`() {
        requireThat(1) { isLt(0) }
        fail("should not be executed")
    }

    @Test fun `test check invalid with label`() {
        try {
            checkThat("x", "ID") { not(isEqualTo("x")) }
            fail("should not be executed")
        } catch (e: IllegalStateException) {
            assertEquals("expected ID x not to be equal to 'x'", e.message)
        }
    }

    @Test fun `test labeled valid evaluation`() {
        checkThat("y", "Should") { isEqualTo("y") }
    }

    @Test fun `test shouldNotBe`() {
        try {
            requireThat(1) { isGt(0) }
        } catch (e: IllegalArgumentException) {
            assertEquals("expected 1 not to be > 0", e.message)
        }
    }

    @Test fun `test result value`() {
        val result: Int = requireThat(1) { isGt(0) }

        assertEquals(result, 1)
    }

    @Test fun `test integration of all collection preconditions`() {
        val list = listOf(1, 2)

        requireThat(list) { hasSize(2) }
        requireThat(list) { contains(1) or contains(3) and not(hasSize(3)) }
        requireThat(list) { containsAll(1, 2) }
        requireThat(list) { containsAll(list) }
        requireThat(list) { isSorted() }
    }

    @Test fun `test integration of all comparable preconditions`() {
        requireThat(1) { isLt(2) }
        requireThat(1) { isLte(1) }
        requireThat(1) { isGt(0) }
        requireThat(1) { isGte(1) }
        requireThat(1) { isBetween(0..2) }
    }

    @Test fun `test integration of all map preconditions`() {
        val map = mapOf(1 to "1")

        requireThat(map) { hasKey(1) }
        requireThat(map) { hasValue("1") }
        requireThat(map) { contains(1, "1") }
    }

    @Test fun `test integration of all string preconditions`() {
        val value = "hello"

        requireThat(value) { startsWith("he") and hasLength(5) and not(includes("io")) }
        requireThat(value) { includes("ll") }
        requireThat(value) { matches("hello") }
        requireThat(value) { endsWith("lo") }
        requireThat(value) { hasLength(5) }
        requireThat(value) { not(isBlank()) }
        requireThat(value) { not(isEmptyString()) }
        requireThat(value) { hasLengthBetween(1, 5) }
    }

    @Test fun `test integration of all object preconditions`() {
        val result = Result(true) { "" }

        requireThat(result) { not(isNull()) }
        requireThat(result) { isEqualTo(result) }
        requireThat(result) { isSameInstanceAs(result) }
    }

}
