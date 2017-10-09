package com.github.spoptchev.kotlin.preconditions

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.fail


class PreconditionDSLTest {

    @Test(expected = IllegalStateException::class)
    fun `test invalid check`() {
        check("hallo") { match("ollex") }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test invalid require`() {
        require(1) { toBe(lt(0)) }
        fail("should not be executed")
    }

    @Test fun `test check invalid with label`() {
        try {
            check("x", "ShouldNot") { notToBe(equal("x")) }
            fail("should not be executed")
        } catch (e: IllegalStateException) {
            assertEquals("expected ShouldNot with value(s) x not to be equal to x", e.message)
        }
    }

    @Test fun `test labeled valid evaluation`() {
        check("y", "Should") { to(equal("y")) }
    }

    @Test fun `test shouldNotBe`() {
        try {
            require(1) { gt(0) }
        } catch (e: IllegalArgumentException) {
            assertEquals("expected 1 not to be > 0", e.message)
        }
    }

    @Test fun `test result value`() {
        val result: Int = require(1) { gt(0) }

        assertEquals(result, 1)
    }

    @Test fun `test integration of all collection preconditions`() {
        val list = listOf(1, 2)

        require(list) { haveSize(2) }
        require(list) { contain(1) or contain(3) and not(haveSize(3)) }
        require(list) { containAll(1, 2) }
        require(list) { containAll(list) }
        require(list) { sorted() }
    }

    @Test fun `test integration of all comparable preconditions`() {
        require(1) { lt(2) }
        require(1) { lte(1) }
        require(1) { gt(0) }
        require(1) { gte(1) }
        require(1) { between(0..2) }
    }

    @Test fun `test integration of all map preconditions`() {
        val map = mapOf(1 to "1")

        require(map) { to(haveKey(1)) }
        require(map) { haveValue("1") }
        require(map) { contain(1, "1") }
    }

    @Test fun `test integration of all string preconditions`() {
        val value = "hello"

        require(value) { startWith("he") and haveLength(5) and not(include("io")) }
        require(value) { include("ll") }
        require(value) { match("hello") }
        require(value) { endWith("lo") }
        require(value) { haveLength(5) }
    }

    @Test fun `test integration of all type preconditions`() {
        val value = "hello"

        require(value) { not(beNull()) }
        require(value) { equal("hello") }
        require(value) { sameInstanceAs("hello") }
    }

}
