package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.Condition
import com.github.spoptchev.kotlin.preconditions.Matcher


interface StringMatcher {

    fun startWith(prefix: String) = object : Matcher<String>() {
        override fun test(condition: Condition<String>) = condition.test {
            withResult(value.startsWith(prefix)) { "$expectedTo start with '$prefix'" }
        }
    }

    fun include(substring: String) = object : Matcher<String>() {
        override fun test(condition: Condition<String>) = condition.test {
            withResult(value.contains(substring)) { "$expectedTo include '$substring'" }
        }
    }

    fun match(regex: String) = object : Matcher<String>() {
        override fun test(condition: Condition<String>) = condition.test {
            withResult(value.matches(Regex(regex))) { "$expectedTo match '$regex'" }
        }
    }

    fun endWith(suffix: String) = object : Matcher<String>() {
        override fun test(condition: Condition<String>) = condition.test {
            withResult(value.endsWith(suffix)) { "$expectedTo end with '$suffix'" }
        }
    }

    fun haveLength(length: Int) = object : Matcher<String>() {
        override fun test(condition: Condition<String>) = condition.test {
            withResult(value.length == length) { "$expectedTo have length $length" }
        }
    }

}
