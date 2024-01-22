package io.github.codejanovic.java.predicates.primitives;

import io.github.codejanovic.java.predicates.helper.Throws;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

public interface StringPredicates {
    Predicate<String> equals(final String inner);

    Predicate<String> equalsIgnoreCase(final String inner);

    Predicate<String> startsWith(final String inner);

    Predicate<String> endsWith(final String inner);

    Predicate<String> startsWithIgnoreCase(final String inner);

    Predicate<String> endsWithIgnoreCase(final String inner);

    Predicate<String> hasLengthGreaterThan(final int minLength);

    Predicate<String> hasLengthLessThan(final int maxLength);

    Predicate<String> hasLengthEqualOrGreaterThan(final int minLength);

    Predicate<String> hasLengthEqualOrLessThan(final int maxLength);

    Predicate<String> hasLength(final int length);

    Predicate<String> isBoolean();

    Predicate<String> isInteger();

    Predicate<String> isUUID();

    Predicate<String> contains(final String inner);

    Predicate<String> containsIgnoreCase(final String inner);

    Predicate<String> isEmpty();

    Predicate<String> isNotEmpty();

    Predicate<String> isNull();

    Predicate<String> isNotNull();

    final class Default implements StringPredicates {
        public Predicate<String> equals(final String inner) {
            return inner::equals;
        }

        public Predicate<String> equalsIgnoreCase(final String inner) {
            return inner::equalsIgnoreCase;
        }

        public Predicate<String> startsWith(final String inner) {
            return outer -> outer.startsWith(inner);
        }

        public Predicate<String> endsWith(final String inner) {
            return outer -> outer.endsWith(inner);
        }

        //TODO: needs an alternative implementation due to locale issues on toLowerCase()
        public Predicate<String> startsWithIgnoreCase(final String inner) {
            return outer -> outer.toLowerCase().startsWith(inner.toLowerCase());
        }

        //TODO: needs an alternative implementation due to locale issues on toLowerCase()
        public Predicate<String> endsWithIgnoreCase(final String inner) {
            return outer -> outer.toLowerCase().endsWith(inner.toLowerCase());
        }

        public Predicate<String> hasLengthGreaterThan(final int minLength) {
            return outer -> outer.length() > minLength;
        }

        public Predicate<String> hasLengthLessThan(final int maxLength) {
            return outer -> outer.length() < maxLength;
        }

        @Override
        public Predicate<String> hasLengthEqualOrGreaterThan(int minLength) {
            return outer -> outer.length() >= minLength;
        }

        @Override
        public Predicate<String> hasLengthEqualOrLessThan(int maxLength) {
            return outer -> outer.length() <= maxLength;
        }

        public Predicate<String> hasLength(final int length) {
            return outer -> outer.length() == length;
        }

        public Predicate<String> isBoolean() {
            return outer -> equalsIgnoreCase("true").or(equalsIgnoreCase("false")).test(outer);
        }

        public Predicate<String> isInteger() {
            return outer -> !Throws.exception(() -> Integer.parseInt(outer));
        }

        public Predicate<String> isUUID() {
            return outer -> !Throws.exception(() -> UUID.fromString(outer));
        }

        public Predicate<String> contains(final String inner) {
            return outer -> outer.contains(inner);
        }

        //TODO: needs an alternative implementation due to locale issues on toLowerCase()
        public Predicate<String> containsIgnoreCase(final String inner) {
            return outer -> outer.toLowerCase().contains(inner.toLowerCase());
        }

        @Override
        public Predicate<String> isEmpty() {
            return outer -> outer.trim().isEmpty();
        }

        @Override
        public Predicate<String> isNotEmpty() {
            return outer -> isEmpty().negate().test(outer);
        }

        @Override
        public Predicate<String> isNull() {
            return Objects::isNull;
        }

        @Override
        public Predicate<String> isNotNull() {
            return Objects::nonNull;
        }
    }
}
