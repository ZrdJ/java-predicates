package com.github.zrdj.java.predicates.primitives;

import com.github.zrdj.java.predicates.Predicates;
import com.github.zrdj.java.predicates.testcases.AbstractStringPredicatesTestcase;

public class DefaultStringPredicatesTest extends AbstractStringPredicatesTestcase {
    @Override
    protected StringPredicates provideStringPredicates() {
        return new Predicates.Default().string();
    }
}
