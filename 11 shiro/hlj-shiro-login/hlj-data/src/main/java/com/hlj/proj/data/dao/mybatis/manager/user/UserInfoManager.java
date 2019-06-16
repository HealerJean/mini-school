/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.dao.mybatis.manager.user;

import com.hlj.proj.data.common.paging.Pagenation;
import com.hlj.proj.data.dao.mybatis.dao.user.UserInfoDao;
import com.hlj.proj.data.pojo.user.UserInfo;
import com.hlj.proj.data.pojo.user.UserInfoPage;
import com.hlj.proj.data.pojo.user.UserInfoQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * @author zhangyujin
 * @ClassName: UserInfoManager
 * @date 2099/1/1
 * @Description: UserInfoManager
 */
@Component("userInfoManager")
public class UserInfoManager {

	@Autowired
	@Qualifier("userInfoDao")
	private UserInfoDao userInfoDao;

	public UserInfoDao getDao() {
		return this.userInfoDao;
	}

	public int save(UserInfo userInfo) {
		int cnt = 0;
		if (userInfo.getId() == null) {
			cnt = userInfoDao.insertSelective(userInfo);
		} else {
			cnt = userInfoDao.updateByPrimaryKeySelective(userInfo);
		}
		return cnt;
	}
	
	public int update(UserInfo userInfo) {
		return userInfoDao.updateByPrimaryKey(userInfo);
	}
	
	public int updateSelective(UserInfo userInfo) {
		return userInfoDao.updateByPrimaryKeySelective(userInfo);
	}
	
	public int insert(UserInfo userInfo) {
		return userInfoDao.insert(userInfo);
	}
	
	public int insertSelective(UserInfo userInfo) {
		return userInfoDao.insertSelective(userInfo);
	}
	
	public int batchInsert(List<UserInfo> list){
		return userInfoDao.batchInsert(list);
	}

	public UserInfo findById(long id) {
		return userInfoDao.selectByPrimaryKey(id);
	}
	
	public UserInfo findByQueryContion(UserInfoQuery query) {
		List<UserInfo> list = userInfoDao.selectByExample(query);
		if(null!=list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public List<UserInfo> queryList(UserInfoQuery query) {
		return userInfoDao.selectByExample(query);
	}

	public int deleteById(long id) {
		return userInfoDao.deleteByPrimaryKey(id);
	}

	public int delete(UserInfoQuery query) {
		return userInfoDao.deleteByExample(query);
	}

	public UserInfoPage queryPageList(UserInfoQuery query) {
		UserInfoPage userInfoPage = new UserInfoPage();
		Integer itemCount = userInfoDao.countByExample(query);
		query.setItemCount(itemCount);

		if (itemCount == 0) {
			userInfoPage.setValues(null);
		} else {
			userInfoPage.setValues(userInfoDao.selectPageByExample(query));
		}

		userInfoPage.setPagenation(new Pagenation(query.getPageNo(), query.getPageSize(), query.getItemCount()));
		return userInfoPage;
	}

}
