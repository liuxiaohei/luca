package org.ld.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.ld.beans.ResponseBodyBean;
import org.ld.enums.ErrorCodeEnum;
import org.ld.exception.CodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import scala.Enumeration;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("unused")
@Aspect
@Component
public class AroundController {

    private static final Logger LOG = LoggerFactory.getLogger(AroundController.class);
    private static final Map<Class, Enumeration.Value> EXCEPTIONS = new HashMap<>();

    static {
        EXCEPTIONS.put(NumberFormatException.class, ErrorCodeEnum.SUCCESS());
    }

    /**
     * 对Controller的方法进一步进行转化处理
     */
    @Around("execution(public * org.ld.controller.*.*(..))"
    )
    public Object mapResponseBodyAdvice(ProceedingJoinPoint point) {
        ResponseBodyBean<Object> result = new ResponseBodyBean<>();
        try {
            final Object body = point.proceed();
            result.setData(body);
            result.setState(ErrorCodeEnum.SUCCESS().id());
            return result;
        } catch (Throwable e) {
            if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
                throw new CodeException(e);
            }
            LOG.info("Logger", e);
            if (e instanceof OutOfMemoryError) {
                LOG.error("OutOfMemoryError:", e);
                System.exit(0);
                return null;
            }
            final CodeException se = findException(e);
            final String errMsg = Optional.ofNullable(se)
                    .map(CodeException::getValue)
                    .map(Enumeration.Value::toString)
                    .orElseGet(() -> EXCEPTIONS.entrySet().stream()
                            .filter(entry -> entry.getKey().isInstance(e))
                            .map(Map.Entry::getValue)
                            .map(Enumeration.Value::toString).findFirst()
                            .orElse(e.getMessage()));
            result.setState(Optional.ofNullable(se).map(CodeException::getValue).map(Enumeration.Value::id).orElseGet(() -> ErrorCodeEnum.UNKNOW().id()));
            result.setMessage(errMsg);
            LOG.info("Response Body : {0}", result.toString());
            return result;
        }
    }

    /**
     * 找到第一个CodeException
     */
    private static CodeException findException(Throwable t) {
        if (t == null) {
            return null;
        }
        if (t instanceof CodeException) {
            return (CodeException) t;
        }
        return findException(t.getCause());
    }
}
