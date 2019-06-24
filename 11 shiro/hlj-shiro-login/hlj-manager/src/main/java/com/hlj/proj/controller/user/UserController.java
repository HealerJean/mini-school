package com.hlj.proj.controller.user;

import com.hlj.proj.api.user.UserService;
import com.hlj.proj.config.shiro.Auth2Token;
import com.hlj.proj.dto.ResponseBean;
import com.hlj.proj.dto.system.MenuDTO;
import com.hlj.proj.dto.user.IdentityInfoDTO;
import com.hlj.proj.dto.user.UcenterFrontMenuDTO;
import com.hlj.proj.dto.user.UserDTO;
import com.hlj.proj.util.IpUtil;
import com.hlj.proj.util.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName UserController
 * @Author DYB
 * @Date 2019/6/3 17:34
 * @Description 用户管理
 * @Version V1.0
 */
@RestController
@Slf4j
@RequestMapping("api")
public class UserController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private UserService userService;


    @PostMapping("user/login")
    public ResponseBean login(@RequestBody(required = false) UserDTO userDTO, HttpServletRequest request) {

        UserDTO result = userService.queryUserInfo(userDTO);
        //shiro登陆
        Subject subject = SecurityUtils.getSubject();
        Auth2Token auth2Token = new Auth2Token(result.getUserId(), result.getUsername(), IpUtil.getIp(request));
        subject.login(auth2Token);
        return ResponseBean.buildSuccess("用户登录成功");
    }

    @GetMapping("logout")
    public ResponseBean logout() {
        log.info("供应链--------用户登出--------参数信息信息：{}");
        //shiro登出
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResponseBean.buildSuccess("用户登出成功");
    }

    /**
     * 当前用户查询
     */
    @GetMapping(value = "user/current")
    public ResponseBean checkCurrentUser() {
        log.info("用户管理--------获取当前用户信息--------查询条件");
        Subject subject = SecurityUtils.getSubject();
        IdentityInfoDTO authUser = UserUtils.getAuthUser();
        return   ResponseBean.buildSuccess("当前登陆用户查询成功",authUser);
    }

    /**
     * 当前用户菜单查询
     */
    @RequestMapping(value = "user/current/menus", method = RequestMethod.GET)
    public ResponseBean currentUserMenus() {
        log.info("用户管理--------当前用户菜单查询--------查询条件");
        List<MenuDTO> menus = UserUtils.getMenus();
        List<UcenterFrontMenuDTO> result = null;
        result = UserUtils.recursionMenus(menus, result);
        return  ResponseBean.buildSuccess("当前用户菜单查询成功",result);
    }

}
