---
type: spec
title: String Predicates
updated: 2026-09-01
---

## Purpose

Lets a caller build `Predicate<String>` checks — equality, prefix/suffix,
substring, length, format validity, blankness and nullness — through
`StringPredicates`, case-sensitively or case-insensitively, instead of
writing the equivalent `String` method calls inline every time.

## Requirements

### Requirement: Exact, prefix, suffix and substring matching are case-sensitive
`req~string-predicates.case-sensitive-matching~1`

`equals(inner)`, `startsWith(inner)`, `endsWith(inner)` and
`contains(inner)` compare the tested text against `inner` using the
`String` methods of the same name, without altering case.

#### Scenario: equals

- **WHEN** `equals("one")` is tested against `"one"` and against `"ONE"`
- **THEN** it accepts `"one"` and rejects `"ONE"`

#### Scenario: startsWith

- **WHEN** `startsWith("one")` is tested against `"onetwo"` and against
  `"ONEtwo"`
- **THEN** it accepts `"onetwo"` and rejects `"ONEtwo"`

#### Scenario: endsWith

- **WHEN** `endsWith("two")` is tested against `"onetwo"` and against
  `"oneTWO"`
- **THEN** it accepts `"onetwo"` and rejects `"oneTWO"`

#### Scenario: contains

- **WHEN** `contains("two")` is tested against `"onetwothree"` and
  against `"oneTWOthree"`
- **THEN** it accepts `"onetwothree"` and rejects `"oneTWOthree"`

### Requirement: Case-insensitive matching lowercases both sides before comparing
`req~string-predicates.case-insensitive-matching~1`

`equalsIgnoreCase(inner)` delegates to `String.equalsIgnoreCase`;
`startsWithIgnoreCase(inner)`, `endsWithIgnoreCase(inner)` and
`containsIgnoreCase(inner)` instead call `toLowerCase()` on both the
tested text and `inner` before delegating to the case-sensitive check.

#### Scenario: equalsIgnoreCase

- **WHEN** `equalsIgnoreCase("one")` is tested against `"ONE"`
- **THEN** it accepts `"ONE"`

#### Scenario: startsWithIgnoreCase

- **WHEN** `startsWithIgnoreCase("one")` is tested against `"ONEtwo"`
- **THEN** it accepts `"ONEtwo"`

#### Scenario: endsWithIgnoreCase

- **WHEN** `endsWithIgnoreCase("two")` is tested against `"oneTWO"`
- **THEN** it accepts `"oneTWO"`

#### Scenario: containsIgnoreCase

- **WHEN** `containsIgnoreCase("two")` is tested against `"oneTWOthree"`
- **THEN** it accepts `"oneTWOthree"`

### Requirement: Length can be compared strictly or inclusively of the boundary
`req~string-predicates.length-comparison~1`

`hasLength(length)` requires exact equality; `hasLengthGreaterThan(min)`
and `hasLengthLessThan(max)` exclude the boundary value itself;
`hasLengthEqualOrGreaterThan(min)` and `hasLengthEqualOrLessThan(max)`
include it.

#### Scenario: hasLength

- **WHEN** `hasLength(1)` is tested against `"o"` and against `"one"`
- **THEN** it accepts `"o"` and rejects `"one"`

#### Scenario: hasLengthGreaterThan excludes the boundary

- **WHEN** `hasLengthGreaterThan(1)` is tested against `"one"` and
  against `"o"`
- **THEN** it accepts `"one"` and rejects `"o"`

#### Scenario: hasLengthLessThan excludes the boundary

- **WHEN** `hasLengthLessThan(1)` is tested against `""` and against
  `"o"`
- **THEN** it accepts `""` and rejects `"o"`

#### Scenario: hasLengthEqualOrGreaterThan includes the boundary

- **WHEN** `hasLengthEqualOrGreaterThan(1)` is tested against `"o"` and
  against `""`
- **THEN** it accepts `"o"` and rejects `""`

#### Scenario: hasLengthEqualOrLessThan includes the boundary

- **WHEN** `hasLengthEqualOrLessThan(1)` is tested against `"o"` and
  against `"one"`
- **THEN** it accepts `"o"` and rejects `"one"`

### Requirement: Text can be checked for boolean-literal, integer or UUID format
`req~string-predicates.format-validity~1`

`isBoolean()` accepts only the literal text `"true"` or `"false"` in any
case (via `equalsIgnoreCase`), not other truthy/falsy spellings.
`isInteger()` accepts text `Integer.parseInt` can parse, and `isUUID()`
accepts text `UUID.fromString` can parse — both via `Throws.exception`,
so a parse failure is reported as `false`, not a propagated exception.

#### Scenario: isBoolean

- **WHEN** `isBoolean()` is tested against `"TRUE"` and against `"other"`
- **THEN** it accepts `"TRUE"` and rejects `"other"`

#### Scenario: isInteger

- **WHEN** `isInteger()` is tested against `"1"` and against `"text"`
- **THEN** it accepts `"1"` and rejects `"text"`

#### Scenario: isUUID

- **WHEN** `isUUID()` is tested against a valid UUID's string form and
  against `"invalid"`
- **THEN** it accepts the valid UUID text and rejects `"invalid"`

### Requirement: A blank string counts as empty, independent of reference nullness
`req~string-predicates.blank-and-null~1`

`isEmpty()` trims the text before checking `String.isEmpty()`, so a
whitespace-only string counts as empty, not only a zero-length one;
`isNotEmpty()` negates it. `isNull()`/`isNotNull()` separately check
reference nullness via `Objects::isNull`/`Objects::nonNull`.

#### Scenario: A whitespace-only string is empty

- **WHEN** `isEmpty()` is tested against `" "` and against `""`
- **THEN** it accepts both

#### Scenario: Non-blank text is not empty

- **WHEN** `isEmpty()` is tested against `"a"`
- **THEN** it rejects `"a"`

#### Scenario: isNull

- **WHEN** `isNull()` is tested against `null` and against `"a"`
- **THEN** it accepts `null` and rejects `"a"`

#### Scenario: isNotNull

- **WHEN** `isNotNull()` is tested against `null` and against `"a"`
- **THEN** it rejects `null` and accepts `"a"`
