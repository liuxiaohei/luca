package org.ld.examples;

import org.junit.Test;
import org.ld.enums.SystemErrorCodeEnum;
import org.ld.exception.ErrorCode;

public class DemoTest {

    @Test
    public void demo() {
        System.out.println(new ErrorCode(SystemErrorCodeEnum.SUCCESS()).getMessage());
    }

    @Test
    public void sendMail() {

    }
}
