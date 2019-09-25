package org.ld.functions;

@FunctionalInterface
public interface UncheckedSupplier<T, R> {
    R get() throws Throwable;
}
