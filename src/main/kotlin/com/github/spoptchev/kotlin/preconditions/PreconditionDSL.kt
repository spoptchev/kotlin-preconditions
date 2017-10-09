package com.github.spoptchev.kotlin.preconditions

import com.github.spoptchev.kotlin.preconditions.matcher.*

@DslMarker
annotation class PreconditionDSLMarker

typealias EvaluationMethod = (value: Boolean, lazyMessage: () -> Any) -> Unit


data class Assertion<out T>(val value: T, val label: String?) {

    fun evalPrecondition(precondition: Precondition<T>): Result = when(precondition) {

        is Matcher<T> ->
            precondition.test(value)

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

}

@PreconditionDSLMarker
class PreconditionContext<T> : CollectionMatcher, ComparableMatcher, MapMatcher, StringMatcher, ObjectMatcher {

    infix fun Precondition<T>.and(precondition: Precondition<T>) = AndPrecondition(this, precondition)
    infix fun Precondition<T>.or(precondition: Precondition<T>) = OrPrecondition(this, precondition)
    infix fun not(precondition: Precondition<T>) = NotPrecondition(precondition)

    fun to(precondition: Precondition<T>): Precondition<T> = toBe(precondition)
    fun toBe(precondition: Precondition<T>): Precondition<T> = precondition
    fun notTo(precondition: Precondition<T>): Precondition<T> = notToBe(precondition)
    fun notToBe(precondition: Precondition<T>): Precondition<T> = not(precondition)

}

fun <T> require(value: T, label: String? = null, preconditionContext: PreconditionContext<T>.() -> Precondition<T>): T =
        assert(value, label, ::require, preconditionContext)

fun <T> check(value: T, label: String? = null, preconditionContext: PreconditionContext<T>.() -> Precondition<T>): T =
        assert(value, label, ::check, preconditionContext)

private fun <T> assert(value: T, label: String? = null, evaluate: EvaluationMethod, preconditionContext: PreconditionContext<T>.() -> Precondition<T>): T {
    val assertion = Assertion(value, label)
    val precondition = preconditionContext(PreconditionContext())
    val result = assertion.evalPrecondition(precondition)

    return result.let { it.label(assertion.label) }
            .let { evaluate(it.valid, it.lazyMessage) }
            .let { assertion.value }
}


