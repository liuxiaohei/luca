package org.ld.utils;

import org.ld.beans.ResponseBodyBean;
import org.ld.enums.SystemErrorCodeEnum;
import org.ld.exception.StackException;
import org.ld.exception.ErrorCode;
import org.ld.functions.UncheckedSupplier;
import org.springframework.core.annotation.AnnotationUtils;
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
        ResponseBodyBean<Object> result = new ResponseBodyBean<>();
        try {
            final Object body = point.get();
            result.setData(body);
            result.setState(SystemErrorCodeEnum.SUCCESS().id());
            result.setMessage(SystemErrorCodeEnum.SUCCESS().toString());
        } catch (Throwable e) {
            if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
                throw new StackException(e);
            }
            LOG.printStackTrace(e);
            if (e instanceof OutOfMemoryError) {
                LOG.error(() -> "OutOfMemoryError:", e);
                System.exit(0);
                return null;
            }
            final StackException se = Optional.ofNullable(findException(e))
                    .orElseGet(() -> ErrorCode.getSystemErrorValue(e)
                            .map(ErrorCode::new)
                            .map(StackException::new)
                            .orElseGet(() -> new StackException(new ErrorCode(SystemErrorCodeEnum.UNKNOW()))));
            final String errMsg = Optional.of(se)
                    .map(StackException::getValue)
                    .filter(value -> !value.equals(SystemErrorCodeEnum.UNKNOW()))
                    .map(Enumeration.Value::toString)
                    .orElseGet(e::getMessage);
            Optional.of(se)
                    .map(StackException::getValue)
                    .map(Enumeration.Value::id)
                    .ifPresent(result::setState);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            result.setStackTrace(sw.toString().split("\n\t"));
            result.setMessage(errMsg);
        }
        LOG.info(() -> "Response Body : " + JsonUtil.obj2Json(result));
        return result;
    }

    /**
     * 找到第一个CodeException
     */
    private static StackException findException(Throwable t) {
        if (t == null) return null;
        if (t instanceof StackException) return (StackException) t;
        return findException(t.getCause());
    }
}
