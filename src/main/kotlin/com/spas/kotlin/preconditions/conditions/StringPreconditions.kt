package com.spas.kotlin.preconditions.conditions


interface StringPreconditions {

    fun match(regex: String) = object : Precondition<String> {
        override fun test(value: String): Result =
                Result(value.matches(Regex(regex)), "expected $regex to match $value")
    }

}
