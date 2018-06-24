package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.requireThat
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails


class StringMatcherTest {

    @Test
    fun `test matches valid`() {
        requireThat("hello") { matches("hello") }
    }

    @Test
    fun `test matches invalid`() {
        val exception = assertFails {
            requireThat("something") { matches("invalid") }
        }

        assertEquals("expected value something to match 'invalid'", exception.message)
    }

    @Test
    fun `test startsWithIgnoreCase valid`() {
        requireThat("hello") { startsWithIgnoreCase("He") }
    }

    @Test
    fun `test startsWithIgnoreCase invalid`() {
        val exception = assertFails {
            requireThat("hello") { startsWithIgnoreCase("Eh") }
        }

        assertEquals("expected value hello to start with 'Eh'", exception.message)
    }

    @Test
    fun `test startsWith valid`() {
        requireThat("HEllo") { startsWith("HE") }
    }

    @Test
    fun `test startsWith invalid`() {
        val exception = assertFails {
            requireThat("HEllo") { startsWith("EH") }
        }

        assertEquals("expected value HEllo to start with 'EH'", exception.message)
    }

    @Test
    fun `test includesIgnoreCase valid`() {
        requireThat("hello") { includesIgnoreCase("EL") }
    }

    @Test
    fun `test includesIgnoreCase invalid`() {
        val exception = assertFails {
            requireThat("hello") { includesIgnoreCase("LE") }
        }

        assertEquals("expected value hello to include 'LE'", exception.message)
    }

    @Test
    fun `test includes valid`() {
        requireThat("hello") { includes("el") }
    }

    @Test
    fun `test includes invalid`() {
        val exception = assertFails {
            requireThat("hello") { includes("le") }
        }

        assertEquals("expected value hello to include 'le'", exception.message)
    }

    @Test
    fun `test endsWithIgnoreCase valid`() {
        requireThat("helLo") { endsWithIgnoreCase("lo") }
    }

    @Test
    fun `test endsWithIgnoreCase invalid`() {
        val exception = assertFails {
            requireThat("helLO") { endsWithIgnoreCase("Ol") }
        }

        assertEquals("expected value helLO to end with 'Ol'", exception.message)
    }

    @Test
    fun `test endsWith valid`() {
        requireThat("helLo") { endsWith("Lo") }
    }

    @Test
    fun `test endsWith invalid`() {
        val exception = assertFails {
            requireThat("helLo") { endsWith("Ol") }
        }

        assertEquals("expected value helLo to end with 'Ol'", exception.message)
    }

    @Test
    fun `test isEqualToIgnoreCase valid`() {
        requireThat("helLo") { isEqualToIgnoreCase("HELLO") }
    }

    @Test
    fun `test isEqualToIgnoreCase invalid`() {
        val exception = assertFails {
            requireThat("helLO") { isEqualToIgnoreCase("EHLLO") }
        }

        assertEquals("expected value helLO to be equal to 'EHLLO'", exception.message)
    }

    @Test
    fun `test isEqualTo valid`() {
        requireThat("helLo") { isEqualTo("helLo") }
    }

    @Test
    fun `test isEqualTo invalid`() {
        val exception = assertFails {
            requireThat("helLo") { isEqualTo("hello") }
        }

        assertEquals("expected value helLo to be equal to 'hello'", exception.message)
    }

    @Test
    fun `test hasLength valid`() {
        requireThat("hello") { hasLength(5) }
    }

    @Test
    fun `test hasLength invalid`() {
        val exception = assertFails {
            requireThat("hello") { hasLength(2) }
        }

        assertEquals("expected value hello to have length 2", exception.message)
    }

    @Test
    fun `test hasLengthBetween within max range`() {
        requireThat("hello") { hasLengthBetween(4, 5) }
    }

    @Test
    fun `test hasLengthBetween within min range`() {
        requireThat("hello") { hasLengthBetween(5, 6) }
    }

    @Test
    fun `test hasLengthBetween exceeding max`() {
        val exception = assertFails {
            requireThat("hello") { hasLengthBetween(1, 4) }
        }

        assertEquals("expected value hello to have length between 1 and 4", exception.message)
    }

    @Test
    fun `test hasLengthBetween exceeding min`() {
        val exception = assertFails {
            requireThat("hello") { hasLengthBetween(6, 10) }
        }

        assertEquals("expected value hello to have length between 6 and 10", exception.message)
    }

    @Test
    fun `test isBlank valid`() {
        requireThat("") { isBlank() }
    }

    @Test
    fun `test isBlank valid with null`() {
        val string: String? = null
        requireThat(string) { isBlank() }
    }

    @Test
    fun `test isBlank invalid`() {
        val exception = assertFails {
            requireThat("hello") { isBlank() }
        }

        assertEquals("expected value hello to be blank", exception.message)
    }

    @Test
    fun `test isEmpty valid`() {
        requireThat("") { isEmpty() }
    }

    @Test
    fun `test isEmpty invalid`() {
        val exception = assertFails {
            requireThat("hello") { isEmpty() }
        }

        assertEquals("expected value hello to be empty", exception.message)
    }

}
