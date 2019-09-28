package org.ld.exception;

import scala.Enumeration;

@SuppressWarnings("unused")
public class ErrorCode {

    private int code;

    private String msg;

    private Enumeration.Value value;

    public Enumeration.Value getValue() {
        return value;
    }

    public ErrorCode(Enumeration.Value value) {
        this.value = value;
        this.code = value.id();
        this.msg = value.toString();
    }

    /**
     * 临时重设错误文案
     */
    @Deprecated
    public ErrorCode reSetMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getMessage() {
        return msg;
    }

    public int getCode() {
        return code;
    }

}
