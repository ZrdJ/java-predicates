package com.github.zrdj.java.predicates.primitives;

import com.github.zrdj.java.predicates.Predicates;
import com.github.zrdj.java.predicates.testcases.AbstractIntegerPredicatesTestcase;

public class DefaultIntegerPredicatesTest extends AbstractIntegerPredicatesTestcase {
    @Override
    protected IntegerPredicates provideIntegerPredicates() {
        return new Predicates.Default().integer();
    }
}