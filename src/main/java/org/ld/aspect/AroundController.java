package org.ld.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.ld.beans.ResponseBodyBean;
import org.ld.enums.SystemErrorCodeEnum;
import org.ld.exception.CodeException;
import org.ld.exception.ErrorCode;
import org.ld.utils.JSONUtil;
import org.ld.utils.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import scala.Enumeration;

import java.util.Optional;

@SuppressWarnings("unused")
@Aspect
@Component
public class AroundController {

    private static final Logger LOG = Logger.newInstance(AroundController.class);

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
            result.setState(SystemErrorCodeEnum.SUCCESS().id());
        } catch (Throwable e) {
            if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
                throw new CodeException(e);
            }
            LOG.printStackTrace(e);
            if (e instanceof OutOfMemoryError) {
                LOG.error(() -> "OutOfMemoryError:", e);
                System.exit(0);
                return null;
            }
            final CodeException se = Optional.ofNullable(findException(e))
                    .orElseGet(() -> ErrorCode.getSystemErrorValue(e)
                            .map(ErrorCode::new)
                            .map(CodeException::new)
                            .orElseGet(() -> new CodeException(new ErrorCode(SystemErrorCodeEnum.UNKNOW()))));
            final String errMsg = Optional.of(se)
                    .map(CodeException::getValue)
                    .filter(value -> !value.equals(SystemErrorCodeEnum.UNKNOW()))
                    .map(Enumeration.Value::toString)
                    .orElseGet(e::getMessage);
            Optional.of(se)
                    .map(CodeException::getValue)
                    .map(Enumeration.Value::id)
                    .ifPresent(result::setState);
            result.setMessage(errMsg);
        }
        LOG.info(() -> "Response Body : " + JSONUtil.obj2String(result));
        return result;
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
