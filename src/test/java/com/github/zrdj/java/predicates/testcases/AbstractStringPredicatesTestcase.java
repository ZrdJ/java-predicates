package com.github.zrdj.java.predicates.testcases;

import com.github.zrdj.java.predicates.primitives.StringPredicates;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractStringPredicatesTestcase {
    protected final StringPredicates checking;

    public AbstractStringPredicatesTestcase() {
        checking = provideStringPredicates();
    }

    protected abstract StringPredicates provideStringPredicates();

    // [impl->req~string-predicates.case-sensitive-matching~1]
    @Test
    public void testEquals() {
        assertThat(checking.equals("one")).accepts("one").rejects("ONE", "TWO");
    }

    // [impl->req~string-predicates.case-insensitive-matching~1]
    @Test
    public void testEqualsIgnoreCase() {
        assertThat(checking.equalsIgnoreCase("one")).accepts("one", "ONE").rejects("two");
    }

    // [impl->req~string-predicates.case-sensitive-matching~1]
    @Test
    public void testStartsWith() {
        assertThat(checking.startsWith("one")).accepts("onetwo", "oneTWO").rejects("ONEtwo", "threefour");
    }

    // [impl->req~string-predicates.case-sensitive-matching~1]
    @Test
    public void testEndsWith() {
        assertThat(checking.endsWith("two")).accepts("onetwo", "ONEtwo").rejects("oneTWO", "threefour");
    }

    // [impl->req~string-predicates.case-insensitive-matching~1]
    @Test
    public void testStartsWithIgnoreCase() {
        assertThat(checking.startsWithIgnoreCase("one")).accepts("onetwo", "ONEtwo").rejects("threefour");
    }

    // [impl->req~string-predicates.case-insensitive-matching~1]
    @Test
    public void testEndsWithIgnoreCase() {
        assertThat(checking.endsWithIgnoreCase("two")).accepts("onetwo", "oneTWO").rejects("threefour");
    }

    // [impl->req~string-predicates.length-comparison~1]
    @Test
    public void testHasLengthGreaterThan() {
        assertThat(checking.hasLengthGreaterThan(1)).accepts("one").rejects("o", "");
    }

    // [impl->req~string-predicates.length-comparison~1]
    @Test
    public void testHasLengthLessThan() {
        assertThat(checking.hasLengthLessThan(1)).accepts("").rejects("one", "o");
    }

    // [impl->req~string-predicates.length-comparison~1]
    @Test
    public void testHasLengthEqualOrGreaterThan() {
        assertThat(checking.hasLengthEqualOrGreaterThan(1)).accepts("one", "o").rejects("");
    }

    // [impl->req~string-predicates.length-comparison~1]
    @Test
    public void testHasLengthEqualOrLessThan() {
        assertThat(checking.hasLengthEqualOrLessThan(1)).accepts("o", "").rejects("one");
    }

    // [impl->req~string-predicates.length-comparison~1]
    @Test
    public void testHasLength() {
        assertThat(checking.hasLength(1)).accepts("o").rejects("one", "");
    }

    // [impl->req~string-predicates.format-validity~1]
    @Test
    public void testIsBoolean() {
        assertThat(checking.isBoolean()).accepts("true", "false", "TRUE", "FALSE").rejects("isNotEmpty", "other", "text");
    }

    // [impl->req~string-predicates.format-validity~1]
    @Test
    public void testIsInteger() {
        assertThat(checking.isInteger()).accepts("1", "2", "3").rejects("isNotEmpty", "text");
    }

    // [impl->req~string-predicates.format-validity~1]
    @Test
    public void testIsUUID() {
        assertThat(checking.isUUID()).accepts(UUID.randomUUID().toString()).rejects("isNotEmpty", "invalid", "UUID");
    }

    // [impl->req~string-predicates.case-sensitive-matching~1]
    @Test
    public void testContains() {
        assertThat(checking.contains("two")).accepts("onetwothree").rejects("oneTWOthree");
    }

    // [impl->req~string-predicates.case-insensitive-matching~1]
    @Test
    public void testContainsIgnoreCase() {
        assertThat(checking.containsIgnoreCase("two")).accepts("onetwothree", "oneTWOthree").rejects("one2three");
    }

    // [impl->req~string-predicates.blank-and-null~1]
    @Test
    public void testEmpty() {
        assertThat(checking.isEmpty()).accepts(" ", "").rejects("a");
    }

    // [impl->req~string-predicates.blank-and-null~1]
    @Test
    public void testAny() {
        assertThat(checking.isNotEmpty()).accepts("a").rejects(" ", "");
    }

    // [impl->req~string-predicates.blank-and-null~1]
    @Test
    public void testIsNull() {
        assertThat(checking.isNull().test(null)).isTrue();
        assertThat(checking.isNull().test("a")).isFalse();
    }

    // [impl->req~string-predicates.blank-and-null~1]
    @Test
    public void testIsNotNull() {
        assertThat(checking.isNotNull().test(null)).isFalse();
        assertThat(checking.isNotNull().test("a")).isTrue();
    }

}