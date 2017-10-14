package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.Condition
import com.github.spoptchev.kotlin.preconditions.Matcher


interface ObjectMatcher {

    fun <T> isNull() = object : Matcher<T>() {
        override fun test(condition: Condition<T>) = condition.test {
            withResult(value == null) { "$expectedTo be null" }
        }
    }

    fun <T> isEqualTo(other: T) = object : Matcher<Any>() {
        override fun test(condition: Condition<Any>) = condition.test {
            withResult(value == other) { "$expectedTo be equal to $other" }
        }
    }

    fun <T> isSameInstanceAs(reference: T) = object : Matcher<T>() {
        override fun test(condition: Condition<T>) = condition.test {
            withResult(value === reference) { "$expectedTo have the same reference as $reference" }
        }
    }

}
