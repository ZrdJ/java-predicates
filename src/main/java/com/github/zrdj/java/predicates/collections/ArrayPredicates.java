package com.github.zrdj.java.predicates.collections;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Predicate;

public interface ArrayPredicates {
    <T> Predicate<T[]> hasLength(final int length);

    <T> Predicate<T[]> hasLengthGreaterThan(int min);

    <T> Predicate<T[]> hasLengthLessThan(int max);

    <T> Predicate<T[]> hasLengthEqualOrGreaterThan(int min);

    <T> Predicate<T[]> hasLengthEqualOrLessThan(int max);

    <T> Predicate<T[]> equals(final T[] inner);

    <T> Predicate<T[]> contains(final T element);

    <T> Predicate<T[]> containsAllOf(final T... elements);

    <T> Predicate<T[]> containsAnyOf(final T... elements);

    <T> Predicate<T[]> containsNoneOf(final T... elements);

    <T> Predicate<T[]> isEmpty();

    <T> Predicate<T[]> isNotEmpty();

    final class Default implements ArrayPredicates {

        @Override
        public <T> Predicate<T[]> hasLength(int length) {
            return outer -> outer.length == length;
        }

        @Override
        public <T> Predicate<T[]> hasLengthGreaterThan(int min) {
            return outer -> outer.length > min;
        }

        @Override
        public <T> Predicate<T[]> hasLengthLessThan(int max) {
            return outer -> outer.length < max;
        }

        @Override
        public <T> Predicate<T[]> hasLengthEqualOrGreaterThan(int min) {
            return outer -> outer.length >= min;
        }

        @Override
        public <T> Predicate<T[]> hasLengthEqualOrLessThan(int max) {
            return outer -> outer.length <= max;
        }

        @Override
        public <T> Predicate<T[]> equals(T[] inner) {
            return outer -> Arrays.equals(outer, inner);
        }

        @Override
        public <T> Predicate<T[]> contains(T element) {
            return outer -> Arrays.asList(outer).contains(element);
        }

        @Override
        public <T> Predicate<T[]> containsAllOf(T[] elements) {
            return outer -> new HashSet<>(Arrays.asList(outer)).containsAll(Arrays.asList(elements));
        }

        @Override
        public <T> Predicate<T[]> containsAnyOf(T[] elements) {
            return outer -> Arrays.stream(outer).anyMatch(Arrays.asList(elements)::contains);
        }

        @Override
        public <T> Predicate<T[]> containsNoneOf(T[] elements) {
            return outer -> !containsAnyOf(elements).test(outer);
        }

        @Override
        public <T> Predicate<T[]> isEmpty() {
            return outer -> outer.length == 0;
        }

        @Override
        public <T> Predicate<T[]> isNotEmpty() {
            return outer -> isEmpty().negate().test(outer);
        }
    }
}
