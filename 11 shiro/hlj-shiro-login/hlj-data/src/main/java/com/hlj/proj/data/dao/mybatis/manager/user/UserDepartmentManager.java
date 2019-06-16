/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.dao.mybatis.manager.user;

import java.util.List;

import com.hlj.proj.data.common.paging.Pagenation;
import com.hlj.proj.data.dao.mybatis.dao.user.UserDepartmentDao;
import com.hlj.proj.data.pojo.user.UserDepartment;
import com.hlj.proj.data.pojo.user.UserDepartmentPage;
import com.hlj.proj.data.pojo.user.UserDepartmentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
/**
 * @author zhangyujin
 * @ClassName: UserDepartmentManager
 * @date 2099/1/1
 * @Description: UserDepartmentManager
 */
@Component("userDepartmentManager")
public class UserDepartmentManager {

	@Autowired
	@Qualifier("userDepartmentDao")
	private UserDepartmentDao userDepartmentDao;

	public UserDepartmentDao getDao() {
		return this.userDepartmentDao;
	}

	public int save(UserDepartment userDepartment) {
		int cnt = 0;
		if (userDepartment.getId() == null) {
			cnt = userDepartmentDao.insertSelective(userDepartment);
		} else {
			cnt = userDepartmentDao.updateByPrimaryKeySelective(userDepartment);
		}
		return cnt;
	}
	
	public int update(UserDepartment userDepartment) {
		return userDepartmentDao.updateByPrimaryKey(userDepartment);
	}
	
	public int updateSelective(UserDepartment userDepartment) {
		return userDepartmentDao.updateByPrimaryKeySelective(userDepartment);
	}
	
	public int insert(UserDepartment userDepartment) {
		return userDepartmentDao.insert(userDepartment);
	}
	
	public int insertSelective(UserDepartment userDepartment) {
		return userDepartmentDao.insertSelective(userDepartment);
	}
	
	public int batchInsert(List<UserDepartment> list){
		return userDepartmentDao.batchInsert(list);
	}

	public UserDepartment findById(long id) {
		return userDepartmentDao.selectByPrimaryKey(id);
	}
	
	public UserDepartment findByQueryContion(UserDepartmentQuery query) {
		List<UserDepartment> list = userDepartmentDao.selectByExample(query);
		if(null!=list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public List<UserDepartment> queryList(UserDepartmentQuery query) {
		return userDepartmentDao.selectByExample(query);
	}

	public int deleteById(long id) {
		return userDepartmentDao.deleteByPrimaryKey(id);
	}

	public int delete(UserDepartmentQuery query) {
		return userDepartmentDao.deleteByExample(query);
	}

	public UserDepartmentPage queryPageList(UserDepartmentQuery query) {
		UserDepartmentPage userDepartmentPage = new UserDepartmentPage();
		Integer itemCount = userDepartmentDao.countByExample(query);
		query.setItemCount(itemCount);

		if (itemCount == 0) {
			userDepartmentPage.setValues(null);
		} else {
			userDepartmentPage.setValues(userDepartmentDao.selectPageByExample(query));
		}

		userDepartmentPage.setPagenation(new Pagenation(query.getPageNo(), query.getPageSize(), query.getItemCount()));
		return userDepartmentPage;
	}

}
