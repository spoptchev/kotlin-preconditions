package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.Matcher


interface CollectionMatcher {

    fun <T> haveSize(size: Int) = object : Matcher<Collection<T>>() {
        override fun test(value: Collection<T>) = verify(value.size == size) {
            "expected collection to have size $size but has size ${value.size}"
        }
    }

    fun <T> contain(element: T) = object : Matcher<Collection<T>>() {
        override fun test(value: Collection<T>) = verify(value.contains(element)) {
            "expected collection $value to contain element $element"
        }
    }

    fun <T> empty() = object : Matcher<Collection<T>>() {
        override fun test(value: Collection<T>) = verify(value.isEmpty()) {
            "expected collection $value to be empty"
        }
    }

    fun <T> containAll(vararg values: T) = containAll(values.asList())
    fun <T> containAll(values: List<T>) = object : Matcher<Collection<T>>() {
        override fun test(value: Collection<T>) = verify(values.all(value::contains)) {
            "expected collection $value to contain all values of $values"
        }
    }

    fun <T : Comparable<T>> sorted() = object : Matcher<List<T>>() {
        override fun test(value: List<T>) = verify(value.sorted() == value) {
            "expected collection $value to be sorted"
        }
    }

}
