package com.hlj.proj.dto.user;

import com.hlj.proj.dto.system.DepartmentDTO;
import com.hlj.proj.dto.system.RoleDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName UserDTO
 * @Author TD
 * @Date 2019/4/22 11:50
 * @Description 用户
 */
@Data
@Accessors(chain = true)
public class UserDTO {

    /**
     * 用户主键
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String telephone;

    /**
     * 性别
     */
    private String gender;

    /**
     * 用户类型（字典）
     */
    private String userType;

    /**
     * 用户状态
     */
    private String userStatus;


    /**
     * 拥有角色
     */
    private List<RoleDTO> roles;

    /**
     * 所属部门
     */
    private DepartmentDTO department;


}
