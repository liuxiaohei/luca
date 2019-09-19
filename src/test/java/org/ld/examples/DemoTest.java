package org.ld.examples;

import org.junit.Test;
import org.ld.enums.ErrorCodeEnum;
import org.ld.exception.ErrorCode;

import java.net.PasswordAuthentication;
import java.util.Properties;

import java.util.Date;


import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class DemoTest {

    @Test
    public void demo() {
        System.out.println(new ErrorCode(ErrorCodeEnum.SUCCESS()).getMessage());
    }

    @Test
    public void sendMail() {

    }
}
