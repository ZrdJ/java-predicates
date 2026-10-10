---
type: spec
title: Integer Predicates
updated: 2026-09-01
---

## Purpose

Lets a caller build `Predicate<Integer>` comparisons — bound, equality and
range checks — through `IntegerPredicates` instead of writing the
equivalent `int` comparisons inline every time.

## Requirements

### Requirement: A bound can be compared strictly or inclusively
`req~integer-predicates.bound-comparison~1`

`isGreaterThan(min)` and `isLessThan(max)` exclude the boundary value
itself; `isEqualOrGreaterThan(min)` and `isEqualOrLessThan(max)` include
it.

#### Scenario: isGreaterThan excludes the boundary

- **WHEN** `isGreaterThan(10)` is tested against `11` and against `10`
- **THEN** it accepts `11` and rejects `10`

#### Scenario: isLessThan excludes the boundary

- **WHEN** `isLessThan(10)` is tested against `9` and against `10`
- **THEN** it accepts `9` and rejects `10`

#### Scenario: isEqualOrGreaterThan includes the boundary

- **WHEN** `isEqualOrGreaterThan(10)` is tested against `10` and against
  `9`
- **THEN** it accepts `10` and rejects `9`

#### Scenario: isEqualOrLessThan includes the boundary

- **WHEN** `isEqualOrLessThan(10)` is tested against `10` and against
  `11`
- **THEN** it accepts `10` and rejects `11`

### Requirement: Two integers can be compared for equality
`req~integer-predicates.equality~1`

`equals(inner)` compares by primitive `==`, not `Integer.equals`.

#### Scenario: equals

- **WHEN** `equals(10)` is tested against `10` and against `11`
- **THEN** it accepts `10` and rejects `11`

### Requirement: isBetween excludes both endpoints of its range
`req~integer-predicates.between-exclusive~1`

`isBetween(left, right)` is composed as
`isGreaterThan(left).and(isLessThan(right))` — a strictly-inside range.
`left` and `right` themselves are rejected, not accepted.

#### Scenario: Inside the range is accepted, the endpoints are not

- **WHEN** `isBetween(5, 10)` is tested against `7`, against `5` and
  against `10`
- **THEN** it accepts `7`
- **AND** it rejects `5` and `10`
