package com.hlj.proj.config.shiro;

import com.hlj.proj.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @ClassName OauthAuthenticationFilter
 * @Author TD
 * @Date 2019/1/24 13:47
 * @Description 单点登陆认证
 */
@Slf4j
public class AuthenticationFilter extends FormAuthenticationFilter {

    private AuthConfiguration authConfiguration;


    public AuthenticationFilter(AuthConfiguration authConfiguration) {
        this.authConfiguration = authConfiguration;
    }

    /**
     *  该方法为未登陆状态进入
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        log.info("=================登陆未授权===================");
//        HttpServletRequest httpRequest = WebUtils.toHttp(request);
//        Object attribute = httpRequest.getAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE);
        if (this.isLoginRequest(request, response)) {
                return true;
        } else {
            this.saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }


    /**
     * 未授权之后重定向到登陆页面，此处重定向到授权中心
     * @return
     */
//    @Override
//    public String getLoginUrl() {
//        if(authConfiguration == null){
//            return "";
//        }
//        HttpUrl httpUrl = HttpUrl.parse(authConfiguration.getAuthBaseUrl());
//        HttpUrl.Builder builder = httpUrl.newBuilder();
//        builder.addEncodedPathSegments("oauth2/authorize");
//        httpUrl = builder.build();
//
//        return httpUrl.toString();
//    }

    @Override
    protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        this.saveRequest(request);
        this.redirectToLogin(request, response);
    }

    /**
     *  重定向到登陆
     */
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        String loginUrl = this.getLoginUrl();
        if(StringUtils.isBlank(loginUrl)){
            return ;
        }
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(ResponseEnum.未登陆.code);
    }


    /**
     * 创建Session，记录重定向的地址
     * @param request
     */
    @Override
    protected void saveRequest(ServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        AuthSavedRequest savedRequest = new AuthSavedRequest(httpRequest);
        session.setAttribute(AuthConstants.AUTH_REQUEST, savedRequest);
    }

    /**
     * 判断是否是登陆的请求：此处修改未获取了授权码并重定向回来的路径
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginRequest(ServletRequest request, ServletResponse response) {
        String requestURI = this.getPathWithinApplication(request);
        String loginUrl = authConfiguration.getLoginUrl();
        return this.pathsMatch(loginUrl, requestURI);
    }

    public void storeSessionId(Serializable currentId, HttpServletRequest request, HttpServletResponse response) {
        if (currentId == null) {
            String msg = "sessionId cannot be null when persisting for subsequent requests.";
            throw new IllegalArgumentException(msg);
        }
    }

}
