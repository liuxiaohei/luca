package org.ld.utils;

import org.ld.exception.CodeException;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;


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

    public void debug(Callable<String> callable) {
        if (logger.isDebugEnabled()) {
            logger.debug(Try.of(callable).get());
        }
    }

    public void debugThrowable(Callable<Throwable> callable) {
        if (logger.isDebugEnabled()) {
            logger.debug("Error : ", Try.of(callable).get());
        }
    }

    public void info(Callable<String> callable) {
        if (logger.isInfoEnabled()) {
            logger.info(Try.of(callable).get());
        }
    }

    /**
     */
    public void error(Callable<String> callable, Object... params) {
        if (logger.isErrorEnabled()) {
            logger.error(Try.of(callable).get(), params);
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
