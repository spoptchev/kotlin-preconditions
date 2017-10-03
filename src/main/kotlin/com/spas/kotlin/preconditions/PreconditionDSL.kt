package com.spas.kotlin.preconditions

import com.spas.kotlin.preconditions.conditions.IntPreconditions
import com.spas.kotlin.preconditions.conditions.Precondition
import com.spas.kotlin.preconditions.conditions.StringPreconditions

@DslMarker
annotation class PreconditionDSLMarker

typealias EvaluationMethod = (value: Boolean, lazyMessage: () -> Any) -> Unit

@PreconditionDSLMarker
open class PreconditionContext(
        private val evaluate: EvaluationMethod
) : IntPreconditions, StringPreconditions {

    infix fun <T> T.to(precondition: Precondition<T>): T = toBe(precondition)

    infix fun <T> T.toBe(precondition: Precondition<T>): T = precondition
            .test(this)
            .run { evaluate(valid) { message } }
            .let { this }

}

fun require(preconditionContext: PreconditionContext.() -> Unit) {
    preconditionContext(PreconditionContext(evaluate = ::require))
}

fun check(preconditionContext: PreconditionContext.() -> Unit) {
    preconditionContext(PreconditionContext(evaluate = ::check))
}


