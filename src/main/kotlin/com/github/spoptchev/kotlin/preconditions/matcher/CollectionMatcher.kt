package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.Matcher


interface CollectionMatcher {

    fun <T> haveSize(size: Int) = object : Matcher<Collection<T>>() {
        override fun test(value: Collection<T>) = verify(value.size == size) {
            "expected $value to have size $size but has size ${value.size}"
        }
    }

    fun <T> contain(element: T) = object : Matcher<Collection<T>>() {
        override fun test(value: Collection<T>) = verify(value.contains(element)) {
            "expected $value to contain element $element"
        }
    }

    fun <T> empty() = object : Matcher<Collection<T>>() {
        override fun test(value: Collection<T>) = verify(value.isEmpty()) {
            "expected $value to be empty"
        }
    }

    fun <T> containAll(vararg values: T) = containAll(values.asList())
    fun <T> containAll(values: List<T>) = object : Matcher<Collection<T>>() {
        override fun test(value: Collection<T>) = verify(values.all(value::contains)) {
            "expected $value to contain all values of $values"
        }
    }

    fun <T : Comparable<T>> sorted() = object : Matcher<List<T>>() {
        override fun test(value: List<T>) = verify(value.sorted() == value) {
            "expected $value to be sorted"
        }
    }

}
