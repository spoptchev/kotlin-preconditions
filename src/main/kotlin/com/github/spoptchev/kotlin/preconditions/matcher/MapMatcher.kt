package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.Condition
import com.github.spoptchev.kotlin.preconditions.Matcher


interface MapMatcher {

    fun <K> haveKey(key: K) = object : Matcher<Map<K, *>>() {
        override fun test(condition: Condition<Map<K, *>>) = condition.test {
            withResult(value.containsKey(key)) { "$expectedTo contain key $key" }
        }
    }

    fun <V> haveValue(v: V) = object : Matcher<Map<*, V>>() {
        override fun test(condition: Condition<Map<*, V>>) = condition.test {
            withResult(value.containsValue(v)) { "$expectedTo contain value $v" }
        }
    }

    fun <K, V> contain(key: K, v: V) = object : Matcher<Map<K, V>>() {
        override fun test(condition: Condition<Map<K, V>>) = condition.test() {
            withResult(value[key] == v) { "$expectedTo contain $key=$v" }
        }
    }

}
