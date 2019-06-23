package com.hlj.proj.config.shiro;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @ClassName AuthConfiguration
 * @Author TD
 * @Date 2019/6/3 20:11
 * @Description 认证属性
 */
@Configuration
@PropertySource(value = "classpath:shiro.properties", ignoreResourceNotFound = true, encoding = "UTF-8")
@Data
public class AuthConfiguration {

    @Value("${fintech.ucenter.auth.loginUrl:}")
    private String loginUrl;
    @Value("${fintech.ucenter.auth.clientId:}")
    private String clientId;
    @Value("${fintech.ucenter.auth.sessionExpire:1800}")
    private Integer sessionExpire;
    @Value("${fintech.ucenter.auth.sessionUserExpire:1800}")
    private Integer sessionUserExpire;
    @Value("${fintech.ucenter.auth.redisTempleName:}")
    private String redisTempleName;
    @Value("${fintech.ucenter.auth.anonPath: ,,}")
    private String[] anonPath;
}
