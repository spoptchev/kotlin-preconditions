package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.Condition
import com.github.spoptchev.kotlin.preconditions.Matcher


interface CollectionMatcher {

    fun <T> haveSize(size: Int) = object : Matcher<Collection<T>>() {
        override fun test(condition: Condition<Collection<T>>) = condition.test {
            withResult(value.size == size) { "$expectedTo have size $size but has size ${value.size}"}
        }
    }

    fun <T> contain(element: T) = object : Matcher<Collection<T>>() {
        override fun test(condition: Condition<Collection<T>>) = condition.test {
            withResult(value.contains(element)) { "$expectedTo contain element $element" }
        }
    }

    fun <T> empty() = object : Matcher<Collection<T>>() {
        override fun test(condition: Condition<Collection<T>>) = condition.test {
            withResult(value.isEmpty()) { "$expectedTo be empty" }
        }
    }

    fun <T> containAll(vararg values: T) = containAll(values.asList())
    fun <T> containAll(values: Collection<T>) = object : Matcher<Collection<T>>() {
        override fun test(condition: Condition<Collection<T>>) = condition.test {
            withResult(values.all(value::contains)) { "$expectedTo contain all values of $values" }
        }
    }

    fun <T : Comparable<T>> sorted() = object : Matcher<Collection<T>>() {
        override fun test(condition: Condition<Collection<T>>) = condition.test {
            withResult(value.sorted() == value) { "$expectedTo be sorted" }
        }
    }

}
