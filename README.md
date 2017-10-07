# Kotlin Preconditions
[![Build Status](https://travis-ci.org/spoptchev/kotlin-preconditions.svg?branch=master)](https://travis-ci.org/spoptchev/kotlin-preconditions)

#### Precondition error checking in kotlin

kotlin-preconditions will assist you in ensuring all of your invariants are met
before an operation is executed.

#### Usage

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


#### Installation

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

