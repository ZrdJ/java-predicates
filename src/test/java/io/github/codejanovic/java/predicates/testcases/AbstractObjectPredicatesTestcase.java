package io.github.codejanovic.java.predicates.testcases;

import io.github.codejanovic.java.predicates.objects.ObjectPredicates;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public abstract class AbstractObjectPredicatesTestcase {
    protected final ObjectPredicates checking;

    protected AbstractObjectPredicatesTestcase() {
        checking = provideObjectPredicates();
    }

    protected abstract ObjectPredicates provideObjectPredicates();

    @Test
    public void testIsNull() {
        assertThat(checking.isNull().test(null)).isTrue();
        assertThat(checking.isNull().test(new Object())).isFalse();
    }

    @Test
    public void testIsNotNull() {
        assertThat(checking.isNotNull().test(null)).isFalse();
        assertThat(checking.isNotNull().test(new Object())).isTrue();
    }
}