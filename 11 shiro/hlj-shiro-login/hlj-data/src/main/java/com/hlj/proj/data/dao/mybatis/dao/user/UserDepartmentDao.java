/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.dao.mybatis.dao.user;

import com.hlj.proj.data.dao.mybatis.dao.BaseDao;
import com.hlj.proj.data.pojo.user.UserDepartment;
import com.hlj.proj.data.pojo.user.UserDepartmentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author zhangyujin
 * @ClassName: UserDepartmentDao
 * @date 2099/1/1
 * @Description: UserDepartmentDao
 */
@Repository("userDepartmentDao")
public class UserDepartmentDao extends BaseDao {

	public int countByExample(UserDepartmentQuery example) {
		return super.getSqlSession().selectOne("UserDepartmentMapper.countByExample", example);
	}

	public int deleteByExample(UserDepartmentQuery example) {
		return super.getSqlSession().delete("UserDepartmentMapper.deleteByExample", example);
	}

	public int deleteByPrimaryKey(long id) {
		return super.getSqlSession().delete("UserDepartmentMapper.deleteByPrimaryKey", id);
	}

	public int insert(UserDepartment record) {
		return super.getSqlSession().insert("UserDepartmentMapper.insert", record);
	}

	public int insertSelective(UserDepartment record) {
		return super.getSqlSession().insert("UserDepartmentMapper.insertSelective", record);
	}
	
	public int batchInsert(List<UserDepartment> list) {
		return super.batchInsert("UserDepartmentMapper.insertSelective", list);
	}

	public List<UserDepartment> selectByExample(UserDepartmentQuery example) {
		return super.getSqlSession().selectList("UserDepartmentMapper.selectByExample", example);
	}

	public List<UserDepartment> selectPageByExample(UserDepartmentQuery example) {
		return super.getSqlSession().selectList("UserDepartmentMapper.selectPageByExample", example);
	}

	public UserDepartment selectByPrimaryKey(long id) {
		return super.getSqlSession().selectOne("UserDepartmentMapper.selectByPrimaryKey", id);
	}

	public int updateByPrimaryKeySelective(UserDepartment record) {
		return super.getSqlSession().update("UserDepartmentMapper.updateByPrimaryKeySelective", record);
	}

	public int updateByPrimaryKey(UserDepartment record) {
		return super.getSqlSession().update("UserDepartmentMapper.updateByPrimaryKey", record);
	}

}
