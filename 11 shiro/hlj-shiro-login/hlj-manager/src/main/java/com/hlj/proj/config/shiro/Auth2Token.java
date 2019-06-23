package com.hlj.proj.config.shiro;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

/**
 * @ClassName Auth2Token
 * @Author TD
 * @Date 2019/1/29 9:36
 * @Description
 */
public class Auth2Token implements HostAuthenticationToken, RememberMeAuthenticationToken {

    private Long userId;

    private String username;

    private String realName;


    private boolean rememberMe = false;

    private String host;

    public Auth2Token(Long userId, String username, String host) {
        this.userId = userId;
        this.username = username;
        this.host = host;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public boolean isRememberMe() {
        return rememberMe;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {

        return userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

}
