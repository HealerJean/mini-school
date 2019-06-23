/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.pojo.user;

import com.hlj.proj.data.common.query.PagingQuery;
import lombok.Data;

import java.util.List;

/**
 * @author duyang
 * @ClassName: ScfUserInfoQuery
 * @date 2099/1/1
 * @Description: ScfUserInfoQuery
 */
@Data
public class ScfUserInfoQuery extends PagingQuery {
	private static final long serialVersionUID = 1L;

	public ScfUserInfoQuery(){
		super(1, 10);
	}

	public ScfUserInfoQuery(int pageNo, int pageSize){
		super(pageNo, pageSize);
	}

	/** 用户ID列表 */
	private List<Long> ids;
	/** 用户名 */
	private String username;
	/** 真实姓名 */
	private String realName;
	/** 邮箱 */
	private String email;
	/** 手机号 */
	private String telephone;
	/** 性别 */
	private String gender;
	/** 密码 */
	private String password;
	/** 用户类型（字典） */
	private String userType;
	/** 用户状态 */
	private String status;
	/** 创建人ID */
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

	/**
	 * 部门id List
	 */
	private List<Long> departmentIds;

}
