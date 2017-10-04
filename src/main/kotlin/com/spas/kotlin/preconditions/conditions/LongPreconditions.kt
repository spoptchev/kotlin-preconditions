package com.spas.kotlin.preconditions.conditions

interface LongPreconditions {

    fun lt(number: Long) = lessThan(number)
    fun lessThan(number: Long) = object : Precondition<Long> {
        override fun test(value: Long) = Result(value < number, "expected $value to be < $number")
    }

    fun lte(number: Long) = lessThanOrEqualTo(number)
    fun lessThanOrEqualTo(number: Long) = object : Precondition<Long> {
        override fun test(value: Long) = Result(value <= number, "expected $value to be <= $number")
    }

    fun gt(number: Long) = greaterThan(number)
    fun greaterThan(number: Long) = object : Precondition<Long> {
        override fun test(value: Long) = Result(value > number, "expected $value to be > $number")
    }

    fun gte(number: Long) = greaterThanOrEqualTo(number)
    fun greaterThanOrEqualTo(number: Long) = object : Precondition<Long> {
        override fun test(value: Long) = Result(value >= number, "expected $value to be >= $number")
    }

    fun between(a: Long, b: Long): Precondition<Long> = object : Precondition<Long> {
        override fun test(value: Long) = Result(value in a..b, "expected $value to be between ($a, $b)")
    }

}
