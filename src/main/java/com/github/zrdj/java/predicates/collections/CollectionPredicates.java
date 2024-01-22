package com.github.zrdj.java.predicates.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

public interface CollectionPredicates {
    <T> Predicate<Collection<T>> hasSize(final int size);

    <T> Predicate<Collection<T>> hasSizeGreaterThan(int min);

    <T> Predicate<Collection<T>> hasSizeLessThan(int max);

    <T> Predicate<Collection<T>> hasSizeEqualOrGreaterThan(int min);

    <T> Predicate<Collection<T>> hasSizeEqualOrLessThan(int max);

    <T> Predicate<Collection<T>> equals(final Collection<T> inner);

    <T> Predicate<Collection<T>> contains(final T element);

    <T> Predicate<Collection<T>> containsAllOf(final Collection<T> elements);

    <T> Predicate<Collection<T>> containsAllOf(final T... elements);

    <T> Predicate<Collection<T>> containsAnyOf(final Collection<T> elements);

    <T> Predicate<Collection<T>> containsAnyOf(final T... elements);

    <T> Predicate<Collection<T>> containsNoneOf(final Collection<T> elements);

    <T> Predicate<Collection<T>> containsNoneOf(final T... elements);

    <T> Predicate<Collection<T>> isEmpty();

    <T> Predicate<Collection<T>> isNotEmpty();

    <T> Predicate<Collection<T>> isNull();

    <T> Predicate<Collection<T>> isNotNull();


    final class Default implements CollectionPredicates {

        @Override
        public <T> Predicate<Collection<T>> hasSize(int size) {
            return outer -> outer.size() == size;
        }

        @Override
        public <T> Predicate<Collection<T>> hasSizeGreaterThan(int min) {
            return outer -> outer.size() > min;
        }

        @Override
        public <T> Predicate<Collection<T>> hasSizeLessThan(int max) {
            return outer -> outer.size() < max;
        }

        @Override
        public <T> Predicate<Collection<T>> hasSizeEqualOrGreaterThan(int min) {
            return outer -> outer.size() >= min;
        }

        @Override
        public <T> Predicate<Collection<T>> hasSizeEqualOrLessThan(int max) {
            return outer -> outer.size() <= max;
        }

        @Override
        public <T> Predicate<Collection<T>> equals(Collection<T> inner) {
            return inner::equals;
        }

        @Override
        public <T> Predicate<Collection<T>> contains(T element) {
            return outer -> outer.contains(element);
        }

        @Override
        public <T> Predicate<Collection<T>> containsAllOf(Collection<T> elements) {
            return outer -> elements.stream().allMatch(outer::contains);
        }

        @Override
        public <T> Predicate<Collection<T>> containsAllOf(T[] elements) {
            return outer -> containsAllOf(Arrays.asList((T[]) elements)).test(outer);
        }

        @Override
        public <T> Predicate<Collection<T>> containsAnyOf(Collection<T> elements) {
            return outer -> elements.stream().anyMatch(outer::contains);
        }

        @Override
        public <T> Predicate<Collection<T>> containsAnyOf(T[] elements) {
            return outer -> containsAnyOf(Arrays.asList((T[]) elements)).test(outer);
        }

        @Override
        public <T> Predicate<Collection<T>> containsNoneOf(Collection<T> elements) {
            return outer -> elements.stream().noneMatch(outer::contains);
        }

        @Override
        public <T> Predicate<Collection<T>> containsNoneOf(T[] elements) {
            return outer -> containsNoneOf(Arrays.asList((T[]) elements)).test(outer);
        }

        @Override
        public <T> Predicate<Collection<T>> isEmpty() {
            return Collection::isEmpty;
        }

        @Override
        public <T> Predicate<Collection<T>> isNotEmpty() {
            return outer -> !outer.isEmpty();
        }

        @Override
        public <T> Predicate<Collection<T>> isNull() {
            return Objects::isNull;
        }

        @Override
        public <T> Predicate<Collection<T>> isNotNull() {
            return Objects::nonNull;
        }

    }
}
