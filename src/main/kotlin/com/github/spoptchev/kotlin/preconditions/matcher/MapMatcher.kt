package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.Condition
import com.github.spoptchev.kotlin.preconditions.Matcher
import com.github.spoptchev.kotlin.preconditions.PreconditionBlock


fun <K, M : Map<K, *>> PreconditionBlock<M>.hasKey(key: K) = object : Matcher<M>() {
    override fun test(condition: Condition<M>) = condition.test {
        withResult(value.containsKey(key)) { "$expectedTo contain key $key" }
    }
}

fun <V, M : Map<*, V>> PreconditionBlock<M>.hasValue(v: V) = object : Matcher<M>() {
    override fun test(condition: Condition<M>) = condition.test {
        withResult(value.containsValue(v)) { "$expectedTo contain value $v" }
    }
}

fun <K, V, M : Map<K, V>> PreconditionBlock<M>.contains(key: K, v: V) = object : Matcher<M>() {
    override fun test(condition: Condition<M>) = condition.test {
        withResult(value[key] == v) { "$expectedTo contain $key=$v" }
    }
}

