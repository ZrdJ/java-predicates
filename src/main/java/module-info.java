// The name javac would derive from the jar file name, "java.predicates", lies in the java.*
// namespace the JDK reserves for platform modules, so the module names itself after its root package.
module com.github.zrdj.java.predicates {
    exports com.github.zrdj.java.predicates;
    exports com.github.zrdj.java.predicates.collections;
    exports com.github.zrdj.java.predicates.helper;
    exports com.github.zrdj.java.predicates.objects;
    exports com.github.zrdj.java.predicates.primitives;
}
