/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.dao.mybatis.manager.system;

import com.hlj.proj.data.common.paging.Pagenation;
import com.hlj.proj.data.dao.mybatis.dao.system.SysRoleMenuDao;
import com.hlj.proj.data.pojo.system.SysMenu;
import com.hlj.proj.data.pojo.system.SysRoleMenu;
import com.hlj.proj.data.pojo.system.SysRoleMenuPage;
import com.hlj.proj.data.pojo.system.SysRoleMenuQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author zhangyujin
 * @ClassName: SysRoleMenuManager
 * @date 2099/1/1
 * @Description: SysRoleMenuManager
 */
@Component("sysRolesysMenuManager")
public class SysRoleMenuManager {

	@Autowired
	@Qualifier("sysRoleMenuDao")
	private SysRoleMenuDao sysRoleMenuDao;

	public SysRoleMenuDao getDao() {
		return this.sysRoleMenuDao;
	}

	public int save(SysRoleMenu sysRoleMenu) {
		int cnt = 0;
		if (sysRoleMenu.getId() == null) {
			cnt = sysRoleMenuDao.insertSelective(sysRoleMenu);
		} else {
			cnt = sysRoleMenuDao.updateByPrimaryKeySelective(sysRoleMenu);
		}
		return cnt;
	}
	
	public int update(SysRoleMenu sysRoleMenu) {
		return sysRoleMenuDao.updateByPrimaryKey(sysRoleMenu);
	}
	
	public int updateSelective(SysRoleMenu sysRoleMenu) {
		return sysRoleMenuDao.updateByPrimaryKeySelective(sysRoleMenu);
	}
	
	public int insert(SysRoleMenu sysRoleMenu) {
		return sysRoleMenuDao.insert(sysRoleMenu);
	}
	
	public int insertSelective(SysRoleMenu sysRoleMenu) {
		return sysRoleMenuDao.insertSelective(sysRoleMenu);
	}
	
	public int batchInsert(List<SysRoleMenu> list){
		return sysRoleMenuDao.batchInsert(list);
	}

	public SysRoleMenu findById(long id) {
		return sysRoleMenuDao.selectByPrimaryKey(id);
	}
	
	public SysRoleMenu findByQueryContion(SysRoleMenuQuery query) {
		List<SysRoleMenu> list = sysRoleMenuDao.selectByExample(query);
		if(null!=list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public List<SysRoleMenu> queryList(SysRoleMenuQuery query) {
		return sysRoleMenuDao.selectByExample(query);
	}

	public int deleteById(long id) {
		return sysRoleMenuDao.deleteByPrimaryKey(id);
	}

	public int delete(SysRoleMenuQuery query) {
		return sysRoleMenuDao.deleteByExample(query);
	}

	public SysRoleMenuPage queryPageList(SysRoleMenuQuery query) {
		SysRoleMenuPage sysRoleMenuPage = new SysRoleMenuPage();
		Integer itemCount = sysRoleMenuDao.countByExample(query);
		query.setItemCount(itemCount);

		if (itemCount == 0) {
			sysRoleMenuPage.setValues(null);
		} else {
			sysRoleMenuPage.setValues(sysRoleMenuDao.selectPageByExample(query));
		}

		sysRoleMenuPage.setPagenation(new Pagenation(query.getPageNo(), query.getPageSize(), query.getItemCount()));
		return sysRoleMenuPage;
	}



	public List<SysMenu> queryListToMenu(SysRoleMenuQuery query) {
		return sysRoleMenuDao.selectByExampleToMenu(query);
	}

	public List<SysMenu> selectMenusByRoleId(Map<String, Object> map) {
		return sysRoleMenuDao.selectMenusByRoleId(map);
	}

}
