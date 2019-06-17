package com.hlj.proj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * swappger文档生成
 */
@EnableSwagger2
@Configuration
@PropertySource("classpath:swagger.properties") // 新增对swagger.properties 的引入
public class ApiConfig extends WebMvcConfigurerAdapter {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("hlj-proj")
                .description("模板项目")
                .version("1.0.0")
                .termsOfServiceUrl("http://localhost:8888/")
                .license("demo")
                .licenseUrl("http://localhost:8888/")
                .build();
    }


}
