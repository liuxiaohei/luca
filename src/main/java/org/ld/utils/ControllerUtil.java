package org.ld.utils;

import org.ld.beans.ResponseBodyBean;
import org.ld.enums.SystemErrorCodeEnum;
import org.ld.exception.CodeStackException;
import org.ld.exception.ErrorCode;
import org.ld.functions.UncheckedSupplier;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import scala.Enumeration;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

public class ControllerUtil {

    private static final Logger LOG = Logger.newInstance();

    /**
     * 转换响应结构体
     */
    public static Object convertResponseBody(UncheckedSupplier point) {
        ResponseEntity<Object> responseEntity;
        ResponseBodyBean<Object> result = new ResponseBodyBean<>();
        try {
            final Object body = point.get();
            result.setData(body);
            result.setErrorCode(SystemErrorCodeEnum.SUCCESS().id());
            result.setMessage(SystemErrorCodeEnum.SUCCESS().toString());
            responseEntity = new ResponseEntity<>(result,HttpStatus.OK);
        } catch (Throwable e) {
            if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
                throw new CodeStackException(e);
            }
            LOG.printStackTrace(e);
            if (e instanceof OutOfMemoryError) {
                LOG.error(() -> "OutOfMemoryError:", e);
                System.exit(0);
                return null;
            }
            final CodeStackException se = Optional.ofNullable(findException(e))
                    .orElseGet(() -> ExceptionUtil.getSystemErrorValue(e)
                            .map(ErrorCode::new)
                            .map(CodeStackException::new)
                            .orElseGet(() -> new CodeStackException(new ErrorCode(SystemErrorCodeEnum.UNKNOW()))));
            final String errMsg = Optional.of(se)
                    .map(CodeStackException::getValue)
                    .filter(value -> !value.equals(SystemErrorCodeEnum.UNKNOW()))
                    .map(Enumeration.Value::toString)
                    .orElseGet(e::getMessage);
            Optional.of(se)
                    .map(CodeStackException::getValue)
                    .map(Enumeration.Value::id)
                    .ifPresent(result::setErrorCode);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            result.setStackTrace(sw.toString().split("\n\t"));
            result.setMessage(errMsg);
            responseEntity = new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOG.info(() -> "Response Body : " + JsonUtil.obj2Json(result));
        return responseEntity;
    }

    /**
     * 找到第一个CodeException
     */
    private static CodeStackException findException(Throwable t) {
        if (t == null) return null;
        if (t instanceof CodeStackException) return (CodeStackException) t;
        return findException(t.getCause());
    }
}
