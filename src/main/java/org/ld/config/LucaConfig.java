package org.ld.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.MultipartConfigElement;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

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
        final BiFunction<Integer, String, ResponseMessage> re = (code, message) -> new ResponseMessageBuilder().code(code).message(message).responseModel(new ModelRef("ApiError")).build();
        final List<ResponseMessage> responseMessages = Arrays.asList(
                re.apply(200, "操作成功"),
                re.apply(400, "登录参数错误"),
                re.apply(401, "用户名或密码错误"),
                re.apply(403, "用户被禁止"),
                re.apply(404, "找不到资源"),
                re.apply(409, "业务逻辑异常"),
                re.apply(422, "参数校验异常"),
                re.apply(500, "服务器内部错误"),
                re.apply(503, "Hystrix异常"));
        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.GET, responseMessages)
                .globalResponseMessage(RequestMethod.POST, responseMessages)
                .globalResponseMessage(RequestMethod.PUT, responseMessages)
                .globalResponseMessage(RequestMethod.DELETE, responseMessages)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(url -> !"/error".equals(url)) // 屏蔽 这个api
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("LUCA")
                        .description("系统介绍")
                        .contact(new Contact("ld", "", "2297598383@qq.com"))
                        .version("1.0")
                        .build());
    }
}