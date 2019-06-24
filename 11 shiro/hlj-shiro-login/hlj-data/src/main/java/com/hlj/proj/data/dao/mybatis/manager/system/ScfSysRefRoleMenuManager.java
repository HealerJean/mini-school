/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.dao.mybatis.manager.system;

import com.hlj.proj.data.common.paging.Pagenation;
import com.hlj.proj.data.dao.mybatis.dao.system.ScfSysRefRoleMenuDao;
import com.hlj.proj.data.pojo.system.ScfSysMenu;
import com.hlj.proj.data.pojo.system.ScfSysRefRoleMenu;
import com.hlj.proj.data.pojo.system.ScfSysRefRoleMenuPage;
import com.hlj.proj.data.pojo.system.ScfSysRefRoleMenuQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author duyang
 * @ClassName: ScfSysRefRoleMenuManager
 * @date 2099/1/1
 * @Description: ScfSysRefRoleMenuManager
 */
@Component("scfSysRefRoleMenuManager")
public class ScfSysRefRoleMenuManager {

	@Autowired
	@Qualifier("scfSysRefRoleMenuDao")
	private ScfSysRefRoleMenuDao scfSysRefRoleMenuDao;

	public ScfSysRefRoleMenuDao getDao() {
		return this.scfSysRefRoleMenuDao;
	}

	public int save(ScfSysRefRoleMenu scfSysRefRoleMenu) {
		int cnt = 0;
		if (scfSysRefRoleMenu.getId() == null) {
			cnt = scfSysRefRoleMenuDao.insertSelective(scfSysRefRoleMenu);
		} else {
			cnt = scfSysRefRoleMenuDao.updateByPrimaryKeySelective(scfSysRefRoleMenu);
		}
		return cnt;
	}

	public int update(ScfSysRefRoleMenu scfSysRefRoleMenu) {
		return scfSysRefRoleMenuDao.updateByPrimaryKey(scfSysRefRoleMenu);
	}

	public int updateSelective(ScfSysRefRoleMenu scfSysRefRoleMenu) {
		return scfSysRefRoleMenuDao.updateByPrimaryKeySelective(scfSysRefRoleMenu);
	}

	public int insert(ScfSysRefRoleMenu scfSysRefRoleMenu) {
		return scfSysRefRoleMenuDao.insert(scfSysRefRoleMenu);
	}

	public int insertSelective(ScfSysRefRoleMenu scfSysRefRoleMenu) {
		return scfSysRefRoleMenuDao.insertSelective(scfSysRefRoleMenu);
	}

	public int batchInsert(List<ScfSysRefRoleMenu> list){
		return scfSysRefRoleMenuDao.batchInsert(list);
	}

	public ScfSysRefRoleMenu findById(long id) {
		return scfSysRefRoleMenuDao.selectByPrimaryKey(id);
	}

	public ScfSysRefRoleMenu findByQueryContion(ScfSysRefRoleMenuQuery query) {
		List<ScfSysRefRoleMenu> list = scfSysRefRoleMenuDao.selectByExample(query);
		if(null!=list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public List<ScfSysRefRoleMenu> queryList(ScfSysRefRoleMenuQuery query) {
		return scfSysRefRoleMenuDao.selectByExample(query);
	}

	public int deleteById(long id) {
		return scfSysRefRoleMenuDao.deleteByPrimaryKey(id);
	}

	public int delete(ScfSysRefRoleMenuQuery query) {
		return scfSysRefRoleMenuDao.deleteByExample(query);
	}

	public ScfSysRefRoleMenuPage queryPageList(ScfSysRefRoleMenuQuery query) {
		ScfSysRefRoleMenuPage scfSysRefRoleMenuPage = new ScfSysRefRoleMenuPage();
		Integer itemCount = scfSysRefRoleMenuDao.countByExample(query);
		query.setItemCount(itemCount);

		if (itemCount == 0) {
			scfSysRefRoleMenuPage.setValues(null);
		} else {
			scfSysRefRoleMenuPage.setValues(scfSysRefRoleMenuDao.selectPageByExample(query));
		}

		scfSysRefRoleMenuPage.setPagenation(new Pagenation(query.getPageNo(), query.getPageSize(), query.getItemCount()));
		return scfSysRefRoleMenuPage;
	}

	public List<ScfSysMenu> queryListToMenu(ScfSysRefRoleMenuQuery query) {
		return scfSysRefRoleMenuDao.selectByExampleToMenu(query);
	}

	public List<ScfSysMenu> selectMenusByRoleId(Map<String, Object> map) {
		return scfSysRefRoleMenuDao.selectMenusByRoleId(map);
	}
}
