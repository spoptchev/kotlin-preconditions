package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.Matcher


interface StringMatcher {

    fun startWith(prefix: String) = object : Matcher<String>() {
        override fun test(value: String) = verify(value.startsWith(prefix)) {
            "expected '$value' to start with '$prefix'"
        }
    }

    fun include(substring: String) = object : Matcher<String>() {
        override fun test(value: String) = verify(value.contains(substring)) {
            "expected '$value' to include '$substring'"
        }
    }

    fun match(regex: String) = object : Matcher<String>() {
        override fun test(value: String) = verify(value.matches(Regex(regex))) {
            "expected '$value' to match '$regex'"
        }
    }

    fun endWith(suffix: String) = object : Matcher<String>() {
        override fun test(value: String) = verify(value.endsWith(suffix)) {
            "expected '$value' to end with '$suffix'"
        }
    }

    fun haveLength(length: Int) = object : Matcher<String>() {
        override fun test(value: String) = verify(value.length == length) {
            "expected '$value' to have length $length"
        }
    }

}
