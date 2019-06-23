/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.pojo.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author duyang
 * @ClassName: ScfUserRefUserDepartment
 * @date 2099/1/1
 * @Description: ScfUserRefUserDepartment
 */
@Data
public class ScfUserRefUserDepartment implements Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 */
	private Long id;
	/** 用户ID */
	private Long refUserId;
	/** 企业ID */
	private Long refDepartmentId;
	/** 状态: 10：有效，99：无效 */
	private String status;

}
