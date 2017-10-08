package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.Matcher


interface MapMatcher {

    fun <K> haveKey(key: K) = object : Matcher<Map<K, *>>() {
        override fun test(value: Map<K, *>) = verify(value.containsKey(key)) {
            "expected $value to contain key $key"
        }
    }

    fun <V> haveValue(v: V) = object : Matcher<Map<*, V>>() {
        override fun test(value: Map<*, V>) = verify(value.containsValue(v)) {
            "expected $value to contain value $v"
        }
    }

    fun <K, V> contain(key: K, v: V) = object : Matcher<Map<K, V>>() {
        override fun test(value: Map<K, V>) = verify(value[key] == v) {
            "expected $value to contain $key=$v"
        }
    }

}
