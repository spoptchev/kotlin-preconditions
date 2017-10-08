package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.Matcher


interface ComparableMatcher {

    fun <T> lt(other: T) = lessThan(other)
    fun <T> lessThan(other: T) = object : Matcher<Comparable<T>>() {
        override fun test(value: Comparable<T>) = verify(value < other) {
            "expected $value to be < $other"
        }
    }

    fun <T> lte(other: T) = lessThanOrEqualTo(other)
    fun <T> lessThanOrEqualTo(other: T) = object : Matcher<Comparable<T>>() {
        override fun test(value: Comparable<T>) = verify(value <= other) {
            "expected $value to be <= $other"
        }
    }

    fun <T> gt(other: T) = greaterThan(other)
    fun <T> greaterThan(other: T) = object : Matcher<Comparable<T>>() {
        override fun test(value: Comparable<T>) = verify(value > other) {
            "expected $value to be > $other"
        }
    }

    fun <T> gte(other: T) = greaterThanOrEqualTo(other)
    fun <T> greaterThanOrEqualTo(other: T) = object : Matcher<Comparable<T>>() {
        override fun test(value: Comparable<T>) = verify(value >= other) {
            "expected $value to be >= $other"
        }
    }

    fun <T : Comparable<T>> between(range: ClosedRange<T>) = object : Matcher<T>() {
        override fun test(value: T) = verify(value in range) {
            "expected $value to be in $range"
        }
    }

}
