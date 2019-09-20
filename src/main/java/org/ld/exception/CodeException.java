package org.ld.exception;


import scala.Enumeration;

@SuppressWarnings("unused")
public class CodeException extends RuntimeException {

    private ErrorCode errorCode;

    public CodeException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CodeException(ErrorCode errorCode, Exception e) {
        super(errorCode.getMessage(), e);
        this.errorCode = errorCode;
    }

    public CodeException(ErrorCode errorCode, String exceptionMsg, Exception e) {
        super(exceptionMsg, e);
        this.errorCode = errorCode;
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
