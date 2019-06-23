package com.hlj.proj.config.shiro;

import com.hlj.proj.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;

/**
 * @ClassName UrlPermissionResolver
 * @Author TD
 * @Date 2019/1/31 17:18
 * @Description URL权限解析器
 */
@Slf4j
public class UrlPermissionResolver implements PermissionResolver {

    @Override
    public Permission resolvePermission(String s) {
        UrlPermission urlPermission = JsonUtils.jsonToObject(s, UrlPermission.class);
        return urlPermission;
    }
}
