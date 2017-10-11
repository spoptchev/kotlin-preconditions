package com.github.spoptchev.kotlin.preconditions

fun <T> condition(value: T, label: String = "value", negated: Boolean = false) = Condition(value, label, negated)
