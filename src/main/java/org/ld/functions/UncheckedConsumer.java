package org.ld.functions;

@FunctionalInterface
public interface UncheckedConsumer<T> {
    void accept(T t) throws Throwable;
}
