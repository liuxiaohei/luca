package org.ld.functions;

@FunctionalInterface
public interface UncheckedFunction<T, R> {
    R apply(T t) throws Throwable;
}
