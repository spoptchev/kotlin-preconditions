package com.spas.kotlin.preconditions.conditions


interface IntPreconditions {

    fun lt(number: Int) = lessThan(number)
    fun lessThan(number: Int) = object : Precondition<Int> {
        override fun test(value: Int) = Result(value < number, "expected $value to be < $number")
    }

    fun lte(number: Int) = lessThanOrEqualTo(number)
    fun lessThanOrEqualTo(number: Int) = object : Precondition<Int> {
        override fun test(value: Int) = Result(value <= number, "expected $value to be <= $number")
    }

    fun gt(number: Int) = greaterThan(number)
    fun greaterThan(number: Int) = object : Precondition<Int> {
        override fun test(value: Int) = Result(value > number, "expected $value to be > $number")
    }

    fun gte(number: Int) = greaterThanOrEqualTo(number)
    fun greaterThanOrEqualTo(number: Int) = object : Precondition<Int> {
        override fun test(value: Int) = Result(value >= number, "expected $value to be >= $number")
    }

    fun between(a: Int, b: Int): Precondition<Int> = object : Precondition<Int> {
        override fun test(value: Int) = Result(value in a..b, "expected $value to be between ($a, $b)")
    }

}
