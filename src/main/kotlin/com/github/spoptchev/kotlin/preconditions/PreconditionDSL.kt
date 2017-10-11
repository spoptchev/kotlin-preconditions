package com.github.spoptchev.kotlin.preconditions

import com.github.spoptchev.kotlin.preconditions.matcher.*

@DslMarker
annotation class PreconditionDSLMarker

typealias EvaluationMethod = (value: Boolean, lazyMessage: () -> Any) -> Unit


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

@PreconditionDSLMarker
class PreconditionContext<T> : CollectionMatcher, ComparableMatcher, MapMatcher, StringMatcher, ObjectMatcher {

    fun to(precondition: Precondition<T>): Precondition<T> = toBe(precondition)
    fun toBe(precondition: Precondition<T>): Precondition<T> = precondition
    fun notTo(precondition: Precondition<T>): Precondition<T> = notToBe(precondition)
    fun notToBe(precondition: Precondition<T>): Precondition<T> = not(precondition)

    infix fun Precondition<T>.and(precondition: Precondition<T>) = AndPrecondition(this, precondition)
    infix fun Precondition<T>.or(precondition: Precondition<T>) = OrPrecondition(this, precondition)
    fun not(precondition: Precondition<T>) = NotPrecondition(precondition)

}

fun <T> require(value: T, label: String = "value", preconditionContext: PreconditionContext<T>.() -> Precondition<T>): T =
        assert(value, label, ::require, preconditionContext)

fun <T> check(value: T, label: String = "value", preconditionContext: PreconditionContext<T>.() -> Precondition<T>): T =
        assert(value, label, ::check, preconditionContext)

private fun <T> assert(value: T, label: String, evaluate: EvaluationMethod, preconditionContext: PreconditionContext<T>.() -> Precondition<T>): T =
        preconditionContext(PreconditionContext()).let { Assertion(value, label, evaluate).run(it) }




