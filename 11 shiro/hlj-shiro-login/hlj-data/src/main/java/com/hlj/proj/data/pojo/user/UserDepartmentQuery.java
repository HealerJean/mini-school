/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.pojo.user;

import com.hlj.proj.data.common.query.PagingQuery;
import lombok.Data;
/**
 * @author zhangyujin
 * @ClassName: UserDepartmentQuery
 * @date 2099/1/1
 * @Description: UserDepartmentQuery
 */
@Data
public class UserDepartmentQuery extends PagingQuery {
	private static final long serialVersionUID = 1L;

	public UserDepartmentQuery(){
		super(1, 10);
	}

	public UserDepartmentQuery(int pageNo, int pageSize){
		super(pageNo, pageSize);
	}

	/** 用户主键 */
	private Long refUserId;
	/** 部门主键 */
	private Long refDepartmentId;
	/** 是否有效10有效，99废弃 */
	private java.lang.String status;

	/** 计算总记录数 */
	public int calcItemCount(Object t) {
		return 0;
	}

	

}