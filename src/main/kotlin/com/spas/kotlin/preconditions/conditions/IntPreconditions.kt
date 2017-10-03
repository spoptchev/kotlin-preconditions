package com.spas.kotlin.preconditions.conditions


interface IntPreconditions {

    fun lt(n: Int) = object : Precondition<Int> {
        override fun test(value: Int): Result =
                Result(value < n, "expected $value to be < $n")
    }

}
