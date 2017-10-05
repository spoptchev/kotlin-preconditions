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

}
