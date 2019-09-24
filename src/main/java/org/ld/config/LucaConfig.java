package org.ld.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.MultipartConfigElement;

/**
 * 获取配置中心的自定义配置信息
 * @author ld
 */
@SuppressWarnings("unused")
@Configuration
public class LucaConfig {

    /**
     * 增大默认上传文件大小的限制
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("20480KB");
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }
}