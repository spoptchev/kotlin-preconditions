package com.spas.kotlin.preconditions.conditions


interface CollectionPreconditions {

    fun <T> haveSize(size: Int) = object : Precondition<Collection<T>> {
        override fun test(value: Collection<T>) =
                Result(value.size == size, "expected collection to have size $size but has size ${value.size}")
    }

    fun <T> contain(element: T) = object : Precondition<Collection<T>> {
        override fun test(value: Collection<T>) =
                Result(value.contains(element), "expected collection $value to contain element $element")
    }

    fun <T> empty(): Precondition<Collection<T>> = object : Precondition<Collection<T>> {
        override fun test(value: Collection<T>): Result =
                Result(value.isEmpty(), "expected collection $value to be empty")
    }

    fun <T> containAll(vararg values: T) = containAll(values.asList())
    fun <T> containAll(values: List<T>): Precondition<Collection<T>> = object : Precondition<Collection<T>> {
        override fun test(value: Collection<T>) =
                Result(values.all { value.contains(it) }, "expected collection $value to contain all values of $values")
    }

    fun <T : Comparable<T>> sorted(): Precondition<List<T>> = object : Precondition<List<T>> {
        override fun test(value: List<T>) =
                Result(value.sorted() == value, "expected collection $value to be sorted")
    }


}
