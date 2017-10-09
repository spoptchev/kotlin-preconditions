package com.github.spoptchev.kotlin.preconditions

data class Result(val valid: Boolean, val lazyMessage: () -> String) {

    fun negate() = copy(valid = !valid, lazyMessage = negatedLazyMessage(lazyMessage()))

    fun label(label: String?) = if (valid || label == null) this else
        copy(valid = !valid, lazyMessage = labelLazyMessage(label, lazyMessage()))

    private fun labelLazyMessage(label: String, message: String) = {
        message.replace(LABEL_REGEX, "$1 $label with value(s)$2")
    }

    private fun negatedLazyMessage(message: String) = {
        message.replace(NEGATION_REGEX, "$1not $2")
    }

    companion object {
        @JvmField val NEGATION_REGEX = Regex("(^.*?)(to.*$)")
        @JvmField val LABEL_REGEX = Regex("(^expected)(.*$)")
    }

}

sealed class Precondition<in T>

abstract class Matcher<in T> : Precondition<T>() {

    abstract fun test(value: T): Result

    protected fun verify(valid: Boolean, lazyMessage: () -> String) = Result(valid, lazyMessage)

}

class AndPrecondition<in T>(val left: Precondition<T>, val right: Precondition<T>) : Precondition<T>()

class OrPrecondition<in T>(val left: Precondition<T>, val right: Precondition<T>) : Precondition<T>()

class NotPrecondition<in T>(val precondition: Precondition<T>) : Precondition<T>()
