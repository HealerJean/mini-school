/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.dao.mybatis.manager.system;

import com.hlj.proj.data.common.paging.Pagenation;
import com.hlj.proj.data.dao.mybatis.dao.system.ScfSysRefUserRoleDao;
import com.hlj.proj.data.pojo.system.ScfSysRefUserRole;
import com.hlj.proj.data.pojo.system.ScfSysRefUserRolePage;
import com.hlj.proj.data.pojo.system.ScfSysRefUserRoleQuery;
import com.hlj.proj.data.pojo.system.ScfSysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author duyang
 * @ClassName: ScfSysRefUserRoleManager
 * @date 2099/1/1
 * @Description: ScfSysRefUserRoleManager
 */
@Component("scfSysRefUserRoleManager")
public class ScfSysRefUserRoleManager {

	@Autowired
	@Qualifier("scfSysRefUserRoleDao")
	private ScfSysRefUserRoleDao scfSysRefUserRoleDao;

	public ScfSysRefUserRoleDao getDao() {
		return this.scfSysRefUserRoleDao;
	}

	public int save(ScfSysRefUserRole scfSysRefUserRole) {
		int cnt = 0;
		if (scfSysRefUserRole.getId() == null) {
			cnt = scfSysRefUserRoleDao.insertSelective(scfSysRefUserRole);
		} else {
			cnt = scfSysRefUserRoleDao.updateByPrimaryKeySelective(scfSysRefUserRole);
		}
		return cnt;
	}

	public int update(ScfSysRefUserRole scfSysRefUserRole) {
		return scfSysRefUserRoleDao.updateByPrimaryKey(scfSysRefUserRole);
	}

	public int updateSelective(ScfSysRefUserRole scfSysRefUserRole) {
		return scfSysRefUserRoleDao.updateByPrimaryKeySelective(scfSysRefUserRole);
	}

	public int insert(ScfSysRefUserRole scfSysRefUserRole) {
		return scfSysRefUserRoleDao.insert(scfSysRefUserRole);
	}

	public int insertSelective(ScfSysRefUserRole scfSysRefUserRole) {
		return scfSysRefUserRoleDao.insertSelective(scfSysRefUserRole);
	}

	public int batchInsert(List<ScfSysRefUserRole> list){
		return scfSysRefUserRoleDao.batchInsert(list);
	}

	public ScfSysRefUserRole findById(long id) {
		return scfSysRefUserRoleDao.selectByPrimaryKey(id);
	}

	public ScfSysRefUserRole findByQueryContion(ScfSysRefUserRoleQuery query) {
		List<ScfSysRefUserRole> list = scfSysRefUserRoleDao.selectByExample(query);
		if(null!=list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public List<ScfSysRefUserRole> queryList(ScfSysRefUserRoleQuery query) {
		return scfSysRefUserRoleDao.selectByExample(query);
	}

	public int deleteById(long id) {
		return scfSysRefUserRoleDao.deleteByPrimaryKey(id);
	}

	public int delete(ScfSysRefUserRoleQuery query) {
		return scfSysRefUserRoleDao.deleteByExample(query);
	}

	public ScfSysRefUserRolePage queryPageList(ScfSysRefUserRoleQuery query) {
		ScfSysRefUserRolePage scfSysRefUserRolePage = new ScfSysRefUserRolePage();
		Integer itemCount = scfSysRefUserRoleDao.countByExample(query);
		query.setItemCount(itemCount);

		if (itemCount == 0) {
			scfSysRefUserRolePage.setValues(null);
		} else {
			scfSysRefUserRolePage.setValues(scfSysRefUserRoleDao.selectPageByExample(query));
		}

		scfSysRefUserRolePage.setPagenation(new Pagenation(query.getPageNo(), query.getPageSize(), query.getItemCount()));
		return scfSysRefUserRolePage;
	}

	public List<ScfSysRole> queryListToRole(ScfSysRefUserRoleQuery query) {
		return scfSysRefUserRoleDao.queryListToRole(query);
	}
}
