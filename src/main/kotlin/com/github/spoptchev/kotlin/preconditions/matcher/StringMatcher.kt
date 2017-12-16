package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.Condition
import com.github.spoptchev.kotlin.preconditions.Matcher


interface StringMatcher {

    fun startsWithIgnoreCase(prefix: String) = startsWith(prefix, true)
    fun startsWith(prefix: String, ignoreCase: Boolean = false) = object : Matcher<String>() {
        override fun test(condition: Condition<String>) = condition.test {
            withResult(value.startsWith(prefix, ignoreCase)) { "$expectedTo start with '$prefix'" }
        }
    }

    fun includesIgnoreCase(substring: String) = includes(substring, true)
    fun includes(substring: String, ignoreCase: Boolean = false) = object : Matcher<String>() {
        override fun test(condition: Condition<String>) = condition.test {
            withResult(value.contains(substring, ignoreCase)) { "$expectedTo include '$substring'" }
        }
    }

    fun matches(regex: String) = matches(Regex(regex))
    fun matches(regex: Regex) = object : Matcher<String>() {
        override fun test(condition: Condition<String>) = condition.test {
            withResult(value.matches(regex)) { "$expectedTo match '$regex'" }
        }
    }

    fun endsWithIgnoreCase(suffix: String) = endsWith(suffix, true)
    fun endsWith(suffix: String, ignoreCase: Boolean = false) = object : Matcher<String>() {
        override fun test(condition: Condition<String>) = condition.test {
            withResult(value.endsWith(suffix, ignoreCase)) { "$expectedTo end with '$suffix'" }
        }
    }

    fun hasLength(length: Int) = object : Matcher<String>() {
        override fun test(condition: Condition<String>) = condition.test {
            withResult(value.length == length) { "$expectedTo have length $length" }
        }
    }

    fun hasLengthBetween(min: Int, max: Int) = object : Matcher<String>() {
        override fun test(condition: Condition<String>) = condition.test {
            withResult(value.length in min..max) { "$expectedTo have length between $min and $max" }
        }
    }

    fun isEqualToIgnoreCase(other: String) = isEqualTo(other, true)
    fun isEqualTo(other: String, ignoreCase: Boolean = false) = object : Matcher<String>() {
        override fun test(condition: Condition<String>) = condition.test {
            withResult(value.equals(other, ignoreCase)) { "$expectedTo be equal to '$other'" }
        }
    }

    fun isBlank() = object : Matcher<String?>() {
        override fun test(condition: Condition<String?>) = condition.test {
            withResult(value?.isBlank() ?: true) { "$expectedTo be blank" }
        }
    }

    fun isEmptyString() = object : Matcher<String>() {
        override fun test(condition: Condition<String>) = condition.test {
            withResult(value.isEmpty()) { "$expectedTo be empty" }
        }
    }

}
