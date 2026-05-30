package com.github.zrdj.java.predicates.helper;

public final class Throws {
    private Throws() {}

    public static boolean exception(final Action action) {
        try {
            action.execute();
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
