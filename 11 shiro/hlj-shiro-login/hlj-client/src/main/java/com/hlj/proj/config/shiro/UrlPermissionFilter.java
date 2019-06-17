package com.hlj.proj.config.shiro;
import com.hlj.proj.enums.ResponseEnum;
import com.hlj.proj.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName URLPermissionFilter
 * @Author TD
 * @Date 2019/1/31 17:48
 * @Description
 */
@Slf4j
public class UrlPermissionFilter extends AccessControlFilter {

    private AuthConfiguration authConfiguration;

    public UrlPermissionFilter(AuthConfiguration authConfiguration ) {
        this.authConfiguration = authConfiguration;
    }

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        if (this.isLoginRequest(request, response)) {
            return true;
        }
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        Object attribute = httpServletRequest.getAttribute(AuthConstants.AUTHORIZATION_PERMISSION);
        if(Boolean.TRUE.equals(attribute)){
            return true;
        }
        Subject subject = getSubject(request,response);
        String method = httpServletRequest.getMethod().toUpperCase();
        String uri = getPathWithinApplication(request);
        UrlPermission urlPermission = new UrlPermission(uri,method);
        return  subject.isPermitted(JsonUtils.toJsonString(urlPermission));
    }

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
