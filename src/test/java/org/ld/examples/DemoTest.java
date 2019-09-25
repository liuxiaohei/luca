package org.ld.examples;

import org.junit.Test;
import org.ld.enums.SystemErrorCodeEnum;
import org.ld.exception.ErrorCode;
import org.ld.utils.Logger;
import org.ld.utils.UuidUtils;

import java.util.stream.Stream;

public class DemoTest {

    @Test
    public void demo() {
        System.out.println(new ErrorCode(SystemErrorCodeEnum.SUCCESS()).getMessage());
    }

    @Test
    public void sendMail() {

    }

    /**
     * 无限流
     */
    @Test
    public void infiniteStream() {
        //Stream.iterate(0, i -> ++i).limit(1000).forEach(e -> Logger.newInstance().info(() -> "" + e));
        Stream.generate(UuidUtils::getShortUuid).limit(1000).forEach(e -> Logger.newInstance().info(() -> "" + e));
    }
}
