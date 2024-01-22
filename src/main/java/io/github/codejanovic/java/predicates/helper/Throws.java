package io.github.codejanovic.java.predicates.helper;

public abstract class Throws {
    public static boolean exception(final Action action) {
        try {
            action.execute();
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
