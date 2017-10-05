package com.spas.kotlin.preconditions.conditions


interface MapPreconditions {

    fun <K> haveKey(key: K): Precondition<Map<K, *>> = object : Precondition<Map<K, *>> {
        override fun test(value: Map<K, *>) = verify(value.containsKey(key)) {
            "expected $value to contain key $key"
        }
    }

    fun <V> haveValue(v: V): Precondition<Map<*, V>> = object : Precondition<Map<*, V>> {
        override fun test(value: Map<*, V>) = verify(value.containsValue(v)) {
            "expected $value to contain value $v"
        }
    }

    fun <K, V> contain(key: K, v: V): Precondition<Map<K, V>> = object : Precondition<Map<K, V>> {
        override fun test(value: Map<K, V>) = verify(value[key] == v) {
            "expected $value to contain $key=$v"
        }
    }

}
