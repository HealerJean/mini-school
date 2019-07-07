/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.dao.mybatis.dao.system;

import com.hlj.proj.data.dao.mybatis.dao.BaseDao;
import com.hlj.proj.data.pojo.system.ScfSysMenu;
import com.hlj.proj.data.pojo.system.ScfSysMenuQuery;
import com.hlj.proj.data.pojo.system.ScfSysRefRoleMenuQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author duyang
 * @ClassName: ScfSysMenuDao
 * @date 2099/1/1
 * @Description: ScfSysMenuDao
 */
@Repository("scfSysMenuDao")
public class ScfSysMenuDao extends BaseDao {

	public int countByExample(ScfSysMenuQuery example) {
		return super.getSqlSession().selectOne("ScfSysMenuMapper.countByExample", example);
	}

	public int countByExampleLike(ScfSysMenuQuery example) {
		return super.getSqlSession().selectOne("ScfSysMenuMapper.countByExampleLike", example);
	}

	public int deleteByExample(ScfSysMenuQuery example) {
		return super.getSqlSession().delete("ScfSysMenuMapper.deleteByExample", example);
	}

	public int deleteByPrimaryKey(long id) {
		return super.getSqlSession().delete("ScfSysMenuMapper.deleteByPrimaryKey", id);
	}

	public int insert(ScfSysMenu record) {
		return super.getSqlSession().insert("ScfSysMenuMapper.insert", record);
	}

	public int insertSelective(ScfSysMenu record) {
		return super.getSqlSession().insert("ScfSysMenuMapper.insertSelective", record);
	}

	public int batchInsert(List<ScfSysMenu> list) {
		return super.batchInsert("ScfSysMenuMapper.insertSelective", list);
	}

	public List<ScfSysMenu> selectByExample(ScfSysMenuQuery example) {
		return super.getSqlSession().selectList("ScfSysMenuMapper.selectByExample", example);
	}

	public List<ScfSysMenu> selectPageByExample(ScfSysMenuQuery example) {
		return super.getSqlSession().selectList("ScfSysMenuMapper.selectPageByExample", example);
	}

	public List<ScfSysMenu> selectPageByExampleLike(ScfSysMenuQuery example) {
		return super.getSqlSession().selectList("ScfSysMenuMapper.selectPageByExampleLike", example);
	}

	public ScfSysMenu selectByPrimaryKey(long id) {
		return super.getSqlSession().selectOne("ScfSysMenuMapper.selectByPrimaryKey", id);
	}

	public int updateByPrimaryKeySelective(ScfSysMenu record) {
		return super.getSqlSession().update("ScfSysMenuMapper.updateByPrimaryKeySelective", record);
	}

	public int updateByPrimaryKey(ScfSysMenu record) {
		return super.getSqlSession().update("ScfSysMenuMapper.updateByPrimaryKey", record);
	}

	public List<ScfSysMenu> selectByPrimaryKeys(List<Long> ids) {
		return super.getSqlSession().selectList("ScfSysMenuMapper.selectByPrimaryKeys", ids);
	}

	public int batchUpdate(List<ScfSysMenu> list) {
		return super.batchUpdate("ScfSysMenuMapper.updateByPrimaryKeySelective", list);
	}


	public List<ScfSysMenu> selectByExampleToMenu(ScfSysMenuQuery example) {
		return super.getSqlSession().selectList("ScfSysMenuMapper.selectByExampleToMenu", example);
	}

	public List<ScfSysMenu> selectMenusByRoleId(ScfSysMenuQuery example) {
		return super.getSqlSession().selectList("ScfSysMenuMapper.selectMenusByRoleId", example);
	}
}
