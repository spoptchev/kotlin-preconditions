package com.github.spoptchev.kotlin.preconditions

import org.junit.Test
import kotlin.test.assertEquals


class PreconditionDSLTest {

    @Test(expected = IllegalStateException::class)
    fun `test should`() {

        check {
            "hello" should match("olleh")
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test shouldBe`() {
        require {
            1 shouldBe lt(0)
            1 shouldBe between(1..2)
        }
    }

    @Test fun `test shouldNot`() {
        try {
            check {
                withLabel("ShouldNot") { "x" shouldNot equal("y") }
            }
        } catch (e: IllegalStateException) {
            assertEquals("expected ShouldNot with value(s) x not to be equal to y", e.message)
        }
    }

    @Test fun `test shouldNotBe`() {
        try {
            require { 1 shouldNotBe gt(0) }
        } catch (e: IllegalArgumentException) {
            assertEquals("expected 1 not to be > 0", e.message)
        }
    }

    @Test fun `test success`() {
        var result: Int = 0

        require {
            result = 1 shouldBe not(lt(0)).or(not(lt(1)))
        }

        assertEquals(result, 1)
    }

    @Test fun `test integration of all collection preconditions`() {
        val list = listOf(1, 2)

        require {
            list should haveSize(2)
            list should contain(1).or(contain(1)).and(not(haveSize(3)))
            list should containAll(1, 2)
            list shouldBe sorted()
        }
    }

    @Test fun `test integration of all comparable preconditions`() {
        require {
            1 shouldBe lt(2)
            1 shouldBe lte(1)
            1 shouldBe gt(0)
            1 shouldBe gte(1)
            1 shouldBe between(0..2)
        }
    }

    @Test fun `test integration of all map preconditions`() {
        val map = mapOf(1 to "1")

        require {
            map should haveKey(1)
            map should haveValue("1")
            map should contain(1, "1")
        }
    }

    @Test fun `test integration of all string preconditions`() {
        val value = "hello"

        require {
            value should startWith("he").and(haveLength(5)).and(not(include("io")))
            value should include("ll")
            value should match("hello")
            value should endWith("lo")
            value should haveLength(5)
        }
    }

    @Test fun `test integration of all type preconditions`() {
        val value = "hello"

        require {
            value shouldNot beNull()
            value shouldBe equal("hello")
            value shouldBe sameInstanceAs("hello")
        }
    }

}
