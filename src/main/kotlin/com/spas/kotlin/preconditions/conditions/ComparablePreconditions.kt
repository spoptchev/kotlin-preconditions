package com.spas.kotlin.preconditions.conditions


interface ComparablePreconditions {

    fun <T> lt(other: T) = lessThan(other)
    fun <T> lessThan(other: T) = object : Precondition<Comparable<T>> {
        override fun test(value: Comparable<T>) =
                Result(value < other, "expected $value to be < $other")
    }

    fun <T> lte(other: T) = lessThanOrEqualTo(other)
    fun <T> lessThanOrEqualTo(other: T) = object : Precondition<Comparable<T>> {
        override fun test(value: Comparable<T>) = Result(value <= other, "expected $value to be <= $other")
    }

    fun <T> gt(other: T) = greaterThan(other)
    fun <T> greaterThan(other: T) = object : Precondition<Comparable<T>> {
        override fun test(value: Comparable<T>) = Result(value > other, "expected $value to be > $other")
    }

    fun <T> gte(other: T) = greaterThanOrEqualTo(other)
    fun <T> greaterThanOrEqualTo(other: T) = object : Precondition<Comparable<T>> {
        override fun test(value: Comparable<T>) = Result(value >= other, "expected $value to be >= $other")
    }

}
