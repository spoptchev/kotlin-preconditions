package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.Condition
import com.github.spoptchev.kotlin.preconditions.Matcher
import com.github.spoptchev.kotlin.preconditions.PreconditionBlock
import com.github.spoptchev.kotlin.preconditions.requireThat
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class CustomMatcherTest {

    @Test
    fun `test custom matcher valid`() {
        requireThat(Car(22)) { hasAge(22) }
    }

    @Test
    fun `test custom matcher invalid`() {
        val exception = assertFails {
            requireThat(Car(22), label = "car") { hasAge(23) }
        }

        assertEquals("expected car to be 23 years old, but was 22", exception.message)
    }

    data class Car(val age: Int)

}

fun PreconditionBlock<CustomMatcherTest.Car>.hasAge(expected: Int) = object : Matcher<CustomMatcherTest.Car>() {
    override fun test(condition: Condition<CustomMatcherTest.Car>) = condition.test {
        withResult(value.age == expected) { "expected car to be $expected years old, but was ${value.age}" }
    }
}
