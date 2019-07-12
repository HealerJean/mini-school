package com.hlj.proj.config.shiro;

import com.hlj.proj.service.user.identity.IdentityService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ShiroConfiguration
 * @Author TD
 * @Date 2019/1/24 11:40
 * @Description Shiro配置
 */
@Configuration
public class ShiroConfiguration {

    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    private AuthConfiguration authConfiguration;

    @Autowired
    private IdentityService identityService;

    @Value("${spring.application.name}")
    private String applicationName;



    /**
     * 安全管理器
     * 1、添加Realm
     *   1.1. 添加权限解析器
     * 2、添加WebSession 管理器
     *   2.1、初始化WebSession 管理器
     *   2.2、WebSession 管理器添加Session创建工厂
     *   2.3、WebSession 管理器添加 Redis共享Session
     */
    @Bean
    public DefaultWebSecurityManager securityManager(AuthConfiguration authConfiguration) {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        AuthRealm authRealm = new AuthRealm(identityService);
        authRealm.setAuthenticationTokenClass(Auth2Token.class);
        authRealm.setPermissionResolver(new UrlPermissionResolver());
        securityManager.setRealm(authRealm);


        AuthWebSessionManager sessionManager = new AuthWebSessionManager(authConfiguration);
        sessionManager.setSessionFactory(new AuthSessionFactory());
        sessionManager.setSessionDAO(new RedisSessionDao(applicationName , redisTemplate));

        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }



    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        Map<String, Filter> filters = bean.getFilters();
        //authc 需要认证才能进行访问;
        filters.put("authc",new AuthenticationFilter(authConfiguration));
        //permsUrl 认证了，但是需要有权限才能访问
        filters.put("permsUrl",new UrlPermissionFilter(authConfiguration));
        Map<String, String> filterMap = new LinkedHashMap<>();
        // 配置不会被拦截的链接 顺序判断
        List<String> anonPaths = Arrays.asList(authConfiguration.getAnonPath());
        if(anonPaths != null && !anonPaths.isEmpty()){
            for ( String anonPath: anonPaths) {
                filterMap.put(anonPath, "anon");
            }
        }
        if(!anonPaths.contains("/**")) {
            filterMap.put("/**", "authc,permsUrl");
        }
        // 登录成功后要跳转的链接
        bean.setSuccessUrl("/index");
        //未授权界面;
        bean.setUnauthorizedUrl("/unauth");
        bean.setFilterChainDefinitionMap(filterMap);

        return bean;
    }


}
