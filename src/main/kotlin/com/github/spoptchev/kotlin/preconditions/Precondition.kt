package com.github.spoptchev.kotlin.preconditions

data class Result(val valid: Boolean, val lazyMessage: () -> String)

data class Condition<out T>(
        val value: T,
        private val label: String,
        private val negated: Boolean = false
) {

    val expectedTo: String by lazy {
        when (negated) {
            true -> "expected $label $value not to"
            else -> "expected $label $value to"
        }
    }

    fun test(run: Condition<T>.() -> Result): Result = run(this)

    fun withResult(valid: Boolean, lazyMessage: () -> String) = Result(valid == !negated, lazyMessage)

}

data class Assertion<out T>(
        private val value: T,
        private val label: String,
        private val evaluate: EvaluationMethod
) {

    fun run(precondition: Precondition<T>): T = evalPrecondition(precondition)
            .let { evaluate(it.valid, it.lazyMessage) }
            .let { value }

    private fun evalPrecondition(precondition: Precondition<T>, negated: Boolean = false): Result = when(precondition) {

        is Matcher<T> ->
            precondition.test(Condition(value, label, negated))

        is AndPrecondition<T> -> {
            val left = evalPrecondition(precondition.left)
            if (left.valid) evalPrecondition(precondition.right) else left
        }

        is OrPrecondition<T> -> {
            val left = evalPrecondition(precondition.left)
            if (left.valid) left else evalPrecondition(precondition.right)
        }

        is NotPrecondition<T> ->
            evalPrecondition(precondition.precondition, true)

    }

}

sealed class Precondition<in T>

abstract class Matcher<in T> : Precondition<T>() {

    abstract fun test(condition: Condition<T>): Result

}

class AndPrecondition<in T>(val left: Precondition<T>, val right: Precondition<T>) : Precondition<T>()

class OrPrecondition<in T>(val left: Precondition<T>, val right: Precondition<T>) : Precondition<T>()

class NotPrecondition<in T>(val precondition: Precondition<T>) : Precondition<T>()
