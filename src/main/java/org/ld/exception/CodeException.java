package org.ld.exception;


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

    public static void throwException(ErrorCode info) {
        throw new CodeException(info);
    }

}
