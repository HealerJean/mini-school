/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.dao.mybatis.manager.system;

import com.hlj.proj.data.common.paging.Pagenation;
import com.hlj.proj.data.dao.mybatis.dao.system.ScfSysRoleDao;
import com.hlj.proj.data.pojo.system.ScfSysRole;
import com.hlj.proj.data.pojo.system.ScfSysRolePage;
import com.hlj.proj.data.pojo.system.ScfSysRoleQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author duyang
 * @ClassName: ScfSysRoleManager
 * @date 2099/1/1
 * @Description: ScfSysRoleManager
 */
@Component("scfSysRoleManager")
public class ScfSysRoleManager {

	@Autowired
	@Qualifier("scfSysRoleDao")
	private ScfSysRoleDao scfSysRoleDao;

	public ScfSysRoleDao getDao() {
		return this.scfSysRoleDao;
	}

	public int save(ScfSysRole scfSysRole) {
		int cnt = 0;
		if (scfSysRole.getId() == null) {
			cnt = scfSysRoleDao.insertSelective(scfSysRole);
		} else {
			cnt = scfSysRoleDao.updateByPrimaryKeySelective(scfSysRole);
		}
		return cnt;
	}

	public int update(ScfSysRole scfSysRole) {
		return scfSysRoleDao.updateByPrimaryKey(scfSysRole);
	}

	public int updateSelective(ScfSysRole scfSysRole) {
		return scfSysRoleDao.updateByPrimaryKeySelective(scfSysRole);
	}

	public int insert(ScfSysRole scfSysRole) {
		return scfSysRoleDao.insert(scfSysRole);
	}

	public int insertSelective(ScfSysRole scfSysRole) {
		return scfSysRoleDao.insertSelective(scfSysRole);
	}

	public int batchInsert(List<ScfSysRole> list){
		return scfSysRoleDao.batchInsert(list);
	}

	public ScfSysRole findById(long id) {
		return scfSysRoleDao.selectByPrimaryKey(id);
	}

	public ScfSysRole findByQueryContion(ScfSysRoleQuery query) {
		List<ScfSysRole> list = scfSysRoleDao.selectByExample(query);
		if(null!=list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public List<ScfSysRole> queryList(ScfSysRoleQuery query) {
		return scfSysRoleDao.selectByExample(query);
	}

	public List<ScfSysRole> queryListLike(ScfSysRoleQuery query) {
		return scfSysRoleDao.selectByExampleLike(query);
	}

	public int deleteById(long id) {
		return scfSysRoleDao.deleteByPrimaryKey(id);
	}

	public int delete(ScfSysRoleQuery query) {
		return scfSysRoleDao.deleteByExample(query);
	}

	public ScfSysRolePage queryPageList(ScfSysRoleQuery query) {
		ScfSysRolePage scfSysRolePage = new ScfSysRolePage();
		Integer itemCount = scfSysRoleDao.countByExample(query);
		query.setItemCount(itemCount);

		if (itemCount == 0) {
			scfSysRolePage.setValues(null);
		} else {
			scfSysRolePage.setValues(scfSysRoleDao.selectPageByExample(query));
		}

		scfSysRolePage.setPagenation(new Pagenation(query.getPageNo(), query.getPageSize(), query.getItemCount()));
		return scfSysRolePage;
	}

	public ScfSysRolePage queryPageListLike(ScfSysRoleQuery query) {
		ScfSysRolePage rolePage = new ScfSysRolePage();
		Integer itemCount = scfSysRoleDao.countByExampleLike(query);
		query.setItemCount(itemCount);
		if (itemCount == 0) {
			rolePage.setValues(null);
		} else {
			rolePage.setValues(scfSysRoleDao.selectPageByExampleLike(query));
		}
		rolePage.setPagenation(new Pagenation(query.getPageNo(), query.getPageSize(), query.getItemCount()));
		return rolePage;
	}

}
