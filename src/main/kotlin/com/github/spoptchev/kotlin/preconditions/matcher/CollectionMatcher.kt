package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.Condition
import com.github.spoptchev.kotlin.preconditions.Matcher
import com.github.spoptchev.kotlin.preconditions.PreconditionBlock


fun <T : Collection<*>> PreconditionBlock<T>.hasSize(size: Int) = object : Matcher<T>() {
    override fun test(condition: Condition<T>) = condition.test {
        withResult(value.size == size) { "$expectedTo have size $size but has size ${value.size}" }
    }
}

fun <E, T : Collection<E>> PreconditionBlock<T>.contains(element: E) = object : Matcher<T>() {
    override fun test(condition: Condition<T>) = condition.test {
        withResult(value.contains(element)) { "$expectedTo contain element $element" }
    }
}

fun <T : Collection<*>> PreconditionBlock<T>.isEmpty() = object : Matcher<Collection<*>>() {
    override fun test(condition: Condition<Collection<*>>) = condition.test {
        withResult(value.isEmpty()) { "$expectedTo be empty" }
    }
}

fun <E, T : Collection<E>> PreconditionBlock<T>.containsAll(vararg values: E) = object : Matcher<T>() {
    override fun test(condition: Condition<T>) = condition.test {
        withResult(values.all(value::contains)) { "$expectedTo contain all values of ${values.toList()}" }
    }
}

fun <T : Collection<*>> PreconditionBlock<T>.containsAll(values: T) = object : Matcher<T>() {
    override fun test(condition: Condition<T>) = condition.test {
        withResult(values.all(value::contains)) { "$expectedTo contain all values of $values" }
    }
}

fun <E : Comparable<E>, T : Collection<E>> PreconditionBlock<T>.isSorted() = object : Matcher<T>() {
    override fun test(condition: Condition<T>) = condition.test {
        withResult(value.sorted() == value) { "$expectedTo be sorted" }
    }
}
