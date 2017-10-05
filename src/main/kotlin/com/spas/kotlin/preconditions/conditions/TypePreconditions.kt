package com.spas.kotlin.preconditions.conditions


interface TypePreconditions {

    fun <T> beNull(fieldName: String? = null): Precondition<T> = object : Precondition<T> {
        override fun test(value: T): Result = verify(value == null) {
            val expectation = when(fieldName.isNullOrEmpty()) {
                true -> "$value"
                else -> "field '$fieldName'"
            }

            "expected $expectation to be null"
        }
    }

    fun <T> equal(other: T): Precondition<T> = object : Precondition<T> {
        override fun test(value: T): Result = verify(value == other) {
            "expected $value to be equal to $other"
        }
    }

    fun <T> sameInstanceAs(reference: T): Precondition<T> = object : Precondition<T> {
        override fun test(value: T) = verify(value === reference) {
            "expected $value to have the same reference as $reference"
        }
    }

}
