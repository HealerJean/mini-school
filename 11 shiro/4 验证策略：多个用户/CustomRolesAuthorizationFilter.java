package cn.edu.dlut.career.shiro;

/**
 * Created by HealerJean on 2017/4/20.
 */
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

public class CustomRolesAuthorizationFilter extends AuthorizationFilter {

        Logger logger = LoggerFactory.getLogger(CustomRolesAuthorizationFilter.class);
    @Override
    protected boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object mappedValue) throws Exception {
        Subject subject = getSubject(req, resp);
        String[] rolesArray = (String[]) mappedValue;
        logger.info(rolesArray[0]+rolesArray[1]+"******* config"+rolesArray.length+""+subject.getPrincipal());
        if (rolesArray == null || rolesArray.length == 0) { //没有角色限制，有权限访问
            return true;
        }
        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        if (userPrincipal!=null) {
            for (int i = 0; i < rolesArray.length; i++) {
                if (userPrincipal.getRole().equals(rolesArray[i])) { //若当前用户是rolesArray中的任何一个，则有权限访问
                    return true;
                }
            }
        }
        return false;
    }
}
