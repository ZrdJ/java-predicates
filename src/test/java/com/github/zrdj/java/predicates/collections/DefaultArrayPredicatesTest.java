package com.github.zrdj.java.predicates.collections;

import com.github.zrdj.java.predicates.Predicates;
import com.github.zrdj.java.predicates.testcases.AbstractArrayPredicatesTestcase;

public class DefaultArrayPredicatesTest extends AbstractArrayPredicatesTestcase {
    @Override
    protected ArrayPredicates provideArrayPredicates() {
        return new Predicates.Default().array();
    }
}
