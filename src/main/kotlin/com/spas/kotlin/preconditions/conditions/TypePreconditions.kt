package com.spas.kotlin.preconditions.conditions


interface TypePreconditions {

    fun <T> sameInstanceAs(reference: T): Precondition<T> = object : Precondition<T> {
        override fun test(value: T) =
                Result(value === reference, "expected $value to have the same reference as $reference")
    }

}
