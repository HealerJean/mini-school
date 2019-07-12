package com.hlj.proj.config.shiro;
import com.hlj.proj.enums.ResponseEnum;
import com.hlj.proj.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author HealerJean
 * @version 1.0v
 * @Description 用户登录后的权限拦截
 * @ClassName
 * @date
 */
@Slf4j
public class UrlPermissionFilter extends AccessControlFilter {

    private AuthConfiguration authConfiguration;

    public UrlPermissionFilter(AuthConfiguration authConfiguration ) {
        this.authConfiguration = authConfiguration;
    }

    /**
     * 请求是否允许访问
     * 1、如果是登录请求，则返回true
     * 2、没有登录则判断是否可以登录
     *
     */
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        if (this.isLoginRequest(request, response)) {
            return true;
        }

        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String method = httpServletRequest.getMethod().toUpperCase();
        String uri = getPathWithinApplication(request);
        UrlPermission urlPermission = new UrlPermission(uri,method);
        Subject subject = SecurityUtils.getSubject();
        // Subject subject = getSubject(request,response);
        return  subject.isPermitted(JsonUtils.toJsonString(urlPermission));
    }


    /**
     * 当上面拒绝访问的时候调用，我们可以用这个来打印日志
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        httpServletResponse.setStatus(ResponseEnum.未授权.code);
        log.info("用户无权调用：{},{}" ,httpServletRequest.getRequestURI(),httpServletRequest.getMethod());
        return false;
    }


    @Override
    protected boolean isLoginRequest(ServletRequest request, ServletResponse response) {
        String requestURI = this.getPathWithinApplication(request);
        String loginUrl = authConfiguration.getLoginUrl();
        return  this.pathsMatch(loginUrl, requestURI);
    }
}
