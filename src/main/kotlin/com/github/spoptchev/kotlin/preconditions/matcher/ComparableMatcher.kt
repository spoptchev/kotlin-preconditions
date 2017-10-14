package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.Condition
import com.github.spoptchev.kotlin.preconditions.Matcher


interface ComparableMatcher {

    fun <T> isLt(other: T) = isLessThan(other)
    fun <T> isLessThan(other: T) = object : Matcher<Comparable<T>>() {
        override fun test(condition: Condition<Comparable<T>>) = condition.test {
            withResult(value < other) { "$expectedTo be < $other" }
        }
    }

    fun <T> isLte(other: T) = isLessThanOrEqualTo(other)
    fun <T> isLessThanOrEqualTo(other: T) = object : Matcher<Comparable<T>>() {
        override fun test(condition: Condition<Comparable<T>>) = condition.test {
            withResult(value <= other) { "$expectedTo be <= $other" }
        }
    }

    fun <T> isGt(other: T) = isGreaterThan(other)
    fun <T> isGreaterThan(other: T) = object : Matcher<Comparable<T>>() {
        override fun test(condition: Condition<Comparable<T>>) = condition.test {
            withResult(value > other) { "$expectedTo be > $other" }
        }
    }

    fun <T> isGte(other: T) = isGreaterThanOrEqualTo(other)
    fun <T> isGreaterThanOrEqualTo(other: T) = object : Matcher<Comparable<T>>() {
        override fun test(condition: Condition<Comparable<T>>) = condition.test {
            withResult(value >= other) { "$expectedTo be >= $other" }
        }
    }

    fun <T : Comparable<T>> isBetween(range: ClosedRange<T>) = object : Matcher<T>() {
        override fun test(condition: Condition<T>) = condition.test {
            withResult(value in range) { "$expectedTo be in $range" }
        }
    }

}
