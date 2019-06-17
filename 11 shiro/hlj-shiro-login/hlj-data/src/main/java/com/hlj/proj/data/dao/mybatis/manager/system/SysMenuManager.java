/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.dao.mybatis.manager.system;

import com.hlj.proj.data.common.paging.Pagenation;
import com.hlj.proj.data.dao.mybatis.dao.system.SysMenuDao;
import com.hlj.proj.data.pojo.system.SysMenu;
import com.hlj.proj.data.pojo.system.SysMenuPage;
import com.hlj.proj.data.pojo.system.SysMenuQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * @author zhangyujin
 * @ClassName: SysMenuManager
 * @date 2099/1/1
 * @Description: SysMenuManager
 */
@Component("syssysMenuManager")
public class SysMenuManager {

	@Autowired
	@Qualifier("sysMenuDao")
	private SysMenuDao sysMenuDao;

	public SysMenuDao getDao() {
		return this.sysMenuDao;
	}

	public int save(SysMenu sysMenu) {
		int cnt = 0;
		if (sysMenu.getId() == null) {
			cnt = sysMenuDao.insertSelective(sysMenu);
		} else {
			cnt = sysMenuDao.updateByPrimaryKeySelective(sysMenu);
		}
		return cnt;
	}
	
	public int update(SysMenu sysMenu) {
		return sysMenuDao.updateByPrimaryKey(sysMenu);
	}
	
	public int updateSelective(SysMenu sysMenu) {
		return sysMenuDao.updateByPrimaryKeySelective(sysMenu);
	}
	
	public int insert(SysMenu sysMenu) {
		return sysMenuDao.insert(sysMenu);
	}
	
	public int insertSelective(SysMenu sysMenu) {
		return sysMenuDao.insertSelective(sysMenu);
	}
	
	public int batchInsert(List<SysMenu> list){
		return sysMenuDao.batchInsert(list);
	}

	public SysMenu findById(long id) {
		return sysMenuDao.selectByPrimaryKey(id);
	}
	
	public SysMenu findByQueryContion(SysMenuQuery query) {
		List<SysMenu> list = sysMenuDao.selectByExample(query);
		if(null!=list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public List<SysMenu> queryList(SysMenuQuery query) {
		return sysMenuDao.selectByExample(query);
	}

	public int deleteById(long id) {
		return sysMenuDao.deleteByPrimaryKey(id);
	}

	public int delete(SysMenuQuery query) {
		return sysMenuDao.deleteByExample(query);
	}

	public SysMenuPage queryPageList(SysMenuQuery query) {
		SysMenuPage sysMenuPage = new SysMenuPage();
		Integer itemCount = sysMenuDao.countByExample(query);
		query.setItemCount(itemCount);

		if (itemCount == 0) {
			sysMenuPage.setValues(null);
		} else {
			sysMenuPage.setValues(sysMenuDao.selectPageByExample(query));
		}

		sysMenuPage.setPagenation(new Pagenation(query.getPageNo(), query.getPageSize(), query.getItemCount()));
		return sysMenuPage;
	}




	public SysMenuPage queryPageListLike(SysMenuQuery query) {
		SysMenuPage menuPage = new SysMenuPage();
		Integer itemCount = sysMenuDao.countByExampleLike(query);
		query.setItemCount(itemCount);

		if (itemCount == 0) {
			menuPage.setValues(null);
		} else {
			menuPage.setValues(sysMenuDao.selectPageByExampleLike(query));
		}

		menuPage.setPagenation(new Pagenation(query.getPageNo(), query.getPageSize(), query.getItemCount()));
		return menuPage;
	}

	public  List<SysMenu> findByIds(List<Long> ids) {
		return sysMenuDao.selectByPrimaryKeys(ids);
	}

	public int batchUpdate(List<SysMenu> list) {
		return sysMenuDao.batchUpdate(list);
	}

}
