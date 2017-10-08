package com.github.spoptchev.kotlin.preconditions

import com.github.spoptchev.kotlin.preconditions.matcher.*

@DslMarker
annotation class PreconditionDSLMarker

typealias EvaluationMethod = (value: Boolean, lazyMessage: () -> Any) -> Unit

@PreconditionDSLMarker
open class PreconditionContext(
        private val evaluate: EvaluationMethod
) :
        CollectionMatcher,
        ComparableMatcher,
        MapMatcher,
        StringMatcher,
        ObjectMatcher
{

    infix fun <T> T.to(precondition: Precondition<T>): T = toBe(precondition)

    infix fun <T> T.toBe(precondition: Precondition<T>): T = evalPrecondition(precondition)
            .let { evaluate(it.valid, it.lazyMessage) }
            .let { this }

    inline infix fun <reified T> T.notTo(precondition: Precondition<T>): T = notToBe(precondition)

    inline infix fun <reified T> T.notToBe(precondition: Precondition<T>): T = toBe(not(precondition))

}

fun require(preconditionContext: PreconditionContext.() -> Unit) {
    preconditionContext(PreconditionContext(evaluate = ::require))
}

fun check(preconditionContext: PreconditionContext.() -> Unit) {
    preconditionContext(PreconditionContext(evaluate = ::check))
}


