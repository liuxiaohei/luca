package org.ld.utils;

import org.ld.beans.ResponseBodyBean;
import org.ld.enums.SystemErrorCodeEnum;
import org.ld.exception.CodeStackException;
import org.ld.functions.UncheckedSupplier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import scala.Enumeration;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import java.util.stream.Stream;

public class ControllerUtil {

    private static final Logger LOG = Logger.newInstance();

    /**
     * 转换响应结构体
     */
    public static Object convertResponseBody(UncheckedSupplier point) {
        try {
            final ResponseBodyBean<Object> result = new ResponseBodyBean<>();
            result.setData(point.get());
            result.setErrorCode(SystemErrorCodeEnum.SUCCESS().id());
            result.setMessage(SystemErrorCodeEnum.SUCCESS().toString());
            LOG.info(() -> "Response Body : " + JsonUtil.obj2Json(result));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Throwable e) {
            LOG.printStackTrace(e);
            final CodeStackException se = Optional.of(e)
                    .map(t -> {
                        Throwable t1 = t;
                        while (null != t1) {
                            if (t1 instanceof CodeStackException) return (CodeStackException) t1;//找到第一个CodeException
                            t1 = t1.getCause();
                        }
                        return null;
                    })
                    .orElseGet(() -> SystemErrorCodeEnum.getSystemError(e));
            final ResponseBodyBean<Object> result = new ResponseBodyBean<>();
            result.setErrorCode(Optional.of(se)
                    .map(CodeStackException::getValue)
                    .map(Enumeration.Value::id)
                    .orElseGet(SystemErrorCodeEnum.UNKNOWN()::id));
            result.setStackTrace(Optional.of(e)
                    .map(error -> {
                        final StringWriter sw = new StringWriter();
                        e.printStackTrace(new PrintWriter(sw));
                        return Stream.of(sw.toString().split("\n\t"))
                                .skip(1)
                                .map(str -> str.replace("\n", ""))
                                .toArray(String[]::new);
                    }).orElse(null));
            result.setMessage(Optional.of(se)
                    .map(CodeStackException::getValue)
                    .filter(value -> !value.equals(SystemErrorCodeEnum.UNKNOWN()))
                    .map(Enumeration.Value::toString)
                    .orElseGet(e::getMessage));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
