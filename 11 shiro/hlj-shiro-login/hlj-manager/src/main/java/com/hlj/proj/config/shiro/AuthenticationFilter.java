package com.hlj.proj.config.shiro;

import com.hlj.proj.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
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
     * 是否拒绝访问：没有登录状态下进入
     *  判断是否是登录Url请求
     *  1、如果是登录请求返回true
     *  2、如果不是登录请求，保存登录请求，并重定向到登录，返回false
     *     2.1、创建Session，记录重定向的请求
     *     2.2、重定向到登陆
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        log.info("shiro=================用户未登录===================");
        if (this.isLoginRequest(request, response)) {
                return true;
        } else {
            this.saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }


    @Override
    protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        this.saveRequest(request);
        this.redirectToLogin(request, response);
    }


    /**
     * 创建Session，记录重定向的请求
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
     *  重定向到登陆
     *  这里获取到的 loginUrl = login.jsp 不用理会
     */
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        String loginUrl = this.getLoginUrl();
        if(StringUtils.isBlank(loginUrl)){
            return ;
        }
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(ResponseEnum.未登录.code);
    }






    /**
     * 判断是否是登陆的Url请求：
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


}
