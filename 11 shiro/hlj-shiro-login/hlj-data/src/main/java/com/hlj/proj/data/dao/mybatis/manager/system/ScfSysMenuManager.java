/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.dao.mybatis.manager.system;

import com.hlj.proj.data.common.paging.Pagenation;
import com.hlj.proj.data.dao.mybatis.dao.system.ScfSysMenuDao;
import com.hlj.proj.data.pojo.system.ScfSysMenu;
import com.hlj.proj.data.pojo.system.ScfSysMenuPage;
import com.hlj.proj.data.pojo.system.ScfSysMenuQuery;
import com.hlj.proj.data.pojo.system.ScfSysRefRoleMenuQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author duyang
 * @ClassName: ScfSysMenuManager
 * @date 2099/1/1
 * @Description: ScfSysMenuManager
 */
@Component("scfSysMenuManager")
public class ScfSysMenuManager {

	@Autowired
	@Qualifier("scfSysMenuDao")
	private ScfSysMenuDao scfSysMenuDao;

	public ScfSysMenuDao getDao() {
		return this.scfSysMenuDao;
	}

	public int save(ScfSysMenu scfSysMenu) {
		int cnt = 0;
		if (scfSysMenu.getId() == null) {
			cnt = scfSysMenuDao.insertSelective(scfSysMenu);
		} else {
			cnt = scfSysMenuDao.updateByPrimaryKeySelective(scfSysMenu);
		}
		return cnt;
	}

	public int update(ScfSysMenu scfSysMenu) {
		return scfSysMenuDao.updateByPrimaryKey(scfSysMenu);
	}

	public int updateSelective(ScfSysMenu scfSysMenu) {
		return scfSysMenuDao.updateByPrimaryKeySelective(scfSysMenu);
	}

	public int insert(ScfSysMenu scfSysMenu) {
		return scfSysMenuDao.insert(scfSysMenu);
	}

	public int insertSelective(ScfSysMenu scfSysMenu) {
		return scfSysMenuDao.insertSelective(scfSysMenu);
	}

	public int batchInsert(List<ScfSysMenu> list){
		return scfSysMenuDao.batchInsert(list);
	}

	public ScfSysMenu findById(long id) {
		return scfSysMenuDao.selectByPrimaryKey(id);
	}

	public ScfSysMenu findByQueryContion(ScfSysMenuQuery query) {
		List<ScfSysMenu> list = scfSysMenuDao.selectByExample(query);
		if(null!=list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public List<ScfSysMenu> queryList(ScfSysMenuQuery query) {
		return scfSysMenuDao.selectByExample(query);
	}

	public int deleteById(long id) {
		return scfSysMenuDao.deleteByPrimaryKey(id);
	}

	public int delete(ScfSysMenuQuery query) {
		return scfSysMenuDao.deleteByExample(query);
	}

	public ScfSysMenuPage queryPageList(ScfSysMenuQuery query) {
		ScfSysMenuPage scfSysMenuPage = new ScfSysMenuPage();
		Integer itemCount = scfSysMenuDao.countByExample(query);
		query.setItemCount(itemCount);

		if (itemCount == 0) {
			scfSysMenuPage.setValues(null);
		} else {
			scfSysMenuPage.setValues(scfSysMenuDao.selectPageByExample(query));
		}

		scfSysMenuPage.setPagenation(new Pagenation(query.getPageNo(), query.getPageSize(), query.getItemCount()));
		return scfSysMenuPage;
	}

	public ScfSysMenuPage queryPageListLike(ScfSysMenuQuery query) {
		ScfSysMenuPage menuPage = new ScfSysMenuPage();
		Integer itemCount = scfSysMenuDao.countByExampleLike(query);
		query.setItemCount(itemCount);

		if (itemCount == 0) {
			menuPage.setValues(null);
		} else {
			menuPage.setValues(scfSysMenuDao.selectPageByExampleLike(query));
		}

		menuPage.setPagenation(new Pagenation(query.getPageNo(), query.getPageSize(), query.getItemCount()));
		return menuPage;
	}

	public  List<ScfSysMenu> findByIds(List<Long> ids) {
		return scfSysMenuDao.selectByPrimaryKeys(ids);
	}

	public int batchUpdate(List<ScfSysMenu> list) {
		return scfSysMenuDao.batchUpdate(list);
	}


	public List<ScfSysMenu> queryListToMenu(ScfSysMenuQuery query) {
		return scfSysMenuDao.selectByExampleToMenu(query);
	}

	public List<ScfSysMenu> selectMenusByRoleId(ScfSysMenuQuery  query) {
		return scfSysMenuDao.selectMenusByRoleId(query);
	}
}
