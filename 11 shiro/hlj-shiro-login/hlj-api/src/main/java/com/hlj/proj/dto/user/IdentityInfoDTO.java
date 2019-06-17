package com.hlj.proj.dto.user;

import com.hlj.proj.dto.system.DepartmentDTO;
import com.hlj.proj.dto.system.MenuDTO;
import com.hlj.proj.dto.system.RoleDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName IdentityInfoDTO
 * @Author TD
 * @Date 2019/1/9 18:09
 * @Description 身份信息
 */
@Data
@Accessors(chain = true)
public class IdentityInfoDTO implements Serializable {


    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 名称
     */
    private String realName;
    /**
     * 类型
     */
    private String userType;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机
     */
    private String telephone;


    /**
     * 用户对应部门信息
     */
    private DepartmentDTO departmentDTO;

    /**
     * 角色集合
     */
    private List<RoleDTO> roles;
    /**
     * 菜单集合
     */
    private List<MenuDTO> menus;
    /**
     * 权限集合
     */
    private List<MenuDTO> permissions;


}
