package com.arlin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @ClassName: SwaggerConfig
 * @Description: Swagger配置类
 * @Author: arlin
 * @Date: 2021/6/23
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 配置swagger的Docket的bean实例
     * @return
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
//                .enable(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.arlin.controller"))
                .build();
    }

    /**
     * 配置swagger中的apiInfo
     * @return
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("Arlin", "", "arlincd@foxmail.com");
        return new ApiInfo(
                "英语词汇量测试后端接口",
                "英语词汇量测试后端接口测试",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
