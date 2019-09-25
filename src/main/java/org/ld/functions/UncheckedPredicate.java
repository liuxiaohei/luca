package org.ld.functions;

@FunctionalInterface
public interface UncheckedPredicate<T> {
    boolean test(T t) throws Throwable;
}
