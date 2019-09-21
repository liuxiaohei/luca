package org.ld.exception;


import org.ld.enums.ErrorCodeEnum;
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
        super.setStackTrace(e.getStackTrace()); // 替换StackTrace 避免调用过程中线索中断
        ErrorCode errorCode = new ErrorCode(ErrorCodeEnum.UNKNOW()); // todo 这里面增加 判断 自动根据错误生成异常码
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
