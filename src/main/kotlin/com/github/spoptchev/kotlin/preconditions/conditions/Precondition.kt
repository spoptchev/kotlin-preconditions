package com.github.spoptchev.kotlin.preconditions.conditions


interface Precondition<in T> {

    fun test(value: T): Result

    fun verify(valid: Boolean, lazyMessage: () -> String): Result = Result(valid, lazyMessage)

}

data class Result(val valid: Boolean, val lazyMessage: () -> String)

