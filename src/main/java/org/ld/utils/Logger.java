package org.ld.utils;

import org.ld.exception.CodeException;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.function.Supplier;


/**
 */
@SuppressWarnings("unused")
public class Logger {
    private final org.slf4j.Logger logger;

    private Logger(Class clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    public static Logger newInstance(Class clazz) {
        return new Logger(clazz);
    }

    public static Logger newInstance() {
        try {
            return Logger.newInstance(ClassLoader.getSystemClassLoader().loadClass(Thread.currentThread().getStackTrace()[2].getClassName())); // 1 代表当前的栈帧 2 代表创建该线程的栈帧
        } catch (Exception e) {
            throw new CodeException(e);
        }
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
     */
    public void error(Callable<String> callable, Object... params) {
        try {
            if (logger.isErrorEnabled()) {
                logger.error(callable.call(), params);
            }
        } catch (Exception e) {
            throw new CodeException(e);
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
