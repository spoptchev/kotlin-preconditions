package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.Matcher


interface ObjectMatcher {

    fun <T> beNull(label: String? = null) = object : Matcher<T>() {
        override fun test(value: T) = verify(value == null) {
            val expectation = when (label.isNullOrEmpty()) {
                true -> "$value"
                else -> "field '$label'"
            }

            "expected $expectation to be null"
        }
    }

    fun <T> equal(other: T) = object : Matcher<T>() {
        override fun test(value: T) = verify(value == other) {
            "expected $value to be equal to $other"
        }
    }

    fun <T> sameInstanceAs(reference: T) = object : Matcher<T>() {
        override fun test(value: T) = verify(value === reference) {
            "expected $value to have the same reference as $reference"
        }
    }

}
