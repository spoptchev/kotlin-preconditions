package com.github.spoptchev.kotlin.preconditions

import com.github.spoptchev.kotlin.preconditions.matcher.*

@DslMarker
annotation class PreconditionDSLMarker

typealias EvaluationMethod = (value: Boolean, lazyMessage: () -> Any) -> Unit

@PreconditionDSLMarker
class PreconditionContext<T> : CollectionMatcher, ComparableMatcher, MapMatcher, StringMatcher, ObjectMatcher {

    infix fun Precondition<T>.and(precondition: Precondition<T>) = AndPrecondition(this, precondition)
    infix fun Precondition<T>.or(precondition: Precondition<T>) = OrPrecondition(this, precondition)
    fun not(precondition: Precondition<T>) = NotPrecondition(precondition)

}

fun <T> requireThat(value: T, label: String = "value", preconditionContext: PreconditionContext<T>.() -> Precondition<T>): T =
        require(value, label, preconditionContext)
fun <T> require(value: T, label: String = "value", preconditionContext: PreconditionContext<T>.() -> Precondition<T>): T =
        assertThat(value, label, ::require, preconditionContext)

fun <T> checkThat(value: T, label: String = "value", preconditionContext: PreconditionContext<T>.() -> Precondition<T>): T =
        check(value, label, preconditionContext)
fun <T> check(value: T, label: String = "value", preconditionContext: PreconditionContext<T>.() -> Precondition<T>): T =
        assertThat(value, label, ::check, preconditionContext)

private fun <T> assertThat(value: T, label: String, evaluate: EvaluationMethod, preconditionContext: PreconditionContext<T>.() -> Precondition<T>): T =
        preconditionContext(PreconditionContext()).let { Assertion(value, label, evaluate).run(it) }




