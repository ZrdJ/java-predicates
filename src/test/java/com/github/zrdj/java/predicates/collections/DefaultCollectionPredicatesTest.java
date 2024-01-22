package com.github.zrdj.java.predicates.collections;

import com.github.zrdj.java.predicates.Predicates;
import com.github.zrdj.java.predicates.testcases.AbstractCollectionPredicatesTestcase;

public class DefaultCollectionPredicatesTest extends AbstractCollectionPredicatesTestcase {
    @Override
    protected CollectionPredicates provideCollectionPredicates() {
        return new Predicates.Default().collection();
    }
}
