package org.ld.exception;


import org.ld.enums.SystemErrorCodeEnum;
import org.ld.utils.ExceptionUtil;
import org.ld.utils.Logger;
import scala.Enumeration;

/**
 * ld
 */
@SuppressWarnings("unused")
public class CodeStackException extends RuntimeException {

    private static Logger logger = Logger.newInstance();

    private ErrorCode errorCode;

    public CodeStackException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CodeStackException(Throwable e) {
        super(e.getMessage(), e);
        super.setStackTrace(e.getStackTrace());
        this.errorCode = new ErrorCode(ExceptionUtil.getSystemErrorValue(e)
                .filter(code -> !(e instanceof CodeStackException))
                .filter(code -> {
                    logger.info(() -> "ErrorCode:" + code.id() + " Reason:" + code.toString());
                    return true;
                })
                .orElseGet(SystemErrorCodeEnum::UNKNOW));
    }

    public Enumeration.Value getValue() {
        return errorCode.getValue();
    }

    public static void throwException(ErrorCode info) {
        throw new CodeStackException(info);
    }

    public static void throwException(Enumeration.Value value) {
        throw new CodeStackException(new ErrorCode(value));
    }

    public static void throwException(Throwable e) {
        throw new CodeStackException(e);
    }
}
