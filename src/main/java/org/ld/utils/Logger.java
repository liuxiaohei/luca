package org.ld.utils;

import org.slf4j.LoggerFactory;

import java.util.function.Supplier;


/**
 */
public class Logger {
    private final org.slf4j.Logger logger;

    private Logger(Class clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    public static Logger newInstance(Class clazz) {
        return new Logger(clazz);
    }

    public void debug(Supplier<String> supplier) {
        if (logger.isDebugEnabled()) {
            logger.debug(supplier.get());
        }
    }

    public void debugThrowable(Supplier<Throwable> supplier) {
        if (logger.isDebugEnabled()) {
            logger.debug("Error : ", supplier.get());
        }
    }

    public void info(Supplier<String> supplier) {
        if (logger.isInfoEnabled()) {
            logger.info(supplier.get());
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
     *
     * @see #debugThrowable(Supplier)
     */
    @Deprecated
    public void printDebugStackTrace(Throwable e) {
        logger.debug("Logger", e);
    }

}
