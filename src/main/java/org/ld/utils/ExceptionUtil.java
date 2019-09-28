package org.ld.utils;

import org.ld.enums.SystemErrorCodeEnum;
import org.ld.exception.CodeStackException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import scala.Enumeration;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * 异常相关工具
 */
public class ExceptionUtil {

    private static final Map<Class, Enumeration.Value> EXCEPTIONS = new HashMap<>();

    static {
        EXCEPTIONS.put(NullPointerException.class, SystemErrorCodeEnum.NULL_POINTER_EXCEPTION());
        EXCEPTIONS.put(NumberFormatException.class, SystemErrorCodeEnum.PARAMS_INVALID());
        EXCEPTIONS.put(HttpMessageNotReadableException.class, SystemErrorCodeEnum.PARAMS_INVALID());
        EXCEPTIONS.put(MethodArgumentNotValidException.class, SystemErrorCodeEnum.PARAMS_INVALID());
        EXCEPTIONS.put(SQLException.class, SystemErrorCodeEnum.SQL_EXCEPTION());
        EXCEPTIONS.put(DataAccessException.class, SystemErrorCodeEnum.DATA_ACCESS_FAILED());
    }

    private static AtomicBoolean merged = new AtomicBoolean(false);

    /**
     * 添加自定义Exception判断规则(只有第一次运行时才生效)
     * todo 用Akka实现
     */
    @Deprecated
    public synchronized static void mergeExceptionRule(Consumer<Map<Class, Enumeration.Value>> exceptionsConsumer) {
        if (!merged.get()) {
            exceptionsConsumer.accept(EXCEPTIONS);
            merged.set(true);
        }
    }

    /**
     * 只运行一次 添加自定义Exception判断
     */
    @Deprecated
    public static void mergeExceptionRule(Map<Class, Enumeration.Value> newExceptions) {
        mergeExceptionRule(exceptions -> newExceptions.forEach(exceptions::put));
    }

    public static Optional<Enumeration.Value> getSystemErrorValue(Throwable e) {
        if (e instanceof CodeStackException) {
            return Optional.of((CodeStackException) e).map(CodeStackException::getValue);
        }
        return EXCEPTIONS.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isInstance(e))
                .map(Map.Entry::getValue)
                .findFirst();
    }
}
