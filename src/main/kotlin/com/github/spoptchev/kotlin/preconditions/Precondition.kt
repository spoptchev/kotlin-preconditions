package com.github.spoptchev.kotlin.preconditions


data class Result(val valid: Boolean, val lazyMessage: () -> String) {

    fun negate() = copy(valid = !valid, lazyMessage = negatedLazyMessage(lazyMessage()))

    private fun negatedLazyMessage(message: String) = {
        message.replace(NEGATION_REGEX, "$1not $2")
    }

    companion object {
        @JvmField val NEGATION_REGEX = Regex("(^.*?)(to.*$)")
    }

}

sealed class Precondition<in T>

abstract class Matcher<T> : Precondition<T>() {

    abstract fun test(value: T): Result

    fun and(precondition: Precondition<T>) = AndPrecondition(this, precondition)

    fun or(precondition: Precondition<T>) = OrPrecondition(this, precondition)

    protected fun verify(valid: Boolean, lazyMessage: () -> String) = Result(valid, lazyMessage)

}

class AndPrecondition<in T>(val left: Precondition<T>, val right: Precondition<T>) : Precondition<T>()

class OrPrecondition<in T>(val left: Precondition<T>, val right: Precondition<T>) : Precondition<T>()

class NotPrecondition<T>(val precondition: Precondition<T>) : Precondition<T>() {

    fun and(precondition: Precondition<T>) = AndPrecondition(this, precondition)

    fun or(precondition: Precondition<T>) = OrPrecondition(this, precondition)

}

internal fun <T> T.evalPrecondition(precondition: Precondition<T>): Result = when(precondition) {

    is Matcher<T> ->
        precondition.test(this)

    is AndPrecondition<T> -> {
        val left = evalPrecondition(precondition.left)
        if (left.valid) evalPrecondition(precondition.right) else left
    }

    is OrPrecondition<T> -> {
        val left = evalPrecondition(precondition.left)
        if (left.valid) left else evalPrecondition(precondition.right)
    }

    is NotPrecondition<T> ->
        evalPrecondition(precondition.precondition).let(Result::negate)

}


