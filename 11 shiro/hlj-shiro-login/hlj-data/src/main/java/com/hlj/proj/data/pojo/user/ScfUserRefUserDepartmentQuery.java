/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.pojo.user;

import com.hlj.proj.data.common.query.PagingQuery;
import lombok.Data;

/**
 * @author duyang
 * @ClassName: ScfUserRefUserDepartmentQuery
 * @date 2099/1/1
 * @Description: ScfUserRefUserDepartmentQuery
 */
@Data
public class ScfUserRefUserDepartmentQuery extends PagingQuery {
	private static final long serialVersionUID = 1L;

	public ScfUserRefUserDepartmentQuery(){
		super(1, 10);
	}

	public ScfUserRefUserDepartmentQuery(int pageNo, int pageSize){
		super(pageNo, pageSize);
	}

	/** 用户ID */
	private Long refUserId;
	/** 企业ID */
	private Long refDepartmentId;
	/** 状态: 10：有效，99：无效 */
	private String status;

	/** 计算总记录数 */
	public int calcItemCount(Object t) {
		return 0;
	}



}
