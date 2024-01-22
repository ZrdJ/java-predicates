package com.github.zrdj.java.predicates.primitives;

import java.util.function.Predicate;

public interface IntegerPredicates {
    Predicate<Integer> isGreaterThan(int min);

    Predicate<Integer> isLessThan(int max);

    Predicate<Integer> isEqualOrGreaterThan(int min);

    Predicate<Integer> isEqualOrLessThan(int max);

    Predicate<Integer> isBetween(int left, int right);

    Predicate<Integer> equals(int inner);

    final class Default implements IntegerPredicates {

        @Override
        public Predicate<Integer> isGreaterThan(int min) {
            return outer -> outer > min;
        }

        @Override
        public Predicate<Integer> isLessThan(int max) {
            return outer -> outer < max;
        }

        @Override
        public Predicate<Integer> isEqualOrGreaterThan(int min) {
            return outer -> outer >= min;
        }

        @Override
        public Predicate<Integer> isEqualOrLessThan(int max) {
            return outer -> outer <= max;
        }

        @Override
        public Predicate<Integer> isBetween(int left, int right) {
            return isGreaterThan(left).and(isLessThan(right));
        }

        @Override
        public Predicate<Integer> equals(int inner) {
            return outer -> outer == inner;
        }
    }
}
