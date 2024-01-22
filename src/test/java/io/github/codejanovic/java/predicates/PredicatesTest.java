package io.github.codejanovic.java.predicates;

import io.github.codejanovic.java.predicates.collections.CollectionPredicates;
import io.github.codejanovic.java.predicates.primitives.IntegerPredicates;
import io.github.codejanovic.java.predicates.primitives.StringPredicates;
import org.junit.Test;

import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jusecase.Builders.*;

public class PredicatesTest {
    private final Predicates well = new Predicates.Default();

    @Test
    public void testFluentIntegerPredicate() {
        final IntegerPredicates it = well.integer();
        assertThat(well.let().ensure().that().integer().isGreaterThan(5)
                .and(it.isLessThan(7))
                .and(it.isBetween(1, 10)))
                .accepts(6)
                .rejects(5, 7);
    }

    @Test
    public void testFluentStringPredicate() {
        final StringPredicates it = well.string();
        assertThat(well.let().check().that().string().isNotNull()
                .and(it.startsWith("one"))
                .and(it.endsWith("three")))
                .accepts("onetwothree")
                .rejects("onetwo");
    }

    @Test
    public void testFluentCollectionPredicate() {
        final CollectionPredicates it = well.collection();
        assertThat(well.let().ensure().valid().when().collection().isNotNull()
                .and(it.isNotEmpty())
                .and(it.containsAnyOf("one", "two"))
                .and(it.containsNoneOf("three", "four")))
                .accepts(a(list(of("one", "five", "ten"))))
                .rejects(null, a(list(of("one", "five", "ten", "four"))));
    }

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
}