package org.ld.config;

import org.ld.exception.StackException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("unused")
@Configuration
public class SpringApplicationContext implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    /**
     * 获取Bean 实例
     */
    public static <T> T getBean(Class<T> clazz) {
        try {
            return applicationContext.getBean(clazz);
        } catch (BeansException e) {
            throw new StackException(e);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }
}
