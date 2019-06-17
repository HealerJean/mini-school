/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.dao.mybatis.dao.system;

import java.util.List;

import com.hlj.proj.data.dao.mybatis.dao.BaseDao;
import com.hlj.proj.data.pojo.system.SysDepartment;
import com.hlj.proj.data.pojo.system.SysDepartmentQuery;
import org.springframework.stereotype.Repository;
/**
 * @author zhangyujin
 * @ClassName: SysDepartmentDao
 * @date 2099/1/1
 * @Description: SysDepartmentDao
 */
@Repository("sysDepartmentDao")
public class SysDepartmentDao extends BaseDao {

	public int countByExample(SysDepartmentQuery example) {
		return super.getSqlSession().selectOne("SysDepartmentMapper.countByExample", example);
	}

	public int deleteByExample(SysDepartmentQuery example) {
		return super.getSqlSession().delete("SysDepartmentMapper.deleteByExample", example);
	}

	public int deleteByPrimaryKey(long id) {
		return super.getSqlSession().delete("SysDepartmentMapper.deleteByPrimaryKey", id);
	}

	public int insert(SysDepartment record) {
		return super.getSqlSession().insert("SysDepartmentMapper.insert", record);
	}

	public int insertSelective(SysDepartment record) {
		return super.getSqlSession().insert("SysDepartmentMapper.insertSelective", record);
	}
	
	public int batchInsert(List<SysDepartment> list) {
		return super.batchInsert("SysDepartmentMapper.insertSelective", list);
	}

	public List<SysDepartment> selectByExample(SysDepartmentQuery example) {
		return super.getSqlSession().selectList("SysDepartmentMapper.selectByExample", example);
	}

	public List<SysDepartment> selectPageByExample(SysDepartmentQuery example) {
		return super.getSqlSession().selectList("SysDepartmentMapper.selectPageByExample", example);
	}

	public SysDepartment selectByPrimaryKey(long id) {
		return super.getSqlSession().selectOne("SysDepartmentMapper.selectByPrimaryKey", id);
	}

	public int updateByPrimaryKeySelective(SysDepartment record) {
		return super.getSqlSession().update("SysDepartmentMapper.updateByPrimaryKeySelective", record);
	}

	public int updateByPrimaryKey(SysDepartment record) {
		return super.getSqlSession().update("SysDepartmentMapper.updateByPrimaryKey", record);
	}

}
