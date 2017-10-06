package com.spas.kotlin.preconditions

import org.junit.Test
import kotlin.test.assertEquals


class PreconditionDSLTest {

    @Test(expected = IllegalStateException::class) fun `test to`() {
        check {
            "hello" to match("olleh")
        }
    }

    @Test(expected = IllegalArgumentException::class) fun `test toBe`() {
        require {
            1 toBe lt(0)
            1 toBe between(1..2)
        }
    }

    @Test fun `test notTo`() {
        try {
            check { "x" notTo equal("y") }
        } catch (e: IllegalStateException) {
            assertEquals("expected x not to equal y", e.message)
        }
    }

    @Test fun `test notToBe`() {
        try {
            require { 1 notToBe gt(0) }
        } catch (e: IllegalArgumentException) {
            assertEquals("expected 1 not to be > 0", e.message)
        }
    }

    @Test fun `test success`() {
        var result: Int = 0

        require {
            result = 1 toBe lt(2)
        }

        assertEquals(result, 1)
    }

    @Test fun `test integration of all collection preconditions`() {
        val list = listOf(1, 2)

        require {
            list to haveSize(2)
            list to contain(2)
            list notToBe empty()
            list to containAll(1, 2)
            list toBe sorted()
        }
    }

    @Test fun `test integration of all comparable preconditions`() {
        require {
            1 toBe lt(2)
            1 toBe lte(1)
            1 toBe gt(0)
            1 toBe gte(1)
            1 toBe between(0..2)
        }
    }

    @Test fun `test integration of all map preconditions`() {
        val map = mapOf(1 to "1")

        require {
            map to haveKey(1)
            map to haveValue("1")
            map to contain(1, "1")
        }
    }

    @Test fun `test integration of all string preconditions`() {
        val value = "hello"

        require {
            value to startWith("he")
            value to include("ll")
            value to match("hello")
            value to endWith("lo")
            value to haveLength(5)
        }
    }

    @Test fun `test integration of all type preconditions`() {
        val value = "hello"

        require {
            value notTo beNull()
            value to equal("hello")
            value toBe sameInstanceAs("hello")
        }
    }

}
