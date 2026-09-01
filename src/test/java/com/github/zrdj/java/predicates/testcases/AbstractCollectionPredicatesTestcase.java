package com.github.zrdj.java.predicates.testcases;

import com.github.zrdj.java.predicates.collections.CollectionPredicates;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractCollectionPredicatesTestcase {
    protected final CollectionPredicates checking;

    protected AbstractCollectionPredicatesTestcase() {
        checking = provideCollectionPredicates();
    }

    protected abstract CollectionPredicates provideCollectionPredicates();

    // [impl->req~collection-predicates.size-comparison~1]
    @Test
    public void testHasSize() {
        final List<Object> listWithTwoElements = List.of("two", "elements");
        final List<Object> listWithOneElement = List.of("one element");
        assertThat(checking.hasSize(1)).accepts(listWithOneElement).rejects(listWithTwoElements);
    }

    // [impl->req~collection-predicates.size-comparison~1]
    @Test
    public void testHasSizeGreaterThan() {
        final List<Object> listWithTwoElements = List.of("two", "elements");
        final List<Object> listWithOneElement = List.of("one element");
        assertThat(checking.hasSizeGreaterThan(1)).accepts(listWithTwoElements).rejects(listWithOneElement);
    }

    // [impl->req~collection-predicates.size-comparison~1]
    @Test
    public void testHasSizeLessThan() {
        final List<Object> emptyList = Collections.emptyList();
        final List<Object> listWithOneElement = List.of("one element");
        assertThat(checking.hasSizeLessThan(1)).accepts(emptyList).rejects(listWithOneElement);
    }

    // [impl->req~collection-predicates.size-comparison~1]
    @Test
    public void testHasSizeEqualOrGreaterThan() {
        final List<Object> emptyList = Collections.emptyList();
        final List<Object> listWithOneElement = List.of("one element");
        assertThat(checking.hasSizeEqualOrGreaterThan(1)).accepts(listWithOneElement).rejects(emptyList);
    }

    // [impl->req~collection-predicates.size-comparison~1]
    @Test
    public void testHasSizeEqualOrLessThan() {
        final List<Object> listWithTwoElements = List.of("two", "elements");
        final List<Object> listWithOneElement = List.of("one element");
        final List<Object> emptyList = Collections.emptyList();
        assertThat(checking.hasSizeEqualOrLessThan(1)).accepts(emptyList, listWithOneElement).rejects(listWithTwoElements);
    }

    // [impl->req~collection-predicates.equality~1]
    @Test
    public void testEquals() {
        final List<String> listWithTwoElements = List.of("two", "elements");
        final List<String> listWithOneElement = List.of("one element");
        assertThat(checking.equals(listWithTwoElements)).accepts(listWithTwoElements).rejects(listWithOneElement);
    }

    // [impl->req~collection-predicates.membership~1]
    @Test
    public void testContains() {
        final List<String> listWithOneElement = List.of("one element");
        final List<String> emptyList = Collections.emptyList();
        assertThat(checking.contains("one element")).accepts(listWithOneElement).rejects(emptyList);
    }

    // [impl->req~collection-predicates.membership~1]
    @Test
    public void testContainsAllOf() {
        final List<String> listWithTwoElements = List.of("two", "elements");
        final List<String> listWithOneElement = List.of("one element");
        final List<String> emptyList = Collections.emptyList();
        assertThat(checking.containsAllOf("two", "elements")).accepts(listWithTwoElements).rejects(emptyList, listWithOneElement);
    }

    // [impl->req~collection-predicates.membership~1]
    @Test
    public void testContainsAnyOf() {
        final List<String> listWithTwoElements = List.of("two", "elements");
        final List<String> listWithOneElement = List.of("one element");
        final List<String> emptyList = Collections.emptyList();
        assertThat(checking.containsAnyOf("two", "one element")).accepts(listWithTwoElements, listWithOneElement).rejects(emptyList);
    }

    // [impl->req~collection-predicates.membership~1]
    @Test
    public void testContainsNoneOf() {
        final List<String> listWithTwoElements = List.of("two", "elements");
        final List<String> listWithOneElement = List.of("one element");
        final List<String> emptyList = Collections.emptyList();
        assertThat(checking.containsNoneOf("two", "one element")).accepts(emptyList).rejects(listWithTwoElements, listWithOneElement);
    }

    // [impl->req~collection-predicates.emptiness-and-null~1]
    @Test
    public void testEmpty() {
        final List<Object> emptyList = Collections.emptyList();
        final List<Object> listWithOneElement = List.of("one element");
        assertThat(checking.isEmpty()).accepts(emptyList).rejects(listWithOneElement);
    }

    // [impl->req~collection-predicates.emptiness-and-null~1]
    @Test
    public void testAny() {
        final List<Object> emptyList = Collections.emptyList();
        final List<Object> listWithOneElement = List.of("one element");
        assertThat(checking.isNotEmpty()).accepts(listWithOneElement).rejects(emptyList);
    }

    // [impl->req~collection-predicates.emptiness-and-null~1]
    @Test
    public void testIsNull() {
        assertThat(checking.<Object>isNull().test(null)).isTrue();
        assertThat(checking.<Object>isNull().test(Collections.emptyList())).isFalse();
    }

    // [impl->req~collection-predicates.emptiness-and-null~1]
    @Test
    public void testIsNotNull() {
        assertThat(checking.<Object>isNotNull().test(null)).isFalse();
        assertThat(checking.<Object>isNotNull().test(Collections.emptyList())).isTrue();
    }
}