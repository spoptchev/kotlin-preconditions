package com.github.spoptchev.kotlin.preconditions

import com.github.spoptchev.kotlin.preconditions.matcher.*

@DslMarker
annotation class PreconditionDSLMarker

typealias EvaluationMethod = (value: Boolean, lazyMessage: () -> Any) -> Unit

@PreconditionDSLMarker
data class PreconditionContext(
        private val evaluate: EvaluationMethod,
        private val label: String? = null
) :
        CollectionMatcher,
        ComparableMatcher,
        MapMatcher,
        StringMatcher,
        ObjectMatcher
{

    inline fun <reified T> Precondition<T>.and(precondition: Precondition<T>) = AndPrecondition(this, precondition)
    inline fun <reified T> Precondition<T>.or(precondition: Precondition<T>) = OrPrecondition(this, precondition)
    inline fun <reified T> not(precondition: Precondition<T>) = NotPrecondition(precondition)

    infix fun <T> T.should(precondition: Precondition<T>): T = shouldBe(precondition)

    infix fun <T> T.shouldBe(precondition: Precondition<T>): T = evalPrecondition(precondition)
            .let { if (label == null) it else it.label(label) }
            .let { evaluate(it.valid, it.lazyMessage) }
            .let { this }

    inline infix fun <reified T> T.shouldNot(precondition: Precondition<T>): T = shouldNotBe(precondition)
    inline infix fun <reified T> T.shouldNotBe(precondition: Precondition<T>): T = shouldBe(not(precondition))

    fun <T> withLabel(label: String, context: PreconditionContext.() -> T): T = context(copy(evaluate = evaluate, label = label))

    private fun <T> T.evalPrecondition(precondition: Precondition<T>): Result = when(precondition) {

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

}

fun require(preconditionContext: PreconditionContext.() -> Unit) {
    preconditionContext(PreconditionContext(evaluate = ::require))
}

fun check(preconditionContext: PreconditionContext.() -> Unit) {
    preconditionContext(PreconditionContext(evaluate = ::check))
}


