package io.github.codejanovic.java.predicates.testcases;

import io.github.codejanovic.java.predicates.collections.ArrayPredicates;
import org.junit.Test;

import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractArrayPredicatesTestcase {
    protected final ArrayPredicates checking;

    protected AbstractArrayPredicatesTestcase() {
        checking = provideArrayPredicates();
    }

    protected abstract ArrayPredicates provideArrayPredicates();

    @Test
    public void testHasLength() {
        assertThat(checking.hasLength(3)).accepts(new Object[3]).rejects(new Object[2]);
    }

    @Test
    public void testHasLengthGreaterThan() {
        assertThat(checking.hasLengthGreaterThan(3)).accepts(new Object[4]).rejects(new Object[3]);
    }

    @Test
    public void testHasLengthLessThan() {
        assertThat(checking.hasLengthLessThan(3)).accepts(new Object[2]).rejects(new Object[3]);
    }

    @Test
    public void testHasLengthEqualOrGreaterThan() {
        assertThat(checking.hasLengthEqualOrGreaterThan(3)).accepts(new Object[3], new Object[4]).rejects(new Object[2]);
    }

    @Test
    public void testHasLengthEqualOrLessThan() {
        assertThat(checking.hasLengthEqualOrLessThan(3)).accepts(new Object[3], new Object[2]).rejects(new Object[4]);
    }

    @Test
    public void testEquals() {
        final Predicate<String[]> checking = this.checking.equals(new String[]{"one", "two"});
        assertThat(checking).accepts(new String[]{"one", "two"}).rejects(new String[]{"one", "two", "three"});
    }

    @Test
    public void testContains() {
        final Predicate<String[]> checking = this.checking.contains("one");
        assertThat(checking).accepts(new String[]{"one", "two"}).rejects(new String[]{"two", "three"});
    }

    @Test
    public void testContainsAllOf() {
        final Predicate<String[]> checking = this.checking.containsAllOf("one", "two");
        assertThat(checking).accepts(new String[]{"one", "two", "three"}).rejects(new String[]{"two", "three"});
    }

    @Test
    public void testContainsAnyOf() {
        final Predicate<String[]> checking = this.checking.containsAnyOf("one", "two");
        assertThat(checking).accepts(new String[]{"one", "three"}).rejects(new String[]{"three", "four"});
    }

    @Test
    public void testContainsNoneOf() {
        final Predicate<String[]> checking = this.checking.containsNoneOf("one", "two");
        assertThat(checking).accepts(new String[]{"three", "four"}).rejects(new String[]{"one", "four"});
    }

    @Test
    public void testEmpty() {
        assertThat(checking.isEmpty()).accepts(new Object[0]).rejects(new Object[1]);
    }

    @Test
    public void testAny() {
        assertThat(checking.isNotEmpty()).accepts(new Object[1]).rejects(new Object[0]);
    }


}