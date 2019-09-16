package org.ld.exception;


import org.ld.utils.I18N;

import java.util.*;

@SuppressWarnings("unused")
public class ErrorCode {

    private static final String BASE_NAME = "values/status_strings";

    private int code;

    private String msgKey;

    public ErrorCode(int code, String msgKey) {
        this.code = code;
        this.msgKey = msgKey;
    }

    public String getMessage() {
        return I18N.getLocalResource(Locale.getDefault(), BASE_NAME, msgKey);
    }

    public String getMsgKey() {
        return this.msgKey;
    }

    public int getCode() {
        return code;
    }

}
