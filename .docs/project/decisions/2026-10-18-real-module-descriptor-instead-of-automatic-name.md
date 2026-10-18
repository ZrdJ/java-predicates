---
type: decision
title: A real module descriptor, not an automatic module name
updated: 2026-10-18
status: accepted
origin: []
---

## Context

`tibbots/bots` wants to use the zrdj libraries from its named JPMS modules, so all of them get a
real module descriptor in one sweep; `java-properties` went first
(`zrdj/java-properties`, pull request #18), and this repo follows the same model. A named module can
only `requires` another named module. A plain jar without a descriptor still works there as an
automatic module, but its name is derived from the jar file name instead of being chosen.

Until now this library shipped no `module-info.class` and no `Automatic-Module-Name` manifest entry.
Absent both, the JPMS file name algorithm turns `java-predicates-0.5.2.jar` into the automatic module
name `java.predicates` — a name in the `java.*` namespace the JDK reserves for platform modules. Such
a name compiles and runs today as long as the consumer starts from the classpath, where the runtime
module system never resolves it; it is a latent failure, not a current one.

## Decision

**Ship a real `module-info.java`, module name `com.github.zrdj.java.predicates`, matching the root
package.** No `Automatic-Module-Name` fallback: that entry would need the same non-`java.*` value
anyway, and a real descriptor additionally states the module's dependencies and its public surface
explicitly.

The module has no `requires` clause beyond the implicit `java.base`: the only dependencies in
`pom.xml` are JUnit 4 and AssertJ, both in test scope, and no main source file imports anything
outside `java.*` and the library's own packages.

All five packages are exported:

- `com.github.zrdj.java.predicates` — `Predicates`, the fluent entry point
- `com.github.zrdj.java.predicates.collections` — `ArrayPredicates`, `CollectionPredicates`
- `com.github.zrdj.java.predicates.helper` — `Action`, `Throws`
- `com.github.zrdj.java.predicates.objects` — `ObjectPredicates`
- `com.github.zrdj.java.predicates.primitives` — `IntegerPredicates`, `StringPredicates`

## Rationale

- **The derived name sits in a reserved namespace.** The first module-path start, `jlink` image or
  JDK that tightens the check would turn a consumer's green build red, and no test in either repo
  would catch it beforehand, because the descriptor is the only place the name appears.
- **A descriptor is also documentation.** `requires java.predicates;` in a consumer reads as a
  platform module; `requires com.github.zrdj.java.predicates;` reads as what it is.
- **Every package is exported, none held back.** Today every package is reachable on the classpath,
  and its classes are public. The predicate packages are returned from the `Predicates` entry point,
  and even `helper`, which looks internal, holds public types (`Action`, `Throws`) that some consumer
  may already use directly. Hiding a package would break such a consumer as soon as it moves to the
  module path, for no gain this decision needs.

## Consequences

- `pom.xml`'s `<version>` moves from `0.5.2` to `0.6.0`, a minor rather than a patch step because
  this changes how JPMS consumers name the library. The README's dependency snippet follows to
  `0.6.0`.
- `maven.compiler.source`/`target` stay at `11`. JPMS exists since Java 9, and `maven-compiler-plugin`
  3.16.0 detects `module-info.java` and compiles it without `<release>`.
- `maven-surefire-plugin` is pinned to 3.6.0, the version CI's Maven resolves, because with a
  descriptor surefire 3.x runs the tests on the module path while the dev container's default 2.12.4
  ran them on the classpath and so hid the failure; on the module path the test-only package
  `testcases` is opened to JUnit 4 via `--add-opens` in surefire's `argLine`.
- Classpath consumers are unaffected: a modular jar read from the classpath behaves as before.
- Consumers on `0.5.2` keep working. This release removes a latent problem; nothing about adopting
  it is urgent.
