package com.hlj.proj.dto.system;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName MenuDTO
 * @Author TD
 * @Date 2019/1/9 18:11
 * @Description 菜单传输对象
 */
@Data
public class MenuDTO implements Serializable {
    /** 主键 */
    private Long id;
    /** 系统编号 */
    private String systemCode;
    /** 菜单名称 */
    private String menuName;
    /** 相对路径 */
    private String url;
    /** 调用方法 */
    private String method;
    /** 父菜单id */
    private Long pid;
    /** 父菜单链 */
    private String pchain;
    /** 菜单描述 */
    private String desc;
    /** 菜单序列 */
    private Integer sort;
    /** 菜单图标 */
    private String icon;
    /** 前端菜单标识（前端菜单唯一标识） */
    private String frontKey;
    /** 是否需要权限拦截，10：需要，99：不需要 */
    private String isPermission;
    /** 菜单类型：0: 后端路径, 1:前端菜单，2:非展示前端菜单 */
    private String menuType;
    /** 状态 */
    private String status;
    /**
     * 是否返回树形结构
     */
    private Boolean toTree;
    /** 子菜单 */
    private List<MenuDTO> submenus;
    /**
     * 分组后的菜单
     */
    List<MenuDTO> frontMenus;
    /**
     * 分组后的菜单
     */
    List<MenuDTO> backMenus;
    /**
     * 分页大小
     */
    private Integer pageSize;
    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 分组后的菜单
     */
    List<Long> front;
    /**
     * 分组后的菜单
     */
    List<Long> back;
}
