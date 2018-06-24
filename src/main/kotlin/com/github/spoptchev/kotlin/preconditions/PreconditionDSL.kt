package com.github.spoptchev.kotlin.preconditions

@DslMarker
annotation class PreconditionDSLMarker

typealias EvaluationMethod = (value: Boolean, lazyMessage: () -> Any) -> Unit

@PreconditionDSLMarker
class PreconditionBlock<T>
infix fun <T> Precondition<T>.and(precondition: Precondition<T>) = AndPrecondition(this, precondition)
infix fun <T> Precondition<T>.or(precondition: Precondition<T>) = OrPrecondition(this, precondition)
fun <T> PreconditionBlock<T>.not(precondition: Precondition<T>) = NotPrecondition(precondition)

fun <T> requireThat(value: T, label: String = "value", preconditionBlock: PreconditionBlock<T>.() -> Precondition<T>): T =
        require(value, label, preconditionBlock)
fun <T> require(value: T, label: String = "value", preconditionBlock: PreconditionBlock<T>.() -> Precondition<T>): T =
        assertThat(value, label, ::require, preconditionBlock)

fun <T> checkThat(value: T, label: String = "value", preconditionBlock: PreconditionBlock<T>.() -> Precondition<T>): T =
        check(value, label, preconditionBlock)
fun <T> check(value: T, label: String = "value", preconditionBlock: PreconditionBlock<T>.() -> Precondition<T>): T =
        assertThat(value, label, ::check, preconditionBlock)

private fun <T> assertThat(value: T, label: String, evaluate: EvaluationMethod, preconditionBlock: PreconditionBlock<T>.() -> Precondition<T>): T =
        preconditionBlock(PreconditionBlock()).let { Assertion(value, label, evaluate).run(it) }




