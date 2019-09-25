package org.ld.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.MultipartConfigElement;

/**
 * 获取配置中心的自定义配置信息
 *
 * @author ld
 */
@SuppressWarnings("unused")
@Configuration
@EnableSwagger2
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

    /**
     * http://127.0.0.1:9999/swagger-ui.html#/
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("LUCA API")
                        .version("1.0")
                        .build());
    }
}