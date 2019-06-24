package com.hlj.proj.config.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;


/**
 * @ClassName AuthSessionFactory
 * @Author TD
 * @Date 2019/1/28 15:27
 * @Description Session创建工厂
 */
public class AuthSessionFactory implements SessionFactory {
    @Override
    public Session createSession(SessionContext sessionContext) {
        if (sessionContext != null) {
            String host = sessionContext.getHost();
            AuthSession session = null;
            if (host != null) {
                session = new AuthSession();
                session.setSessionType(AuthConstants.SESSION_TYPE_COOKIE);
            }
            return session;
        }
        return null;
    }
}

