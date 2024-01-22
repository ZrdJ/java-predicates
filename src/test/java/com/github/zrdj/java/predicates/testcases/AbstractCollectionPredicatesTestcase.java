package com.github.zrdj.java.predicates.testcases;

import com.github.zrdj.java.predicates.collections.CollectionPredicates;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jusecase.Builders.*;

public abstract class AbstractCollectionPredicatesTestcase {
    protected final CollectionPredicates checking;

    protected AbstractCollectionPredicatesTestcase() {
        checking = provideCollectionPredicates();
    }

    protected abstract CollectionPredicates provideCollectionPredicates();

    @Test
    public void testHasSize() {
        final List<Object> listWithTwoElements = a(list(of("two", "elements")));
        final List<Object> listWithOneElement = a(list(of("one element")));
        assertThat(checking.hasSize(1)).accepts(listWithOneElement).rejects(listWithTwoElements);
    }

    @Test
    public void testHasSizeGreaterThan() {
        final List<Object> listWithTwoElements = a(list(of("two", "elements")));
        final List<Object> listWithOneElement = a(list(of("one element")));
        assertThat(checking.hasSizeGreaterThan(1)).accepts(listWithTwoElements).rejects(listWithOneElement);
    }

    @Test
    public void testHasSizeLessThan() {
        final List<Object> emptyList = Collections.emptyList();
        final List<Object> listWithOneElement = a(list(of("one element")));
        assertThat(checking.hasSizeLessThan(1)).accepts(emptyList).rejects(listWithOneElement);
    }

    @Test
    public void testHasSizeEqualOrGreaterThan() {
        final List<Object> emptyList = Collections.emptyList();
        final List<Object> listWithOneElement = a(list(of("one element")));
        assertThat(checking.hasSizeEqualOrGreaterThan(1)).accepts(listWithOneElement).rejects(emptyList);
    }

    @Test
    public void testHasSizeEqualOrLessThan() {
        final List<Object> listWithTwoElements = a(list(of("two", "elements")));
        final List<Object> listWithOneElement = a(list(of("one element")));
        final List<Object> emptyList = Collections.emptyList();
        assertThat(checking.hasSizeEqualOrLessThan(1)).accepts(emptyList, listWithOneElement).rejects(listWithTwoElements);
    }

    @Test
    public void testEquals() {
        final List<String> listWithTwoElements = a(list(of("two", "elements")));
        final List<String> listWithOneElement = a(list(of("one element")));
        assertThat(checking.equals(listWithTwoElements)).accepts(listWithTwoElements).rejects(listWithOneElement);
    }

    @Test
    public void testContains() {
        final List<String> listWithOneElement = a(list(of("one element")));
        final List<String> emptyList = Collections.emptyList();
        assertThat(checking.contains("one element")).accepts(listWithOneElement).rejects(emptyList);
    }

    @Test
    public void testContainsAllOf() {
        final List<String> listWithTwoElements = a(list(of("two", "elements")));
        final List<String> listWithOneElement = a(list(of("one element")));
        final List<String> emptyList = Collections.emptyList();
        assertThat(checking.containsAllOf("two", "elements")).accepts(listWithTwoElements).rejects(emptyList, listWithOneElement);
    }

    @Test
    public void testContainsAnyOf() {
        final List<String> listWithTwoElements = a(list(of("two", "elements")));
        final List<String> listWithOneElement = a(list(of("one element")));
        final List<String> emptyList = Collections.emptyList();
        assertThat(checking.containsAnyOf("two", "one element")).accepts(listWithTwoElements, listWithOneElement).rejects(emptyList);
    }

    @Test
    public void testContainsNoneOf() {
        final List<String> listWithTwoElements = a(list(of("two", "elements")));
        final List<String> listWithOneElement = a(list(of("one element")));
        final List<String> emptyList = Collections.emptyList();
        assertThat(checking.containsNoneOf("two", "one element")).accepts(emptyList).rejects(listWithTwoElements, listWithOneElement);
    }

    @Test
    public void testEmpty() {
        final List<Object> emptyList = Collections.emptyList();
        final List<Object> listWithOneElement = a(list(of("one element")));
        assertThat(checking.isEmpty()).accepts(emptyList).rejects(listWithOneElement);
    }

    @Test
    public void testAny() {
        final List<Object> emptyList = Collections.emptyList();
        final List<Object> listWithOneElement = a(list(of("one element")));
        assertThat(checking.isNotEmpty()).accepts(listWithOneElement).rejects(emptyList);
    }
}