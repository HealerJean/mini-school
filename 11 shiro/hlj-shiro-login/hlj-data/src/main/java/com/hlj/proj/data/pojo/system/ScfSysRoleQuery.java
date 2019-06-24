/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.pojo.system;

import com.hlj.proj.data.common.query.PagingQuery;
import lombok.Data;

/**
 * @author duyang
 * @ClassName: ScfSysRoleQuery
 * @date 2099/1/1
 * @Description: ScfSysRoleQuery
 */
@Data
public class ScfSysRoleQuery extends PagingQuery {
	private static final long serialVersionUID = 1L;

	public ScfSysRoleQuery(){
		super(1, 10);
	}

	public ScfSysRoleQuery(int pageNo, int pageSize){
		super(pageNo, pageSize);
	}

	/** 角色名称 */
	private String roleName;
	/** 系统CODE */
	private String refSystemCode;
	/** 状态 */
	private String status;
	/** 描述 */
	private String desc;
	/** 创建人 */
	private Long createUser;
	/** 创建人名称 */
	private String createName;
	/** 更新人 */
	private Long updateUser;
	/** 更新人名称 */
	private String updateName;

	/** 计算总记录数 */
	public int calcItemCount(Object t) {
		return 0;
	}



}
