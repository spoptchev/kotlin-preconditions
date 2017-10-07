# Kotlin Preconditions
[![Build Status](https://travis-ci.org/spoptchev/kotlin-preconditions.svg?branch=master)](https://travis-ci.org/spoptchev/kotlin-preconditions)

kotlin-preconditions will assist you in ensuring all of your invariants are met
before an operation is executed.

### Usage

kotlin-preconditions provides a powerful DSL for defining preconditions:

```kotlin
check {
    1 toBe lt(0)
}

require {
    "hello" to match("not")
}
```

If any of the preconditions are not met then `check` will throw an
`IllegalStateException` and `require` will throw an `IllegalArgumentException`.

### API Overview

#### String preconditions

```kotlin
require {
    "hello" to startWith("he")
    "hello" to include("ll")
    "hello" to match("hello")
    "hello" to endWith("lo")
    "hello" to haveLength(5)
}
```

#### Collection preconditions

```kotlin
val list = listOf(1, 2)

require {
    list to haveSize(2)
    list to contain(2)
    list notToBe empty()
    list to containAll(1, 2)
    list toBe sorted()
}
```

#### Comparable preconditions

```kotlin
require {
    1 toBe lt(2)
    1 toBe lte(1)
    1 toBe gt(0)
    1 toBe gte(1)
    1 toBe between(0..2)
}
```

#### Map preconditions

```kotlin
val map = mapOf(1 to "1")

require {
    map to haveKey(1)
    map to haveValue("1")
    map to contain(1, "1")
}
```

#### Object preconditions

```kotlin
val value = "hello"

require {
    value notTo beNull()
    value to equal("hello")
    value toBe sameInstanceAs("hello")
}
```

### Installation

Maven:

```
<dependency>
  <groupId>com.github.spoptchev</groupId>
  <artifactId>kotlin-preconditions</artifactId>
  <version>0.1.1</version>
</dependency>
```

Gradle:

```
compile 'com.github.spoptchev:kotlin-preconditions:0.1.1'
```

