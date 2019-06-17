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

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        Map<String, Filter> filters = bean.getFilters();
        filters.put("authc",new AuthenticationFilter(authConfiguration));
        filters.put("permsUrl",new UrlPermissionFilter(authConfiguration));
        Map<String, String> filterMap = new LinkedHashMap<>();
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
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
}
