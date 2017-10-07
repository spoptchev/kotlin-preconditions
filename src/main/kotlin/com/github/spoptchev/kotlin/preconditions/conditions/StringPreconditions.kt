package com.github.spoptchev.kotlin.preconditions.conditions


interface StringPreconditions {

    fun startWith(prefix: String): Precondition<String> = object : Precondition<String> {
        override fun test(value: String) = verify(value.startsWith(prefix)) {
            "expected '$value' to start with '$prefix'"
        }
    }

    fun include(substring: String): Precondition<String> = object : Precondition<String> {
        override fun test(value: String) = verify(value.contains(substring)) {
            "expected '$value' to include '$substring'"
        }
    }

    fun match(regex: String) = object : Precondition<String> {
        override fun test(value: String): Result = verify(value.matches(Regex(regex))) {
            "expected '$value' to match '$regex'"
        }
    }

    fun endWith(suffix: String): Precondition<String> = object : Precondition<String> {
        override fun test(value: String) = verify(value.endsWith(suffix)) {
            "expected '$value' to end with '$suffix'"
        }
    }

    fun haveLength(length: Int): Precondition<String> = object : Precondition<String> {
        override fun test(value: String) = verify(value.length == length) {
            "expected '$value' to have length $length"
        }
    }

}
