---
type: index
title: java-predicates — Knowledge Layer
lang: en
updated: 2026-08-31
---

# java-predicates — Knowledge Layer

Collection of predefined, chainable `Predicate<T>` implementations for primitives,
strings, arrays and collections, addressed through a fluent `Predicates` entry point
instead of hand-written boolean expressions.

Repo-specific knowledge. What concerns more than this repo lives in the knowledge layer
of the WS root (`~/workspaces/personal/docs/`).

## Folders

- `project/decisions/` — why things are the way they are (ADRs)
- `project/worklog/` — work logs, one file per day
- `project/research/` — self-collected material
- `project/sources/` — material delivered by others
- `wayfinding/` — undertakings whose path is not yet settled
- `changes/` — ongoing undertakings whose path is settled
- `archive/` — completed changes
- `specs/` — current state per capability

## Entry points

- `pom.xml` — coordinates (`com.github.zrdj:java-predicates`), Java 11, JUnit4/AssertJ test deps
- `src/main/java/com/github/zrdj/java/predicates/Predicates.java` — fluent entry point (`Predicates.Default`)
- `src/main/java/com/github/zrdj/java/predicates/primitives/StringPredicates.java` — string-specific predicates used in the README example
- `src/test/java/com/github/zrdj/java/predicates/PredicatesTest.java` — usage examples as tests
- `README.md` (repo root) — motivation and the fluent-chaining example

This repo does not (yet) have its own `CLAUDE.md` — working rules apply from
`zrdj/CLAUDE.md` and the provider levels above it.
