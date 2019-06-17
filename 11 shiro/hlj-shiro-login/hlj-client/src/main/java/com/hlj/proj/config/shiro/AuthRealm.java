package com.hlj.proj.config.shiro;
import com.hlj.proj.dto.system.MenuDTO;
import com.hlj.proj.dto.user.IdentityInfoDTO;
import com.hlj.proj.service.user.identity.IdentityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * @ClassName AuthRealm
 * @Author TD
 * @Date 2019/1/24 11:53
 * @Description
 */
@Slf4j
public class AuthRealm extends AuthorizingRealm {

    private IdentityService identityService;

    public AuthRealm(IdentityService identityService) {
        this.identityService = identityService;
    }

    @Override
    public void setPermissionResolver(PermissionResolver permissionResolver) {
        super.setPermissionResolver(permissionResolver);
    }

    /**
     * 验证权限时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.debug("=====验证权限时调用======");
        SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();
        Object primaryPrincipal = principals.getPrimaryPrincipal();
        if (primaryPrincipal != null && primaryPrincipal instanceof Set) {
            Set<Permission> set = (Set<Permission>) primaryPrincipal;
            authenticationInfo.setObjectPermissions(set);
        }
        return authenticationInfo;
    }


    /**
     * 认证时用户调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("=====认证时用户调用======");
        if (authenticationToken == null || !(authenticationToken instanceof Auth2Token)) {
            //认证失败
            throw new AuthenticationException();
        }
        Auth2Token token = (Auth2Token) authenticationToken;
        IdentityInfoDTO identityInfo = identityService.getUserInfo(token.getUserId());
        log.info("获取到IdentityInfo信息：{}",identityInfo);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo();
        AuthPrincipalCollection principalCollection = new AuthPrincipalCollection();
        List<MenuDTO> permissions = identityInfo.getPermissions();
        List<MenuDTO> menus = identityInfo.getMenus();
        Set<UrlPermission> collect = new HashSet<>();
        if (permissions != null) {
            collect.addAll(permissions.stream()
                    .map(item -> new UrlPermission(item.getUrl(), item.getMethod())).collect(toSet()));
        }
        principalCollection.add(collect, identityInfo.getRealName());
        authenticationInfo.setPrincipals(principalCollection);
        authenticationInfo.setCredentials(token.getUserId());
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        //放入用户信息到Session
        identityInfo.setMenus(null);
        identityInfo.setPermissions(null);
        session.setAttribute(AuthConstants.AUTH_USER, identityInfo);
        //放入菜单信息
        session.setAttribute(AuthConstants.AUTH_MENU, menus);

        return authenticationInfo;
    }


}
