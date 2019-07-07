package com.hlj.proj.config.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @ClassName AuthWebSessionManager
 * @Author TD
 * @Date 2019/1/28 15:31
 * @Description 管理session管理类
 */
@Slf4j
public class AuthWebSessionManager extends DefaultWebSessionManager {

    public static final String AUTHENTICATED = DefaultSubjectContext.class.getName() + ".AUTHENTICATED";
    public static final String TOKEN_AUTHENTICATED = "TOKEN_AUTHENTICATED";
    public static final String AUTHENTICATED_SESSION_KEY = DefaultSubjectContext.class.getName() + "_AUTHENTICATED_SESSION_KEY";

    public String DEFAULT_SESSION_ID_NAME = null;

    private boolean sessionIdCookieEnabled;
    private AuthConfiguration authConfiguration;

    public AuthWebSessionManager(AuthConfiguration authConfiguration) {
        super();
        this.authConfiguration = authConfiguration;
        DEFAULT_SESSION_ID_NAME = authConfiguration.getClientId()+ "_SID";
        Cookie cookie = new SimpleCookie(DEFAULT_SESSION_ID_NAME);
        cookie.setHttpOnly(true);

        //设置sessionIdCookie
        super.setSessionIdCookie(cookie);
        this.setSessionIdCookie(cookie);
        //是否将sessionId存储到cookie中
        super.setSessionIdCookieEnabled(true);
        this.setSessionIdCookieEnabled(true);

        super.setSessionIdUrlRewritingEnabled(true);
        super.setSessionValidationSchedulerEnabled(false);
    }

    @Override
    public boolean isSessionIdCookieEnabled() {
        return sessionIdCookieEnabled;
    }

    @Override
    public void setSessionIdCookieEnabled(boolean sessionIdCookieEnabled) {
        this.sessionIdCookieEnabled = sessionIdCookieEnabled;
    }

    @Override
    public Serializable getSessionId(SessionKey key) {
        Serializable id = super.getSessionId(key);
        if (id == null && WebUtils.isWeb(key)) {
            ServletRequest request = WebUtils.getRequest(key);
            ServletResponse response = WebUtils.getResponse(key);
            id = getSessionId(request, response);
        }
        return id;
    }

    @Override
    public void setGlobalSessionTimeout(long globalSessionTimeout) {
        super.setGlobalSessionTimeout(globalSessionTimeout);
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String id = getSessionIdCookieValue(request, response);
        if (id != null) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
                    ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
        }
        if (id != null) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
        }

        request.setAttribute(ShiroHttpServletRequest.SESSION_ID_URL_REWRITING_ENABLED, isSessionIdUrlRewritingEnabled());

        return id;
    }

    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);
        if (sessionId == null) {
            log.debug("Unable to resolve session ID from SessionKey [{}].  Returning null to indicate a " +
                    "session could not be found.", sessionKey);
            return null;
        }
        String readKey = sessionId.toString();
            readKey = AuthConstants.SESSION_TYPE_COOKIE + ":" + readKey;
        Session session = retrieveSessionFromDataSource(readKey);
        if (session == null) {
            throw new UnknownSessionException("There is no session with id [" + sessionId + "]");
        }
        return session;
    }


    private String getSessionIdCookieValue(ServletRequest request, ServletResponse response) {
        if (!isSessionIdCookieEnabled()) {
            return null;
        }
        if (!(request instanceof HttpServletRequest)) {
            return null;
        }
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        return getSessionIdCookie().readValue(httpRequest, WebUtils.toHttp(response));
    }




}
