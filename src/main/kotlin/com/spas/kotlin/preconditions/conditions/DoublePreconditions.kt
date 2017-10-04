package com.spas.kotlin.preconditions.conditions


interface DoublePreconditions {

    fun lt(number: Double) = lessThan(number)
    fun lessThan(number: Double) = object : Precondition<Double> {
        override fun test(value: Double) = Result(value < number, "expected $value to be < $number")
    }

    fun lte(number: Double) = lessThanOrEqualTo(number)
    fun lessThanOrEqualTo(number: Double) = object : Precondition<Double> {
        override fun test(value: Double) = Result(value <= number, "expected $value to be <= $number")
    }

    fun gt(number: Double) = greaterThan(number)
    fun greaterThan(number: Double) = object : Precondition<Double> {
        override fun test(value: Double) = Result(value > number, "expected $value to be > $number")
    }

    fun gte(number: Double) = greaterThanOrEqualTo(number)
    fun greaterThanOrEqualTo(number: Double) = object : Precondition<Double> {
        override fun test(value: Double) = Result(value >= number, "expected $value to be >= $number")
    }

    fun between(a: Double, b: Double): Precondition<Double> = object : Precondition<Double> {
        override fun test(value: Double) = Result(value in a..b, "expected $value to be between ($a, $b)")
    }

}
