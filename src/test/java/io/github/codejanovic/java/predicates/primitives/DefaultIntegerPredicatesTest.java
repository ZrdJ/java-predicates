package io.github.codejanovic.java.predicates.primitives;

import io.github.codejanovic.java.predicates.Predicates;
import io.github.codejanovic.java.predicates.testcases.AbstractIntegerPredicatesTestcase;

public class DefaultIntegerPredicatesTest extends AbstractIntegerPredicatesTestcase {
    @Override
    protected IntegerPredicates provideIntegerPredicates() {
        return new Predicates.Default().integer();
    }
}