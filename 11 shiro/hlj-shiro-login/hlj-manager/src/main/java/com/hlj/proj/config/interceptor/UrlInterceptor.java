package com.hlj.proj.config.interceptor;

import com.hlj.proj.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author HealerJean
 * @version 1.0v
 * @Description shiro拦截下之后就不会进入了，因为是先进入的shiro
 * @ClassName
 * @date
 */
@Component
@Slf4j
public class UrlInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
            log.info("请求地址:[{}];访问ip:[{}]", httpServletRequest.getRequestURL(), IpUtil.getIp(httpServletRequest));
        return  true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
