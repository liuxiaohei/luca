package org.ld.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.ld.utils.ControllerUtil;
import org.ld.utils.Logger;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Aspect
@Component
public class AroundController {

    private static final Logger LOG = Logger.newInstance();

    /**
     * 对Controller的方法进一步进行转化处理
     */
    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object mapResponseBodyAdvice(ProceedingJoinPoint point) {
        return ControllerUtil.convertResponseBody(point::proceed);
    }
}
