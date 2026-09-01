package com.github.zrdj.java.predicates;

import com.github.zrdj.java.predicates.collections.ArrayPredicates;
import com.github.zrdj.java.predicates.collections.CollectionPredicates;
import com.github.zrdj.java.predicates.objects.ObjectPredicates;
import com.github.zrdj.java.predicates.primitives.IntegerPredicates;
import com.github.zrdj.java.predicates.primitives.StringPredicates;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public class PredicatesTest {
    private final Predicates well = new Predicates.Default();

    // [impl->req~predicates-facade.fluent-noop-words~1]
    @Test
    public void testFluentIntegerPredicate() {
        final IntegerPredicates it = well.integer();
        Assertions.assertThat(well.let().ensure().that().integer().isGreaterThan(5)
                .and(it.isLessThan(7))
                .and(it.isBetween(1, 10)))
                .accepts(6)
                .rejects(5, 7);
    }

    // [impl->req~predicates-facade.fluent-noop-words~1]
    @Test
    public void testFluentStringPredicate() {
        final StringPredicates it = well.string();
        Assertions.assertThat(well.let().check().that().string().isNotNull()
                .and(it.startsWith("one"))
                .and(it.endsWith("three")))
                .accepts("onetwothree")
                .rejects("onetwo");
    }

    // [impl->req~predicates-facade.fluent-noop-words~1]
    @Test
    public void testFluentCollectionPredicate() {
        final CollectionPredicates it = well.collection();
        Assertions.assertThat(well.let().ensure().valid().when().collection().isNotNull()
                .and(it.isNotEmpty())
                .and(it.containsAnyOf("one", "two"))
                .and(it.containsNoneOf("three", "four")))
                .accepts(List.of("one", "five", "ten"))
                .rejects(null, List.of("one", "five", "ten", "four"));
    }

    // [impl->req~predicates-facade.batch-matching~1]
    @Test
    public void testChainingPredicates() {
        final Predicate<String> spec = well.let().string().startsWith("a");
        final String[] elements = {"a", "b", "c", "d"};
        assertThat(well.check().that().anyMatch(spec, elements))
                .isTrue();
        assertThat(well.check().that().noneMatch(spec, elements))
                .isFalse();
        assertThat(well.check().that().allMatch(spec, elements))
                .isFalse();


    }

    // [impl->req~predicates-facade.default-wiring~1]
    @Test
    public void testDefaultWiring() {
        final Predicates checking = new Predicates.Default();
        assertThat(checking.string()).isInstanceOf(StringPredicates.Default.class);
        assertThat(checking.integer()).isInstanceOf(IntegerPredicates.Default.class);
        assertThat(checking.array()).isInstanceOf(ArrayPredicates.Default.class);
        assertThat(checking.object()).isInstanceOf(ObjectPredicates.Default.class);
        assertThat(checking.collection()).isInstanceOf(CollectionPredicates.Default.class);
    }

    // [impl->req~predicates-facade.fluent-noop-words~1]
    @Test
    public void testChainingWordsReturnSameInstance() {
        assertThat(well.let()).isSameAs(well);
        assertThat(well.ensure()).isSameAs(well);
        assertThat(well.check()).isSameAs(well);
        assertThat(well.that()).isSameAs(well);
        assertThat(well.when()).isSameAs(well);
        assertThat(well.valid()).isSameAs(well);
    }
}