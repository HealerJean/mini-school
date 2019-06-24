package com.hlj.proj.config.shiro;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.shiro.subject.SimplePrincipalCollection;

import java.util.Collection;
import java.util.Set;

/**
 * @ClassName AuthPrincipalCollection
 * @Author TD
 * @Date 2019/1/30 13:52
 * @Description
 */
public class AuthPrincipalCollection extends SimplePrincipalCollection {

    @JsonIgnore
    @Override
    protected Collection getPrincipalsLazy(String realmName) {
        return super.getPrincipalsLazy(realmName);
    }
    @JsonIgnore
    @Override
    public Object getPrimaryPrincipal() {
        return super.getPrimaryPrincipal();
    }
    @JsonIgnore
    @Override
    public Set<String> getRealmNames() {
        return super.getRealmNames();
    }
}
