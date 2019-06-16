/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.dao.mybatis.manager.system;

import com.hlj.proj.data.common.paging.Pagenation;
import com.hlj.proj.data.dao.mybatis.dao.system.SysUserRoleDao;
import com.hlj.proj.data.pojo.system.SysRole;
import com.hlj.proj.data.pojo.system.SysUserRole;
import com.hlj.proj.data.pojo.system.SysUserRolePage;
import com.hlj.proj.data.pojo.system.SysUserRoleQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * @author zhangyujin
 * @ClassName: SysUserRoleManager
 * @date 2099/1/1
 * @Description: SysUserRoleManager
 */
@Component("sysUserRoleManager")
public class SysUserRoleManager {

	@Autowired
	@Qualifier("sysUserRoleDao")
	private SysUserRoleDao sysUserRoleDao;

	public SysUserRoleDao getDao() {
		return this.sysUserRoleDao;
	}

	public int save(SysUserRole sysUserRole) {
		int cnt = 0;
		if (sysUserRole.getId() == null) {
			cnt = sysUserRoleDao.insertSelective(sysUserRole);
		} else {
			cnt = sysUserRoleDao.updateByPrimaryKeySelective(sysUserRole);
		}
		return cnt;
	}
	
	public int update(SysUserRole sysUserRole) {
		return sysUserRoleDao.updateByPrimaryKey(sysUserRole);
	}
	
	public int updateSelective(SysUserRole sysUserRole) {
		return sysUserRoleDao.updateByPrimaryKeySelective(sysUserRole);
	}
	
	public int insert(SysUserRole sysUserRole) {
		return sysUserRoleDao.insert(sysUserRole);
	}
	
	public int insertSelective(SysUserRole sysUserRole) {
		return sysUserRoleDao.insertSelective(sysUserRole);
	}
	
	public int batchInsert(List<SysUserRole> list){
		return sysUserRoleDao.batchInsert(list);
	}

	public SysUserRole findById(long id) {
		return sysUserRoleDao.selectByPrimaryKey(id);
	}
	
	public SysUserRole findByQueryContion(SysUserRoleQuery query) {
		List<SysUserRole> list = sysUserRoleDao.selectByExample(query);
		if(null!=list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public List<SysUserRole> queryList(SysUserRoleQuery query) {
		return sysUserRoleDao.selectByExample(query);
	}

	public int deleteById(long id) {
		return sysUserRoleDao.deleteByPrimaryKey(id);
	}

	public int delete(SysUserRoleQuery query) {
		return sysUserRoleDao.deleteByExample(query);
	}

	public SysUserRolePage queryPageList(SysUserRoleQuery query) {
		SysUserRolePage sysUserRolePage = new SysUserRolePage();
		Integer itemCount = sysUserRoleDao.countByExample(query);
		query.setItemCount(itemCount);

		if (itemCount == 0) {
			sysUserRolePage.setValues(null);
		} else {
			sysUserRolePage.setValues(sysUserRoleDao.selectPageByExample(query));
		}

		sysUserRolePage.setPagenation(new Pagenation(query.getPageNo(), query.getPageSize(), query.getItemCount()));
		return sysUserRolePage;
	}


	public List<SysRole> queryListToRole(SysUserRoleQuery query) {
		return sysUserRoleDao.queryListToRole(query);
	}

}
