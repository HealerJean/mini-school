/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.dao.mybatis.dao.system;

import com.hlj.proj.data.dao.mybatis.dao.BaseDao;
import com.hlj.proj.data.pojo.system.SysRole;
import com.hlj.proj.data.pojo.system.SysUserRole;
import com.hlj.proj.data.pojo.system.SysUserRoleQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author zhangyujin
 * @ClassName: SysUserRoleDao
 * @date 2099/1/1
 * @Description: SysUserRoleDao
 */
@Repository("sysUserRoleDao")
public class SysUserRoleDao extends BaseDao {

	public int countByExample(SysUserRoleQuery example) {
		return super.getSqlSession().selectOne("SysUserRoleMapper.countByExample", example);
	}

	public int deleteByExample(SysUserRoleQuery example) {
		return super.getSqlSession().delete("SysUserRoleMapper.deleteByExample", example);
	}

	public int deleteByPrimaryKey(long id) {
		return super.getSqlSession().delete("SysUserRoleMapper.deleteByPrimaryKey", id);
	}

	public int insert(SysUserRole record) {
		return super.getSqlSession().insert("SysUserRoleMapper.insert", record);
	}

	public int insertSelective(SysUserRole record) {
		return super.getSqlSession().insert("SysUserRoleMapper.insertSelective", record);
	}
	
	public int batchInsert(List<SysUserRole> list) {
		return super.batchInsert("SysUserRoleMapper.insertSelective", list);
	}

	public List<SysUserRole> selectByExample(SysUserRoleQuery example) {
		return super.getSqlSession().selectList("SysUserRoleMapper.selectByExample", example);
	}

	public List<SysUserRole> selectPageByExample(SysUserRoleQuery example) {
		return super.getSqlSession().selectList("SysUserRoleMapper.selectPageByExample", example);
	}

	public SysUserRole selectByPrimaryKey(long id) {
		return super.getSqlSession().selectOne("SysUserRoleMapper.selectByPrimaryKey", id);
	}

	public int updateByPrimaryKeySelective(SysUserRole record) {
		return super.getSqlSession().update("SysUserRoleMapper.updateByPrimaryKeySelective", record);
	}

	public int updateByPrimaryKey(SysUserRole record) {
		return super.getSqlSession().update("SysUserRoleMapper.updateByPrimaryKey", record);
	}



	public List<SysRole> queryListToRole(SysUserRoleQuery example) {
		return super.getSqlSession().selectList("SysUserRoleMapper.selectByExampleToRole", example);
	}

}
