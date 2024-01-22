package io.github.codejanovic.java.predicates.objects;

import io.github.codejanovic.java.predicates.Predicates;
import io.github.codejanovic.java.predicates.testcases.AbstractObjectPredicatesTestcase;

public class DefaultObjectPredicatesTest extends AbstractObjectPredicatesTestcase {
    @Override
    protected ObjectPredicates provideObjectPredicates() {
        return new Predicates.Default().object();
    }
}
