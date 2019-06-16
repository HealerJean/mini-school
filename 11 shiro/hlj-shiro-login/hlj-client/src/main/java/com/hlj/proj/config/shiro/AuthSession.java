package com.hlj.proj.config.shiro;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AuthSession
 * @Author TD
 * @Date 2019/1/28 15:29
 * @Description 认证Session
 */
public class AuthSession implements ValidatingSession,Serializable {


    private String userId;
    private transient String id;
    private transient String sessionType;
    private transient Date startTimestamp;
    private transient Date stopTimestamp;
    private transient Date lastAccessTime;
    private transient long timeout;
    private transient boolean expired;
    private transient String host;
    private transient Map<Object, Object> attributes;

    @Override
    public Serializable getId() {
        return id;
    }

    @Override
    public Date getStartTimestamp() {
        return startTimestamp;
    }

    @Override
    public Date getLastAccessTime() {
        return null;
    }

    @Override
    public long getTimeout() throws InvalidSessionException {
        return timeout;
    }

    @Override
    public void setTimeout(long maxIdleTimeInMillis) throws InvalidSessionException {

    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public void touch() throws InvalidSessionException {

    }

    @Override
    public void stop() throws InvalidSessionException {

    }

    @JsonIgnore
    @Override
    public Collection<Object> getAttributeKeys() throws InvalidSessionException {
        return attributes.keySet();
    }

    @Override
    public Object getAttribute(Object key) throws InvalidSessionException {
        Map<Object, Object> attributes = getAttributes();
        if (attributes == null) {
            return null;
        }
        return attributes.get(key);
    }

    @Override
    public void setAttribute(Object key, Object value) throws InvalidSessionException {
        if (value == null) {
            removeAttribute(key);
        } else {
            getAttributesLazy().put(key, value);
        }
    }

    @Override
    public Object removeAttribute(Object key) throws InvalidSessionException {
        Map<Object, Object> attributes = getAttributes();
        if (attributes == null) {
            return null;
        } else {
            return attributes.remove(key);
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public void validate() throws InvalidSessionException {

    }

    public Map<Object, Object> getAttributes() {
        return attributes;
    }

    @JsonIgnore
    private Map<Object, Object> getAttributesLazy() {
        Map<Object, Object> attributes = getAttributes();
        if (attributes == null) {
            attributes = new HashMap<Object, Object>();
            setAttributes(attributes);
        }
        return attributes;
    }

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }

    public void setAttributes(Map<Object, Object> attributes) {
        this.attributes = attributes;
    }
}
