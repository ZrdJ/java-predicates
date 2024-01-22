package io.github.codejanovic.java.predicates.primitives;

import io.github.codejanovic.java.predicates.Predicates;
import io.github.codejanovic.java.predicates.testcases.AbstractStringPredicatesTestcase;

public class DefaultStringPredicatesTest extends AbstractStringPredicatesTestcase {
    @Override
    protected StringPredicates provideStringPredicates() {
        return new Predicates.Default().string();
    }
}
