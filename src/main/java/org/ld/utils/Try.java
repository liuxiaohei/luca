package org.ld.utils;

import org.ld.exception.CodeException;
import org.ld.functions.UncheckedFunction;
import org.ld.functions.UncheckedSupplier;


import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 用于lambda表达式屏蔽受检异常
 *
 * @author ld
 */
@SuppressWarnings("unused")
public class Try {

    /**
     * 屏蔽受检异常
     */
    public static <T, R> Function<T, R> of(UncheckedFunction<T, R> mapper) {
        Objects.requireNonNull(mapper);
        return t -> {
            try {
                return mapper.apply(t);
            } catch (Throwable ex) {
                throw new CodeException(ex);
            }
        };
    }

    public static <T> Supplier<T> of(UncheckedSupplier<T> uncheckedSupplier) {
        Objects.requireNonNull(uncheckedSupplier);
        return () -> {
            try {
                return uncheckedSupplier.get();
            } catch (Throwable ex) {
                throw new CodeException(ex);
            }
        };
    }

    /**
     * 屏蔽受检异常 并给出默认值默认值
     */
    public static <T, R> Function<T, R> of(UncheckedFunction<T, R> mapper, Supplier<R> defaultValue) {
        Objects.requireNonNull(mapper);
        return t -> {
            try {
                return mapper.apply(t);
            } catch (Throwable ex) {
                return Optional.ofNullable(defaultValue).map(Supplier::get).orElse(null);
            }
        };
    }
}