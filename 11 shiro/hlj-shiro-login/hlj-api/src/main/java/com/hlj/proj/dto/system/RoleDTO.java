package com.hlj.proj.dto.system;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName RoleDTO
 * @Author TD
 * @Date 2019/1/9 18:10
 * @Description 角色传输对象
 */
@Data
public class RoleDTO implements Serializable {

    /** 角色ID */
    private Long id;
    /** 角色名称 */
    private String roleName;
    /** 系统编号 */
    private String systemCode;
    /** 状态 */
    private String status;
    /** 描述 */
    private String desc;
    /**
     * 分页大小
     */
    private Integer pageSize;
    /**
     * 页码
     */
    private Integer pageNo;
    /**
     * 不采取分页
     */
    private Boolean isPage;
    /**
     * 分组后的菜单
     */
    List<MenuDTO> frontMenus;
    /**
     * 分组后的菜单
     */
    List<MenuDTO> backMenus;
}
