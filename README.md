# Kotlin Preconditions [![Build Status](https://travis-ci.org/spoptchev/kotlin-preconditions.svg?branch=master)](https://travis-ci.org/spoptchev/kotlin-preconditions)

kotlin-preconditions will assist you in ensuring all of your invariants are met
before an operation is executed.

### Usage

kotlin-preconditions provides a powerful DSL for defining preconditions:

```kotlin
check {
    1 shouldBe lt(0)
}

require {
    "hello" should match("not")
}
```

If any of the preconditions are not met then `check` will throw an
`IllegalStateException` and `require` will throw an `IllegalArgumentException`.

Compose your preconditions the way you like it:

```kotlin
val list = listOf(1, 2)

require {
    list should contain(3).or(contain(1)).and(not(haveSize(3)))
}
```

### API Overview

#### String preconditions

```kotlin
require {
    "hello" should startWith("he")
    "hello" should include("ll")
    "hello" should match("hello")
    "hello" should endWith("lo")
    "hello" should haveLength(5)
}
```

#### Collection preconditions

```kotlin
val list = listOf(1, 2)

require {
    list should haveSize(2)
    list should contain(2)
    list shouldNotBe empty()
    list should containAll(1, 2)
    list shouldBe sorted()
}
```

#### Comparable preconditions

```kotlin
require {
    1 shouldBe lt(2)
    1 shouldBe lte(1)
    1 shouldBe gt(0)
    1 shouldBe gte(1)
    1 shouldBe between(0..2)
}
```

#### Map preconditions

```kotlin
val map = mapOf(1 to "1")

require {
    map should haveKey(1)
    map should haveValue("1")
    map should contain(1, "1")
}
```

#### Object preconditions

```kotlin
val value = "hello"

require {
    value shouldNot beNull()
    value should equal("hello")
    value shouldBe sameInstanceAs("hello")
}
```

#### Composed preconditions

```kotlin
val list = listOf(1, 2)

require {
    list should contain(3).or(contain(1)).and(not(haveSize(3)))
}
```

### Installation

Maven:

```
<dependency>
  <groupId>com.github.spoptchev</groupId>
  <artifactId>kotlin-preconditions</artifactId>
  <version>2.0.0</version>
</dependency>
```

Gradle:

```
compile 'com.github.spoptchev:kotlin-preconditions:2.0.0'
```

