package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.Condition
import com.github.spoptchev.kotlin.preconditions.Matcher
import com.github.spoptchev.kotlin.preconditions.PreconditionBlock


fun <T> PreconditionBlock<T>.isLt(other: T) = isLessThan(other)
fun <T> PreconditionBlock<T>.isLessThan(other: T) = object : Matcher<Comparable<T>>() {
    override fun test(condition: Condition<Comparable<T>>) = condition.test {
        withResult(value < other) { "$expectedTo be < $other" }
    }
}

fun <T> PreconditionBlock<T>.isLte(other: T) = isLessThanOrEqualTo(other)
fun <T> PreconditionBlock<T>.isLessThanOrEqualTo(other: T) = object : Matcher<Comparable<T>>() {
    override fun test(condition: Condition<Comparable<T>>) = condition.test {
        withResult(value <= other) { "$expectedTo be <= $other" }
    }
}

fun <T> PreconditionBlock<T>.isGt(other: T) = isGreaterThan(other)
fun <T> PreconditionBlock<T>.isGreaterThan(other: T) = object : Matcher<Comparable<T>>() {
    override fun test(condition: Condition<Comparable<T>>) = condition.test {
        withResult(value > other) { "$expectedTo be > $other" }
    }
}

fun <T> PreconditionBlock<T>.isGte(other: T) = isGreaterThanOrEqualTo(other)
fun <T> PreconditionBlock<T>.isGreaterThanOrEqualTo(other: T) = object : Matcher<Comparable<T>>() {
    override fun test(condition: Condition<Comparable<T>>) = condition.test {
        withResult(value >= other) { "$expectedTo be >= $other" }
    }
}

fun <T : Comparable<T>> PreconditionBlock<T>.isBetween(range: ClosedRange<T>) = object : Matcher<T>() {
    override fun test(condition: Condition<T>) = condition.test {
        withResult(value in range) { "$expectedTo be in $range" }
    }
}

