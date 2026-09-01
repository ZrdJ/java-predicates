package com.github.zrdj.java.predicates.testcases;

import com.github.zrdj.java.predicates.objects.ObjectPredicates;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public abstract class AbstractObjectPredicatesTestcase {
    protected final ObjectPredicates checking;

    protected AbstractObjectPredicatesTestcase() {
        checking = provideObjectPredicates();
    }

    protected abstract ObjectPredicates provideObjectPredicates();

    // [impl->req~predicates-facade.generic-nullness~1]
    @Test
    public void testIsNull() {
        assertThat(checking.isNull().test(null)).isTrue();
        assertThat(checking.isNull().test(new Object())).isFalse();
    }

    // [impl->req~predicates-facade.generic-nullness~1]
    @Test
    public void testIsNotNull() {
        assertThat(checking.isNotNull().test(null)).isFalse();
        assertThat(checking.isNotNull().test(new Object())).isTrue();
    }
}