package com.github.spoptchev.kotlin.preconditions

import com.github.spoptchev.kotlin.preconditions.conditions.*

@DslMarker
annotation class PreconditionDSLMarker

typealias EvaluationMethod = (value: Boolean, lazyMessage: () -> Any) -> Unit

@PreconditionDSLMarker
open class PreconditionContext(
        private val evaluate: EvaluationMethod
) :
        CollectionPreconditions,
        ComparablePreconditions,
        MapPreconditions,
        StringPreconditions,
        TypePreconditions
{

    infix fun <T> T.to(precondition: Precondition<T>): T = toBe(precondition)

    infix fun <T> T.toBe(precondition: Precondition<T>): T = precondition
            .test(this)
            .run { evaluate(valid, lazyMessage) }
            .let { this }

    infix fun <T> T.notTo(precondition: Precondition<T>): T = notToBe(precondition)
    infix fun <T> T.notToBe(precondition: Precondition<T>): T = precondition
            .test(this)
            .run { evaluate(!valid) { negateMessage(lazyMessage()) } }
            .let { this }

    private fun negateMessage(message: String) =
            message.replace(NEGATION_REGEX, "$1not $2")

    companion object {
        @JvmField val NEGATION_REGEX = Regex("(^.*?)(to.*$)")
    }

}

fun require(preconditionContext: PreconditionContext.() -> Unit) {
    preconditionContext(PreconditionContext(evaluate = ::require))
}

fun check(preconditionContext: PreconditionContext.() -> Unit) {
    preconditionContext(PreconditionContext(evaluate = ::check))
}


