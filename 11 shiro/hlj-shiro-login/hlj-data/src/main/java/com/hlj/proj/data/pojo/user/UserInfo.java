/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.pojo.user;

import java.io.Serializable;
import lombok.Data;
/**
 * @author zhangyujin
 * @ClassName: UserInfo
 * @date 2099/1/1
 * @Description: UserInfo
 */
@Data
public class UserInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
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
	/** 创建时间 */
	private java.util.Date createTime;
	/** 更新人 */
	private Long updateUser;
	/** 更新人名称 */
	private String updateName;
	/** 更新时间 */
	private java.util.Date updateTime;

}