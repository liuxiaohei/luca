package org.ld.utils;

import org.ld.exception.CodeException;
import org.ld.functions.UncheckedSupplier;
import org.slf4j.LoggerFactory;

/**
 */
@SuppressWarnings("unused")
public class Logger {
    private final org.slf4j.Logger logger;

    private Logger(Class clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    /**
     * 不推荐使用
     *
     * @see #newInstance()
     */
    @Deprecated
    public static Logger newInstance(Class clazz) {
        return new Logger(clazz);
    }

    /**
     *
     */
    public static Logger newInstance() {
        try {
            return new Logger(ClassLoader.getSystemClassLoader().loadClass(Thread.currentThread().getStackTrace()[2].getClassName())); // 1 代表当前的栈帧 2 代表创建该线程的栈帧
        } catch (Exception e) {
            throw new CodeException(e);
        }
    }

    public void debug(UncheckedSupplier<String> supplier) {
        if (logger.isDebugEnabled()) {
            logger.debug(Try.of(supplier).get());
        }
    }

    public void debugThrowable(UncheckedSupplier<Throwable> supplier) {
        if (logger.isDebugEnabled()) {
            logger.debug("Error : ", Try.of(supplier).get());
        }
    }

    public void info(UncheckedSupplier<String> supplier) {
        if (logger.isInfoEnabled()) {
            logger.info(Try.of(supplier).get());
        }
    }

    /**
     */
    public void error(UncheckedSupplier<String> supplier, Object... params) {
        if (logger.isErrorEnabled()) {
            logger.error(Try.of(supplier).get(), params);
        }
    }

    /**
     * 打印异常信息
     */
    public void printStackTrace(Throwable e) {
        logger.info("Logger", e);
    }

    /**
     * 打印异常信息
     */
    public void printWarnStackTrace(Throwable e) {
        logger.warn("Logger", e);
    }

    /**
     * 打印异常信息
     */
    @Deprecated
    public void printDebugStackTrace(Throwable e) {
        logger.debug("Logger", e);
    }

}
