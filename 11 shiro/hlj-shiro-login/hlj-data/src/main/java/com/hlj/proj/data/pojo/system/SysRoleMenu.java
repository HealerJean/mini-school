/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.pojo.system;

import java.io.Serializable;
import lombok.Data;
/**
 * @author zhangyujin
 * @ClassName: SysRoleMenu
 * @date 2099/1/1
 * @Description: SysRoleMenu
 */
@Data
public class SysRoleMenu implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 角色ID */
	private Long refRoleId;
	/** 菜单ID */
	private Long refMenuId;
	/** 状态 */
	private String status;
	/** 创建人 */
	private Long createUser;
	/** 创建人名称 */
	private String createName;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 更新人 */
	private Long updateUser;
	/** 更新人名称 */
	private String updateName;
	/** 更新时间 */
	private java.util.Date updateTime;

}