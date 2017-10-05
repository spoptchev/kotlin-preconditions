package com.spas.kotlin.preconditions.conditions


interface Precondition<in T> {

    fun test(value: T): Result

}

data class Result(val valid: Boolean, val lazyMessage: () -> String)

internal fun verify(valid: Boolean, lazyMessage: () -> String): Result = Result(valid, lazyMessage)
