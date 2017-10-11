package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.Condition
import com.github.spoptchev.kotlin.preconditions.Matcher


interface ObjectMatcher {

    fun <T> beNull() = object : Matcher<T>() {
        override fun test(condition: Condition<T>) = condition.test {
            withResult(value == null) { "$expectedTo be null" }
        }
    }

    fun <T> equal(other: T) = object : Matcher<Any>() {
        override fun test(condition: Condition<Any>) = condition.test {
            withResult(value == other) { "$expectedTo be equal to $other" }
        }
    }

    fun <T> sameInstanceAs(reference: T) = object : Matcher<T>() {
        override fun test(condition: Condition<T>) = condition.test {
            withResult(value === reference) { "$expectedTo have the same reference as $reference" }
        }
    }

}
