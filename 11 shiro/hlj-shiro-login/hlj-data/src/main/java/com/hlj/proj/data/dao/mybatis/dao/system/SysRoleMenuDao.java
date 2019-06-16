/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.dao.mybatis.dao.system;

import java.util.List;
import java.util.Map;

import com.hlj.proj.data.dao.mybatis.dao.BaseDao;
import com.hlj.proj.data.pojo.system.SysMenu;
import com.hlj.proj.data.pojo.system.SysRoleMenu;
import com.hlj.proj.data.pojo.system.SysRoleMenuQuery;
import org.springframework.stereotype.Repository;

/**
 * @author zhangyujin
 * @ClassName: SysRoleMenuDao
 * @date 2099/1/1
 * @Description: SysRoleMenuDao
 */
@Repository("sysRoleMenuDao")
public class SysRoleMenuDao extends BaseDao {

	public int countByExample(SysRoleMenuQuery example) {
		return super.getSqlSession().selectOne("SysRoleMenuMapper.countByExample", example);
	}

	public int deleteByExample(SysRoleMenuQuery example) {
		return super.getSqlSession().delete("SysRoleMenuMapper.deleteByExample", example);
	}

	public int deleteByPrimaryKey(long id) {
		return super.getSqlSession().delete("SysRoleMenuMapper.deleteByPrimaryKey", id);
	}

	public int insert(SysRoleMenu record) {
		return super.getSqlSession().insert("SysRoleMenuMapper.insert", record);
	}

	public int insertSelective(SysRoleMenu record) {
		return super.getSqlSession().insert("SysRoleMenuMapper.insertSelective", record);
	}
	
	public int batchInsert(List<SysRoleMenu> list) {
		return super.batchInsert("SysRoleMenuMapper.insertSelective", list);
	}

	public List<SysRoleMenu> selectByExample(SysRoleMenuQuery example) {
		return super.getSqlSession().selectList("SysRoleMenuMapper.selectByExample", example);
	}

	public List<SysRoleMenu> selectPageByExample(SysRoleMenuQuery example) {
		return super.getSqlSession().selectList("SysRoleMenuMapper.selectPageByExample", example);
	}

	public SysRoleMenu selectByPrimaryKey(long id) {
		return super.getSqlSession().selectOne("SysRoleMenuMapper.selectByPrimaryKey", id);
	}

	public int updateByPrimaryKeySelective(SysRoleMenu record) {
		return super.getSqlSession().update("SysRoleMenuMapper.updateByPrimaryKeySelective", record);
	}

	public int updateByPrimaryKey(SysRoleMenu record) {
		return super.getSqlSession().update("SysRoleMenuMapper.updateByPrimaryKey", record);
	}

	public List<SysMenu> selectByExampleToMenu(SysRoleMenuQuery example) {
		return super.getSqlSession().selectList("SysRoleMenuMapper.selectByExampleToMenu", example);
	}

	public List<SysMenu> selectMenusByRoleId(Map<String, Object> map) {
		return super.getSqlSession().selectList("SysRoleMenuMapper.selectMenusByRoleId", map);
	}

}
