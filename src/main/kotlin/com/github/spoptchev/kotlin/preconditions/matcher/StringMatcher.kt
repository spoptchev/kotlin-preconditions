package com.github.spoptchev.kotlin.preconditions.matcher

import com.github.spoptchev.kotlin.preconditions.Condition
import com.github.spoptchev.kotlin.preconditions.Matcher
import com.github.spoptchev.kotlin.preconditions.PreconditionBlock

fun PreconditionBlock<String>.startsWithIgnoreCase(prefix: String) = startsWith(prefix, true)
fun PreconditionBlock<String>.startsWith(prefix: String, ignoreCase: Boolean = false) = object : Matcher<String>() {
    override fun test(condition: Condition<String>) = condition.test {
        withResult(value.startsWith(prefix, ignoreCase)) { "$expectedTo start with '$prefix'" }
    }
}

fun PreconditionBlock<String>.includesIgnoreCase(substring: String) = includes(substring, true)
fun PreconditionBlock<String>.includes(substring: String, ignoreCase: Boolean = false) = object : Matcher<String>() {
    override fun test(condition: Condition<String>) = condition.test {
        withResult(value.contains(substring, ignoreCase)) { "$expectedTo include '$substring'" }
    }
}

fun PreconditionBlock<String>.matches(regex: String) = matches(Regex(regex))
fun PreconditionBlock<String>.matches(regex: Regex) = object : Matcher<String>() {
    override fun test(condition: Condition<String>) = condition.test {
        withResult(value.matches(regex)) { "$expectedTo match '$regex'" }
    }
}

fun PreconditionBlock<String>.endsWithIgnoreCase(suffix: String) = endsWith(suffix, true)
fun PreconditionBlock<String>.endsWith(suffix: String, ignoreCase: Boolean = false) = object : Matcher<String>() {
    override fun test(condition: Condition<String>) = condition.test {
        withResult(value.endsWith(suffix, ignoreCase)) { "$expectedTo end with '$suffix'" }
    }
}

fun PreconditionBlock<String>.hasLength(length: Int) = object : Matcher<String>() {
    override fun test(condition: Condition<String>) = condition.test {
        withResult(value.length == length) { "$expectedTo have length $length" }
    }
}

fun PreconditionBlock<String>.hasLengthBetween(min: Int, max: Int) = object : Matcher<String>() {
    override fun test(condition: Condition<String>) = condition.test {
        withResult(value.length in min..max) { "$expectedTo have length between $min and $max" }
    }
}

fun PreconditionBlock<String>.isEqualToIgnoreCase(other: String) = isEqualTo(other, true)
fun PreconditionBlock<String>.isEqualTo(other: String, ignoreCase: Boolean = false) = object : Matcher<String>() {
    override fun test(condition: Condition<String>) = condition.test {
        withResult(value.equals(other, ignoreCase)) { "$expectedTo be equal to '$other'" }
    }
}

fun PreconditionBlock<String?>.isBlank() = object : Matcher<String?>() {
    override fun test(condition: Condition<String?>) = condition.test {
        withResult(value?.isBlank() ?: true) { "$expectedTo be blank" }
    }
}

@JvmName("nonNullIsBlank")
fun PreconditionBlock<String>.isBlank() = object : Matcher<String>() {
    override fun test(condition: Condition<String>) = condition.test {
        withResult(value.isBlank()) { "$expectedTo be blank" }
    }
}

fun PreconditionBlock<String>.isEmpty() = object : Matcher<String>() {
    override fun test(condition: Condition<String>) = condition.test {
        withResult(value.isEmpty()) { "$expectedTo be empty" }
    }
}

