/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.dao.mybatis.manager.user;

import com.hlj.proj.data.common.paging.Pagenation;
import com.hlj.proj.data.dao.mybatis.dao.user.ScfUserInfoDao;
import com.hlj.proj.data.pojo.user.ScfUserInfo;
import com.hlj.proj.data.pojo.user.ScfUserInfoPage;
import com.hlj.proj.data.pojo.user.ScfUserInfoQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author duyang
 * @ClassName: ScfUserInfoManager
 * @date 2099/1/1
 * @Description: ScfUserInfoManager
 */
@Component("scfUserInfoManager")
public class ScfUserInfoManager {

	@Autowired
	@Qualifier("scfUserInfoDao")
	private ScfUserInfoDao scfUserInfoDao;

	public ScfUserInfoDao getDao() {
		return this.scfUserInfoDao;
	}

	public int save(ScfUserInfo scfUserInfo) {
		int cnt = 0;
		if (scfUserInfo.getId() == null) {
			cnt = scfUserInfoDao.insertSelective(scfUserInfo);
		} else {
			cnt = scfUserInfoDao.updateByPrimaryKeySelective(scfUserInfo);
		}
		return cnt;
	}

	public int update(ScfUserInfo scfUserInfo) {
		return scfUserInfoDao.updateByPrimaryKey(scfUserInfo);
	}

	public int updateSelective(ScfUserInfo scfUserInfo) {
		return scfUserInfoDao.updateByPrimaryKeySelective(scfUserInfo);
	}

	public int insert(ScfUserInfo scfUserInfo) {
		return scfUserInfoDao.insert(scfUserInfo);
	}

	public int insertSelective(ScfUserInfo scfUserInfo) {
		return scfUserInfoDao.insertSelective(scfUserInfo);
	}

	public int batchInsert(List<ScfUserInfo> list){
		return scfUserInfoDao.batchInsert(list);
	}

	public int batchUpdate(List<ScfUserInfo> list){
		return scfUserInfoDao.batchUpdate(list);
	}

	public ScfUserInfo findById(long id) {
		return scfUserInfoDao.selectByPrimaryKey(id);
	}

	public ScfUserInfo findByQueryContion(ScfUserInfoQuery query) {
		List<ScfUserInfo> list = scfUserInfoDao.selectByExample(query);
		if(null!=list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public List<ScfUserInfo> queryList(ScfUserInfoQuery query) {
		return scfUserInfoDao.selectByExample(query);
	}

	public int deleteById(long id) {
		return scfUserInfoDao.deleteByPrimaryKey(id);
	}

	public int delete(ScfUserInfoQuery query) {
		return scfUserInfoDao.deleteByExample(query);
	}

	public ScfUserInfoPage queryPageList(ScfUserInfoQuery query) {
		ScfUserInfoPage scfUserInfoPage = new ScfUserInfoPage();
		Integer itemCount = scfUserInfoDao.countByExample(query);
		query.setItemCount(itemCount);

		if (itemCount == 0) {
			scfUserInfoPage.setValues(null);
		} else {
			scfUserInfoPage.setValues(scfUserInfoDao.selectPageByExample(query));
		}

		scfUserInfoPage.setPagenation(new Pagenation(query.getPageNo(), query.getPageSize(), query.getItemCount()));
		return scfUserInfoPage;
	}

	public int countByExample(ScfUserInfoQuery example) {
		return scfUserInfoDao.countByExample(example);
	}



	public ScfUserInfoPage queryPageListByDepartment(ScfUserInfoQuery query) {
		ScfUserInfoPage scfUserInfoPage = new ScfUserInfoPage();
		Integer itemCount = scfUserInfoDao.countUserByDepartment(query);
		query.setItemCount(itemCount);

		if (itemCount == 0) {
			scfUserInfoPage.setValues(null);
		} else {
			scfUserInfoPage.setValues(scfUserInfoDao.selectUserByDepartment(query));
		}

		scfUserInfoPage.setPagenation(new Pagenation(query.getPageNo(), query.getPageSize(), query.getItemCount()));
		return scfUserInfoPage;
	}

}
