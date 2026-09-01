---
type: spec
title: Predicates Facade
updated: 2026-09-01
---

## Purpose

Gives a caller one `Predicates` entry point that reaches every predicate
category (`string()`, `integer()`, `array()`, `object()`, `collection()`),
tests a `Predicate<T>` against a batch of elements without hand-written
loops, and reads as a sentence via no-op chaining words — instead of
constructing each category's `Default` implementation and stream calls
directly.

## Requirements

### Requirement: The no-argument constructor wires every category to its own Default implementation
`req~predicates-facade.default-wiring~1`

`new Predicates.Default()` must construct itself with a
`StringPredicates.Default`, an `IntegerPredicates.Default`, an
`ArrayPredicates.Default`, an `ObjectPredicates.Default` and a
`CollectionPredicates.Default` — one per accessor, none left unset.

#### Scenario: Every accessor returns its category's own Default implementation

- **WHEN** `new Predicates.Default()` is constructed with no arguments
- **THEN** `string()` returns a `StringPredicates.Default`
- **AND** `integer()` returns an `IntegerPredicates.Default`
- **AND** `array()` returns an `ArrayPredicates.Default`
- **AND** `object()` returns an `ObjectPredicates.Default`
- **AND** `collection()` returns a `CollectionPredicates.Default`

### Requirement: A predicate can be tested against a batch of elements without a hand-written loop
`req~predicates-facade.batch-matching~1`

`anyMatch(spec, elements)`, `noneMatch(spec, elements)` and
`allMatch(spec, elements)` must delegate to the correspondingly named
method on `Stream.of(elements)`.

#### Scenario: anyMatch

- **WHEN** `anyMatch(spec, elements)` is called
- **THEN** it returns `Stream.of(elements).anyMatch(spec)` — `true` when
  at least one element matches `spec`

#### Scenario: noneMatch

- **WHEN** `noneMatch(spec, elements)` is called
- **THEN** it returns `Stream.of(elements).noneMatch(spec)` — `true` when
  no element matches `spec`

#### Scenario: allMatch

- **WHEN** `allMatch(spec, elements)` is called
- **THEN** it returns `Stream.of(elements).allMatch(spec)` — `true` only
  when every element matches `spec`

### Requirement: Six chaining words read as a sentence without altering the Predicates instance
`req~predicates-facade.fluent-noop-words~1`

`let()`, `ensure()`, `check()`, `that()`, `when()` and `valid()` are pure
readability sugar: each returns the same `Predicates` instance unchanged,
so any number of them can be chained in front of a category accessor
(e.g. `checking.let().ensure().that().string()`) without changing what
that accessor returns.

#### Scenario: A chaining word returns the same instance

- **WHEN** any of `let()`, `ensure()`, `check()`, `that()`, `when()` or
  `valid()` is called on a `Predicates` instance
- **THEN** the exact same instance is returned

### Requirement: A value of any type can be checked for nullness
`req~predicates-facade.generic-nullness~1`

`ObjectPredicates.isNull()` delegates to `Objects::isNull` and
`isNotNull()` to `Objects::nonNull`, applicable to any type `T` — the
generic counterpart to the type-specific nullness checks on `String` and
`Collection`.

#### Scenario: isNull

- **WHEN** `ObjectPredicates.isNull()` is tested against `null` and
  against a non-null value
- **THEN** it accepts `null` and rejects the non-null value

#### Scenario: isNotNull

- **WHEN** `ObjectPredicates.isNotNull()` is tested against `null` and
  against a non-null value
- **THEN** it rejects `null` and accepts the non-null value
