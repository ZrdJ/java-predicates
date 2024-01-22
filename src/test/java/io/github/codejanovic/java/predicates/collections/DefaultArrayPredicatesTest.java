package io.github.codejanovic.java.predicates.collections;

import io.github.codejanovic.java.predicates.Predicates;
import io.github.codejanovic.java.predicates.testcases.AbstractArrayPredicatesTestcase;

public class DefaultArrayPredicatesTest extends AbstractArrayPredicatesTestcase {
    @Override
    protected ArrayPredicates provideArrayPredicates() {
        return new Predicates.Default().array();
    }
}
