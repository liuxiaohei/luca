package org.ld.exception;

import org.ld.beans.ValueBean;

@SuppressWarnings("unused")
public class ErrorCode {

    private int code;

    private String msg;

    private ValueBean value;

    public ValueBean getValue() {
        return value;
    }

    public ErrorCode(ValueBean value) {
        this.value = value;
        this.code = value.id();
        this.msg = value.value();
    }

    public String getMessage() {
        return msg;
    }

    public int getCode() {
        return code;
    }

}
