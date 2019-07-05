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
     * 解释： minixiao 的时候，是给他授予角色以及权限的，只会进入一次
     * 验证权限时调用,页面验证多次权限的时候，就会进来多次
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
     * 1、获取登录后需要的的基本信息（将来无需查询数据库直接获取）
     * 2、shiro中放入用户Url权限
     * 3、用户信息存储到session中  （不存储菜单和权限，因为session中的用户是提供给我们后台自己使用的对象）
     *     菜单放到了 session的另一个 name中，如下
     *     权限交给了 shiro ，shiro控制是否有权限操作
     * 4、前台展示的路由菜单menus，用户提供前台显示的菜单放到了session中
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("=====认证时用户调用======");
        if (authenticationToken == null || !(authenticationToken instanceof Auth2Token)) {
            //认证失败
            throw new AuthenticationException();
        }
        Auth2Token token = (Auth2Token) authenticationToken;
        //1、获取登录后需要的的基本信息（将来无需查询数据库直接获取）
        IdentityInfoDTO identityInfo = identityService.getUserInfo(token.getUserId());
        log.info("获取到IdentityInfo信息：{}", identityInfo);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo();
        AuthPrincipalCollection principalCollection = new AuthPrincipalCollection();

        //2、shiro中放入用户权限
        List<MenuDTO> permissions = identityInfo.getPermissions();
        Set<UrlPermission> collect = new HashSet<>();
        if (permissions != null) {
            collect.addAll(permissions.stream()
                    .map(item -> new UrlPermission(item.getUrl(), item.getMethod())).collect(toSet()));
        }
        principalCollection.add(collect, identityInfo.getRealName());
        authenticationInfo.setPrincipals(principalCollection);
        authenticationInfo.setCredentials(token.getUserId());

        //3、用户信息存储到session中  （不存储菜单和权限，因为session中的用户是提供给我们后台自己使用的对象）
        //菜单放到了 session的另一个 name中，如下
        //权限交给了 shiro ，shiro控制是否有权限操作
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        identityInfo.setMenus(null);
        identityInfo.setPermissions(null);
        session.setAttribute(AuthConstants.AUTH_USER, identityInfo);

        // 4、前台展示的路由菜单menus，用户提供前台显示的菜单放到了session中
        List<MenuDTO> menus = identityInfo.getMenus();
        session.setAttribute(AuthConstants.AUTH_MENU, menus);

        return authenticationInfo;
    }


}
