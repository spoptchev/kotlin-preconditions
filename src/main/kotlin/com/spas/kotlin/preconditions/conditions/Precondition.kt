package com.spas.kotlin.preconditions.conditions


interface Precondition<in T> {

    fun test(value: T): Result

}

data class Result(val valid: Boolean, val message: String)
