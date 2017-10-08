package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.Matcher


interface ObjectMatcher {

    fun <T> beNull() = object : Matcher<T>() {
        override fun test(value: T) = verify(value == null) {
            "expected $value to be null"
        }
    }

    fun <T> equal(other: T) = object : Matcher<Any>() {
        override fun test(value: Any) = verify(value == other) {
            "expected $value to be equal to $other"
        }
    }

    fun <T> sameInstanceAs(reference: T) = object : Matcher<T>() {
        override fun test(value: T) = verify(value === reference) {
            "expected $value to have the same reference as $reference"
        }
    }

}
