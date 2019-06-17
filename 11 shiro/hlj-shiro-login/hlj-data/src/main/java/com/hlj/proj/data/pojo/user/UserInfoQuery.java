/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.pojo.user;

import com.hlj.proj.data.common.query.PagingQuery;
import lombok.Data;
/**
 * @author zhangyujin
 * @ClassName: UserInfoQuery
 * @date 2099/1/1
 * @Description: UserInfoQuery
 */
@Data
public class UserInfoQuery extends PagingQuery {
	private static final long serialVersionUID = 1L;

	public UserInfoQuery(){
		super(1, 10);
	}

	public UserInfoQuery(int pageNo, int pageSize){
		super(pageNo, pageSize);
	}

	/** 用户名 */
	private String name;
	/** 真实姓名 */
	private String username;
	/** 密码 */
	private String password;
	/** 邮箱 */
	private String email;
	/** 手机号 */
	private String telephone;
	/** 性别 */
	private String gender;
	/** 用户类型 */
	private String userType;
	/** 用户状态 */
	private String status;
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