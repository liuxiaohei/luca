package org.ld.exception;


import org.ld.enums.SystemErrorCodeEnum;
import scala.Enumeration;

@SuppressWarnings("unused")
public class StackException extends RuntimeException {

    private ErrorCode errorCode;

    public StackException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public StackException(Throwable e) {
        super(e.getMessage(), e);
        super.setStackTrace(e.getStackTrace());
        this.errorCode = new ErrorCode(ErrorCode.getSystemErrorValue(e).orElseGet(SystemErrorCodeEnum::UNKNOW));
    }

    public Enumeration.Value getValue() {
        return errorCode.getValue();
    }

    public static void throwException(ErrorCode info) {
        throw new StackException(info);
    }

    public static void throwException(Enumeration.Value value) {
        throw new StackException(new ErrorCode(value));
    }

    public static void throwException(Throwable e) {
        throw new StackException(e);
    }
}
