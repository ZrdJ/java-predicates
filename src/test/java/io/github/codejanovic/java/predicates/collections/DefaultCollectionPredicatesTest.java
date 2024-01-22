package io.github.codejanovic.java.predicates.collections;

import io.github.codejanovic.java.predicates.Predicates;
import io.github.codejanovic.java.predicates.testcases.AbstractCollectionPredicatesTestcase;

public class DefaultCollectionPredicatesTest extends AbstractCollectionPredicatesTestcase {
    @Override
    protected CollectionPredicates provideCollectionPredicates() {
        return new Predicates.Default().collection();
    }
}
