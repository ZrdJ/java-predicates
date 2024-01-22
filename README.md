[![License](https://img.shields.io/github/license/mashape/apistatus.svg?maxAge=2592000)]()
[![](https://jitpack.io/v/ZrdJ/java-predicates.svg)](https://jitpack.io/#ZrdJ/java-predicates)
![GitHub Workflow Status (branch)](https://github.com/zrdj/java-predicates/actions/workflows/maven.yml/badge.svg)

# java-predicates

collection of predefined predicates for all basic types

## Maven

Add the Jitpack repository to your build file

```xml

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Release artifact

```xml

<dependency>
    <groupId>com.github.zrdj</groupId>
    <artifactId>java-predicates</artifactId>
    <version>0.3.0</version>
</dependency>
```

## Motivation

The motivation behind this project is to achieve a more readable and maintainable way of chaining checks on all kind of
basic types (`primitives`, `strings`, `arrays`, etc ) we use in our everyday code, by providing
predefined `Predicate<T>` implementations.

So lets be naive and try to check that a given `String` conforms to a valid `.de` and `.com` URL.

```java
    public void doSomethingWhenUrlIsValid(final String url) {
    if (url != null
            && !url.isEmpty()
            && url.startsWith("http://")
            && (url.endsWith(".de") || url.endsWith(".com"))) {
        doSomethingWithThatUrl();
    } else {
        throw new IllegalArgumentException("invalid url: " + url);
    }
}
```

Basically this code has two issues, a major and a minor one. the **major** one is that whenever the condition on what a
valid `URL` is changes, you have to change the `if` statement. the **minor** one is that its not as fluent as it could
be for the reader.

Now lets use a separate `method` and the `Predicate<T>` interface from Java 8 to fix our major issue ...

```java
    public void doSomethingWhenUrlIsValid(final String url) {
    if (isValidUrl().test(url)) {
        doSomethingWithThatUrl();
    } else {
        throw new IllegalArgumentException("invalid url: " + url);
    }
}

public Predicate<String> isValidUrl() {
    return string -> string != null
            && !string.isEmpty()
            && string.startsWith("http://")
            && (string.endsWith(".de") || string.endsWith(".com"));
}
```

... and rewrite `isValidUrl()` method using our fluent `Predicates` interface ...

```java
    public Predicate<String> isValidUrl() {
    final Predicates checking = new Predicates.Default();
    final StringPredicates it = checking.string();

    return checking.that().valid().when().string().isNotNull()
            .and(it.isNotEmpty())
            .and(it.startsWith("http://"))
            .and(it.endsWith(".de").or(it.endsWith(".com")));
}
```


 
