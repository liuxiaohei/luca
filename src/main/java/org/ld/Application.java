package org.ld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ComponentScan :
 * SpringBoot在写启动类的时候如果不使用@ComponentScan指明对象扫描范围，默认指扫描当前启动类所在的包里的对象，
 * 如果当前启动类没有包，则在启动时会报错：Your ApplicationContext is unlikely to start due to a @ComponentScan of the default package错误。
 * 因为启动类不能直接放在main/java文件夹下，必须要建一个包把它放进去或者使用@ComponentScan指明要扫描的包。代码示例如下：
 * @author alen.c
 *
 */

@SpringBootApplication
@EnableAutoConfiguration //自动载入应用程序所需的所有Bean，当使用Exclude这个属性时，是禁止自动配置某个类
@RestController //返回对象则自动解析为json字符串 因为spring boot 默认使用json解析框架自动返回，返回头是Content-Type:application/json;charset=UTF-8
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping(value="/")
	public String index(){
		return "hello demo!";
	}
}
