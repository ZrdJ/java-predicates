package com.github.zrdj.java.predicates.objects;

import com.github.zrdj.java.predicates.Predicates;
import com.github.zrdj.java.predicates.testcases.AbstractObjectPredicatesTestcase;

public class DefaultObjectPredicatesTest extends AbstractObjectPredicatesTestcase {
    @Override
    protected ObjectPredicates provideObjectPredicates() {
        return new Predicates.Default().object();
    }
}
