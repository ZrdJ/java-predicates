---
type: spec
title: Array Predicates
updated: 2026-09-01
---

## Purpose

Lets a caller build `Predicate<T[]>` checks — length, equality,
membership and emptiness — through `ArrayPredicates` instead of writing
the equivalent `Arrays`/loop-based checks inline every time. Unlike
`StringPredicates` and `CollectionPredicates`, `ArrayPredicates` offers no
`isNull()`/`isNotNull()`.

## Requirements

### Requirement: Length can be compared strictly or inclusively of the boundary
`req~array-predicates.length-comparison~1`

`hasLength(length)` requires exact equality; `hasLengthGreaterThan(min)`
and `hasLengthLessThan(max)` exclude the boundary value itself;
`hasLengthEqualOrGreaterThan(min)` and `hasLengthEqualOrLessThan(max)`
include it.

#### Scenario: hasLength

- **WHEN** `hasLength(3)` is tested against a 3-element array and against
  a 2-element array
- **THEN** it accepts the 3-element array and rejects the 2-element array

#### Scenario: hasLengthGreaterThan excludes the boundary

- **WHEN** `hasLengthGreaterThan(3)` is tested against a 4-element array
  and against a 3-element array
- **THEN** it accepts the 4-element array and rejects the 3-element array

#### Scenario: hasLengthLessThan excludes the boundary

- **WHEN** `hasLengthLessThan(3)` is tested against a 2-element array and
  against a 3-element array
- **THEN** it accepts the 2-element array and rejects the 3-element array

#### Scenario: hasLengthEqualOrGreaterThan includes the boundary

- **WHEN** `hasLengthEqualOrGreaterThan(3)` is tested against a
  3-element array and against a 2-element array
- **THEN** it accepts the 3-element array and rejects the 2-element array

#### Scenario: hasLengthEqualOrLessThan includes the boundary

- **WHEN** `hasLengthEqualOrLessThan(3)` is tested against a 3-element
  array and against a 4-element array
- **THEN** it accepts the 3-element array and rejects the 4-element array

### Requirement: Array equality is element-wise and order-sensitive
`req~array-predicates.equality~1`

`equals(inner)` delegates to `Arrays.equals(outer, inner)` — same length,
same elements, in the same order.

#### Scenario: equals

- **WHEN** `equals(new String[]{"one", "two"})` is tested against
  `{"one", "two"}` and against `{"one", "two", "three"}`
- **THEN** it accepts `{"one", "two"}` and rejects the differently-sized
  array

### Requirement: Membership checks treat the array as a set, ignoring duplicate elements
`req~array-predicates.membership~1`

`contains(element)` checks for one element. `containsAllOf(elements)`
converts the tested array to a `HashSet` before calling
`containsAll(Arrays.asList(elements))`, so duplicate elements in the
tested array do not affect the result. `containsAnyOf(elements)` and
`containsNoneOf(elements)` are the positive and negated any-match over
the same two arrays.

#### Scenario: contains

- **WHEN** `contains("one")` is tested against `{"one", "two"}` and
  against `{"two", "three"}`
- **THEN** it accepts `{"one", "two"}` and rejects `{"two", "three"}`

#### Scenario: containsAllOf

- **WHEN** `containsAllOf("one", "two")` is tested against
  `{"one", "two", "three"}` and against `{"two", "three"}`
- **THEN** it accepts the array containing both and rejects the array
  missing `"one"`

#### Scenario: containsAnyOf

- **WHEN** `containsAnyOf("one", "two")` is tested against
  `{"one", "three"}` and against `{"three", "four"}`
- **THEN** it accepts the array sharing an element and rejects the array
  sharing none

#### Scenario: containsNoneOf

- **WHEN** `containsNoneOf("one", "two")` is tested against
  `{"three", "four"}` and against `{"one", "four"}`
- **THEN** it accepts the array sharing no element and rejects the array
  sharing one

### Requirement: An array is empty when it has zero elements
`req~array-predicates.emptiness~1`

`isEmpty()` checks `outer.length == 0`; `isNotEmpty()` negates it. Unlike
`StringPredicates.isEmpty()`, there is no trimming concept — length is
the only criterion.

#### Scenario: isEmpty

- **WHEN** `isEmpty()` is tested against a 0-element array and against a
  1-element array
- **THEN** it accepts the 0-element array and rejects the 1-element array

#### Scenario: isNotEmpty

- **WHEN** `isNotEmpty()` is tested against a 1-element array and
  against a 0-element array
- **THEN** it accepts the 1-element array and rejects the 0-element array
