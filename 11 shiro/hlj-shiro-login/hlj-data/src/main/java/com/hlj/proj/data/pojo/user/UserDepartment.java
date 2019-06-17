/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.pojo.user;

import java.io.Serializable;
import lombok.Data;
/**
 * @author zhangyujin
 * @ClassName: UserDepartment
 * @date 2099/1/1
 * @Description: UserDepartment
 */
@Data
public class UserDepartment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 用户主键 */
	private Long refUserId;
	/** 部门主键 */
	private Long refDepartmentId;
	/** 是否有效10有效，99废弃 */
	private java.lang.String status;
}