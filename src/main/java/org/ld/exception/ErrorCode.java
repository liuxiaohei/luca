package org.ld.exception;

import scala.Enumeration;

@SuppressWarnings("unused")
public class ErrorCode {

    private int code;

    private String msg;

    public ErrorCode(Enumeration.Value value) {
        this.code = value.id();
        this.msg = value.toString();
    }

    public String getMessage() {
        return msg;
    }

    public int getCode() {
        return code;
    }

}
