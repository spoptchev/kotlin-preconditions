# Kotlin Preconditions [![Build Status](https://travis-ci.org/spoptchev/kotlin-preconditions.svg?branch=master)](https://travis-ci.org/spoptchev/kotlin-preconditions)

kotlin-preconditions will assist you in ensuring all of your invariants are met
before an operation is executed.

### Usage

kotlin-preconditions provides a powerful DSL for defining preconditions:

```kotlin
checkThat(1) { isLt(2) }
checkThat(listOf(1, 2)) { contains(1) }

requireThat(1) { isLt(2) }
requireThat(listOf(1, 2)) { contains(1) }
```

If any of the preconditions are not met then `checkThat` will throw an
`IllegalStateException` and `requireThat` will throw an `IllegalArgumentException`.
The exception message will be generated lazily, so your application does
not waste resources on evaluating objects that may not trigger an exception.

Compose your preconditions the way you like it:

```kotlin
val list = listOf(1, 2)

requireThat(list) { contains(3) or contains(1) and not(hasSize(3)) }
```

### API Overview

#### String preconditions

```kotlin
val value = "hello"

requireThat(value) { startsWith("he") and hasLength(5) and not(includes("io")) }
requireThat(value) { includes("ll") }
requireThat(value) { matches("hello") }
requireThat(value) { endsWith("lo") }
requireThat(value) { hasLength(5) }
requireThat(value) { not(isBlank()) }
requireThat(value) { not(isEmptyString()) }
```

#### Collection preconditions

```kotlin
val list = listOf(1, 2)

requireThat(list) { hasSize(2) }
requireThat(list) { contains(1) or contains(3) and not(hasSize(3)) }
requireThat(list) { containsAll(1, 2) }
requireThat(list) { containsAll(list) }
requireThat(list) { isSorted() }
```

#### Comparable preconditions

```kotlin
requireThat(1) { isLt(2) }
requireThat(1) { isLte(1) }
requireThat(1) { isGt(0) }
requireThat(1) { isGte(1) }
requireThat(1) { isBetween(0..2) }
```

#### Map preconditions

```kotlin
val map = mapOf(1 to "1")

requireThat(map) { hasKey(1) }
requireThat(map) { hasValue("1") }
requireThat(map) { contains(1, "1") }
```

#### Object preconditions

```kotlin
val result = Result(true)

requireThat(result) { not(isNull()) }
requireThat(result) { isEqualTo(result) }
requireThat(result) { isSameInstanceAs(result) }
```

#### Composed preconditions

```kotlin
val value = "hello"

requireThat(value) { startsWith("he") and hasLength(5) and not(includes("io")) }
```

#### Labels

```kotlin
val numbers = listOf(1, 2)

requireThat(numbers, "Numbers") { contains(3) or contains(1) and not(hasSize(3)) }
```

### Installation

Maven:

```
<dependency>
  <groupId>com.github.spoptchev</groupId>
  <artifactId>kotlin-preconditions</artifactId>
  <version>5.0.0</version>
</dependency>
```

Gradle:

```
compile 'com.github.spoptchev:kotlin-preconditions:5.0.0'
```

