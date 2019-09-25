package org.ld.functions;

@FunctionalInterface
public interface UncheckedSupplier<R> {
    R get() throws Throwable;
}
