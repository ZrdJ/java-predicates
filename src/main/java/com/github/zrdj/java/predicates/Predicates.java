package com.github.zrdj.java.predicates;

import com.github.zrdj.java.predicates.collections.ArrayPredicates;
import com.github.zrdj.java.predicates.collections.CollectionPredicates;
import com.github.zrdj.java.predicates.objects.ObjectPredicates;
import com.github.zrdj.java.predicates.primitives.IntegerPredicates;
import com.github.zrdj.java.predicates.primitives.StringPredicates;

import java.util.function.Predicate;
import java.util.stream.Stream;

public interface Predicates {
    StringPredicates string();

    IntegerPredicates integer();

    ArrayPredicates array();

    ObjectPredicates object();

    CollectionPredicates collection();

    default <T> boolean anyMatch(Predicate<T> spec, T... elements) {
        return Stream.of(elements).anyMatch(spec);
    }

    default <T> boolean noneMatch(Predicate<T> spec, T... elements) {
        return Stream.of(elements).noneMatch(spec);
    }

    default <T> boolean allMatch(Predicate<T> spec, T... elements) {
        return Stream.of(elements).allMatch(spec);
    }

    default Predicates let() {
        return this;
    }

    default Predicates ensure() {
        return this;
    }

    default Predicates check() {
        return this;
    }

    default Predicates that() {
        return this;
    }

    default Predicates when() {
        return this;
    }

    default Predicates valid() {
        return this;
    }

    final class Default implements Predicates {
        private final StringPredicates stringPredicates;
        private final IntegerPredicates integerPredicates;
        private final ArrayPredicates arrayPredicates;
        private final ObjectPredicates objectPredicates;
        private final CollectionPredicates collectionPredicates;

        public Default(StringPredicates stringPredicates, IntegerPredicates integerPredicates, ArrayPredicates arrayPredicates, ObjectPredicates objectPredicates, CollectionPredicates collectionPredicates) {
            this.stringPredicates = stringPredicates;
            this.integerPredicates = integerPredicates;
            this.arrayPredicates = arrayPredicates;
            this.objectPredicates = objectPredicates;
            this.collectionPredicates = collectionPredicates;
        }

        public Default() {
            this(new StringPredicates.Default(), new IntegerPredicates.Default(), new ArrayPredicates.Default(), new ObjectPredicates.Default(), new CollectionPredicates.Default());
        }

        @Override
        public StringPredicates string() {
            return stringPredicates;
        }

        @Override
        public IntegerPredicates integer() {
            return integerPredicates;
        }

        @Override
        public ArrayPredicates array() {
            return arrayPredicates;
        }

        @Override
        public ObjectPredicates object() {
            return objectPredicates;
        }

        @Override
        public CollectionPredicates collection() {
            return collectionPredicates;
        }
    }
}
