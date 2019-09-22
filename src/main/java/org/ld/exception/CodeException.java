package org.ld.exception;


import org.ld.enums.SystemErrorCodeEnum;
import scala.Enumeration;

@SuppressWarnings("unused")
public class CodeException extends RuntimeException {

    private ErrorCode errorCode;

    public CodeException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CodeException(Throwable e) {
        super(e.getMessage(), e);
        super.setStackTrace(e.getStackTrace());
        this.errorCode = new ErrorCode(ErrorCode.getSystemErrorValue(e).orElseGet(SystemErrorCodeEnum::UNKNOW));
    }

    public Enumeration.Value getValue() {
        return errorCode.getValue();
    }

    public static void throwException(ErrorCode info) {
        throw new CodeException(info);
    }

    public static void throwException(Enumeration.Value value) {
        throw new CodeException(new ErrorCode(value));
    }
}
