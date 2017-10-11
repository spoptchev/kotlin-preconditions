# Kotlin Preconditions [![Build Status](https://travis-ci.org/spoptchev/kotlin-preconditions.svg?branch=master)](https://travis-ci.org/spoptchev/kotlin-preconditions)

kotlin-preconditions will assist you in ensuring all of your invariants are met
before an operation is executed.

### Usage

kotlin-preconditions provides a powerful DSL for defining preconditions:

```kotlin
check(1) { lt(2) }
check(listOf(1, 2)) { to(contain(1)) }

require(1) { lt(2) }
require(listOf(1, 2)) { to(contain(1)) }
```

If any of the preconditions are not met then `check` will throw an
`IllegalStateException` and `require` will throw an `IllegalArgumentException`.
The exception message will be generated lazily, so your application does
not waste resources on evaluating objects that may not trigger an exception.

Compose your preconditions the way you like it:

```kotlin
val list = listOf(1, 2)

require(list) { contain(3) or contain(1) and not(haveSize(3)) }
```

### API Overview

#### String preconditions

```kotlin
val value = "hello"

require(value) { startWith("he") and haveLength(5) and not(include("io")) }
require(value) { include("ll") }
require(value) { match("hello") }
require(value) { endWith("lo") }
require(value) { haveLength(5) }
```

#### Collection preconditions

```kotlin
val list = listOf(1, 2)

require(list) { haveSize(2) }
require(list) { contain(1) or contain(3) and not(haveSize(3)) }
require(list) { containAll(1, 2) }
require(list) { containAll(list) }
require(list) { sorted() }
```

#### Comparable preconditions

```kotlin
require(1) { lt(2) }
require(1) { lte(1) }
require(1) { gt(0) }
require(1) { gte(1) }
require(1) { between(0..2) }
```

#### Map preconditions

```kotlin
val map = mapOf(1 to "1")

require(map) { to(haveKey(1)) }
require(map) { haveValue("1") }
require(map) { contain(1, "1") }
```

#### Object preconditions

```kotlin
val value = "hello"

require(value) { not(beNull()) }
require(value) { equal("hello") }
require(value) { sameInstanceAs("hello") }
```

#### Composed preconditions

```kotlin
val value = "hello"

require(value) { startWith("he") and haveLength(5) and not(include("io")) }
```

#### Labels

```kotlin
val numbers = listOf(1, 2)

require(numbers, "Numbers") { contain(3) or contain(1) and not(haveSize(3)) }
```

### Installation

Maven:

```
<dependency>
  <groupId>com.github.spoptchev</groupId>
  <artifactId>kotlin-preconditions</artifactId>
  <version>4.0.0</version>
</dependency>
```

Gradle:

```
compile 'com.github.spoptchev:kotlin-preconditions:4.0.0'
```

