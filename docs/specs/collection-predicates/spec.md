---
type: spec
title: Collection Predicates
updated: 2026-09-01
---

## Purpose

Lets a caller build `Predicate<Collection<T>>` checks — size, equality,
membership, emptiness and nullness — through `CollectionPredicates`
instead of writing the equivalent `Collection` method calls inline every
time. Membership checks accept either a `Collection<T>` or a `T...`
varargs argument; the varargs overloads simply wrap their arguments in
`Arrays.asList` and forward to the `Collection`-typed overload, so both
forms behave identically.

## Requirements

### Requirement: Size can be compared strictly or inclusively of the boundary
`req~collection-predicates.size-comparison~1`

`hasSize(size)` requires exact equality; `hasSizeGreaterThan(min)` and
`hasSizeLessThan(max)` exclude the boundary value itself;
`hasSizeEqualOrGreaterThan(min)` and `hasSizeEqualOrLessThan(max)`
include it.

#### Scenario: hasSize

- **WHEN** `hasSize(1)` is tested against a 1-element list and against a
  2-element list
- **THEN** it accepts the 1-element list and rejects the 2-element list

#### Scenario: hasSizeGreaterThan excludes the boundary

- **WHEN** `hasSizeGreaterThan(1)` is tested against a 2-element list and
  against a 1-element list
- **THEN** it accepts the 2-element list and rejects the 1-element list

#### Scenario: hasSizeLessThan excludes the boundary

- **WHEN** `hasSizeLessThan(1)` is tested against an empty list and
  against a 1-element list
- **THEN** it accepts the empty list and rejects the 1-element list

#### Scenario: hasSizeEqualOrGreaterThan includes the boundary

- **WHEN** `hasSizeEqualOrGreaterThan(1)` is tested against a 1-element
  list and against an empty list
- **THEN** it accepts the 1-element list and rejects the empty list

#### Scenario: hasSizeEqualOrLessThan includes the boundary

- **WHEN** `hasSizeEqualOrLessThan(1)` is tested against a 1-element list
  and against a 2-element list
- **THEN** it accepts the 1-element list and rejects the 2-element list

### Requirement: Collection equality follows the given collection's own equals contract
`req~collection-predicates.equality~1`

`equals(inner)` returns `inner::equals` — the tested collection is passed
into `inner`'s own `equals` method, not the other way around. Equality
therefore follows whatever contract `inner`'s concrete type defines (e.g.
`List.equals` requires the same order).

#### Scenario: equals

- **WHEN** `equals(List.of("two", "elements"))` is tested against
  `List.of("two", "elements")` and against `List.of("one element")`
- **THEN** it accepts the identical list and rejects the different one

### Requirement: Membership can be checked for one element, all of, any of or none of a set of elements
`req~collection-predicates.membership~1`

`contains(element)` checks for one element via `outer.contains(element)`.
`containsAllOf(elements)` requires every given element to be present via
`elements.stream().allMatch(outer::contains)`; `containsAnyOf(elements)`
requires at least one; `containsNoneOf(elements)` requires none.

#### Scenario: contains

- **WHEN** `contains("one element")` is tested against a list containing
  it and against an empty list
- **THEN** it accepts the list containing it and rejects the empty list

#### Scenario: containsAllOf

- **WHEN** `containsAllOf("two", "elements")` is tested against
  `List.of("two", "elements")` and against an empty list
- **THEN** it accepts the list with both elements and rejects the empty
  list

#### Scenario: containsAnyOf

- **WHEN** `containsAnyOf("two", "one element")` is tested against
  `List.of("two", "elements")` and against an empty list
- **THEN** it accepts the list sharing an element and rejects the empty
  list

#### Scenario: containsNoneOf

- **WHEN** `containsNoneOf("two", "one element")` is tested against an
  empty list and against `List.of("two", "elements")`
- **THEN** it accepts the empty list and rejects the list sharing an
  element

### Requirement: A collection is empty when it has zero elements, independent of reference nullness
`req~collection-predicates.emptiness-and-null~1`

`isEmpty()` delegates to `Collection::isEmpty`; `isNotEmpty()` negates it.
`isNull()`/`isNotNull()` separately check reference nullness via
`Objects::isNull`/`Objects::nonNull`.

#### Scenario: isEmpty

- **WHEN** `isEmpty()` is tested against an empty list and against a
  1-element list
- **THEN** it accepts the empty list and rejects the 1-element list

#### Scenario: isNotEmpty

- **WHEN** `isNotEmpty()` is tested against a 1-element list and against
  an empty list
- **THEN** it accepts the 1-element list and rejects the empty list

#### Scenario: isNull

- **WHEN** `isNull()` is tested against `null` and against an empty list
- **THEN** it accepts `null` and rejects the empty list

#### Scenario: isNotNull

- **WHEN** `isNotNull()` is tested against `null` and against an empty
  list
- **THEN** it rejects `null` and accepts the empty list
