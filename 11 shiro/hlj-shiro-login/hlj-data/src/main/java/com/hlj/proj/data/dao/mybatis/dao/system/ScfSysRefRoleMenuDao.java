/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.dao.mybatis.dao.system;

import com.hlj.proj.data.dao.mybatis.dao.BaseDao;
import com.hlj.proj.data.pojo.system.ScfSysMenu;
import com.hlj.proj.data.pojo.system.ScfSysRefRoleMenu;
import com.hlj.proj.data.pojo.system.ScfSysRefRoleMenuQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author duyang
 * @ClassName: ScfSysRefRoleMenuDao
 * @date 2099/1/1
 * @Description: ScfSysRefRoleMenuDao
 */
@Repository("scfSysRefRoleMenuDao")
public class ScfSysRefRoleMenuDao extends BaseDao {

	public int countByExample(ScfSysRefRoleMenuQuery example) {
		return super.getSqlSession().selectOne("ScfSysRefRoleMenuMapper.countByExample", example);
	}

	public int deleteByExample(ScfSysRefRoleMenuQuery example) {
		return super.getSqlSession().delete("ScfSysRefRoleMenuMapper.deleteByExample", example);
	}

	public int deleteByPrimaryKey(long id) {
		return super.getSqlSession().delete("ScfSysRefRoleMenuMapper.deleteByPrimaryKey", id);
	}

	public int insert(ScfSysRefRoleMenu record) {
		return super.getSqlSession().insert("ScfSysRefRoleMenuMapper.insert", record);
	}

	public int insertSelective(ScfSysRefRoleMenu record) {
		return super.getSqlSession().insert("ScfSysRefRoleMenuMapper.insertSelective", record);
	}

	public int batchInsert(List<ScfSysRefRoleMenu> list) {
		return super.batchInsert("ScfSysRefRoleMenuMapper.insertSelective", list);
	}

	public List<ScfSysRefRoleMenu> selectByExample(ScfSysRefRoleMenuQuery example) {
		return super.getSqlSession().selectList("ScfSysRefRoleMenuMapper.selectByExample", example);
	}

	public List<ScfSysRefRoleMenu> selectPageByExample(ScfSysRefRoleMenuQuery example) {
		return super.getSqlSession().selectList("ScfSysRefRoleMenuMapper.selectPageByExample", example);
	}

	public ScfSysRefRoleMenu selectByPrimaryKey(long id) {
		return super.getSqlSession().selectOne("ScfSysRefRoleMenuMapper.selectByPrimaryKey", id);
	}

	public int updateByPrimaryKeySelective(ScfSysRefRoleMenu record) {
		return super.getSqlSession().update("ScfSysRefRoleMenuMapper.updateByPrimaryKeySelective", record);
	}

	public int updateByPrimaryKey(ScfSysRefRoleMenu record) {
		return super.getSqlSession().update("ScfSysRefRoleMenuMapper.updateByPrimaryKey", record);
	}

	public List<ScfSysMenu> selectByExampleToMenu(ScfSysRefRoleMenuQuery example) {
		return super.getSqlSession().selectList("ScfSysRefRoleMenuMapper.selectByExampleToMenu", example);
	}

	public List<ScfSysMenu> selectMenusByRoleId(Map<String, Object> map) {
		return super.getSqlSession().selectList("ScfSysRefRoleMenuMapper.selectMenusByRoleId", map);
	}
}
