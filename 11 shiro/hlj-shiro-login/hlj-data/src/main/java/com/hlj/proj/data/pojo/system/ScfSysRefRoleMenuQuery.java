/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.pojo.system;

import com.hlj.proj.data.common.query.PagingQuery;
import lombok.Data;

import java.util.List;

/**
 * @author duyang
 * @ClassName: ScfSysRefRoleMenuQuery
 * @date 2099/1/1
 * @Description: ScfSysRefRoleMenuQuery
 */
@Data
public class ScfSysRefRoleMenuQuery extends PagingQuery {
	private static final long serialVersionUID = 1L;

	public ScfSysRefRoleMenuQuery(){
		super(1, 10);
	}

	public ScfSysRefRoleMenuQuery(int pageNo, int pageSize){
		super(pageNo, pageSize);
	}

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
	/** 更新人 */
	private Long updateUser;
	/** 更新人名称 */
	private String updateName;
	/** 角色ID */
	private List<Long> refRoleIds;
	/** 计算总记录数 */
	public int calcItemCount(Object t) {
		return 0;
	}


}
