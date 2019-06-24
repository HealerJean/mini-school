/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.dao.mybatis.manager.user;

import com.hlj.proj.data.common.paging.Pagenation;
import com.hlj.proj.data.dao.mybatis.dao.user.ScfUserRefUserDepartmentDao;
import com.hlj.proj.data.pojo.user.ScfUserDepartment;
import com.hlj.proj.data.pojo.user.ScfUserRefUserDepartment;
import com.hlj.proj.data.pojo.user.ScfUserRefUserDepartmentPage;
import com.hlj.proj.data.pojo.user.ScfUserRefUserDepartmentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author duyang
 * @ClassName: ScfUserRefUserDepartmentManager
 * @date 2099/1/1
 * @Description: ScfUserRefUserDepartmentManager
 */
@Component("scfUserRefUserDepartmentManager")
public class ScfUserRefUserDepartmentManager {

	@Autowired
	@Qualifier("scfUserRefUserDepartmentDao")
	private ScfUserRefUserDepartmentDao scfUserRefUserDepartmentDao;

	public ScfUserRefUserDepartmentDao getDao() {
		return this.scfUserRefUserDepartmentDao;
	}

	public int save(ScfUserRefUserDepartment scfUserRefUserDepartment) {
		int cnt = 0;
		if (scfUserRefUserDepartment.getId() == null) {
			cnt = scfUserRefUserDepartmentDao.insertSelective(scfUserRefUserDepartment);
		} else {
			cnt = scfUserRefUserDepartmentDao.updateByPrimaryKeySelective(scfUserRefUserDepartment);
		}
		return cnt;
	}

	public int update(ScfUserRefUserDepartment scfUserRefUserDepartment) {
		return scfUserRefUserDepartmentDao.updateByPrimaryKey(scfUserRefUserDepartment);
	}

	public int updateSelective(ScfUserRefUserDepartment scfUserRefUserDepartment) {
		return scfUserRefUserDepartmentDao.updateByPrimaryKeySelective(scfUserRefUserDepartment);
	}

	public int insert(ScfUserRefUserDepartment scfUserRefUserDepartment) {
		return scfUserRefUserDepartmentDao.insert(scfUserRefUserDepartment);
	}

	public int insertSelective(ScfUserRefUserDepartment scfUserRefUserDepartment) {
		return scfUserRefUserDepartmentDao.insertSelective(scfUserRefUserDepartment);
	}

	public int batchInsert(List<ScfUserRefUserDepartment> list){
		return scfUserRefUserDepartmentDao.batchInsert(list);
	}

	public ScfUserRefUserDepartment findById(long id) {
		return scfUserRefUserDepartmentDao.selectByPrimaryKey(id);
	}

	public ScfUserRefUserDepartment findByQueryContion(ScfUserRefUserDepartmentQuery query) {
		List<ScfUserRefUserDepartment> list = scfUserRefUserDepartmentDao.selectByExample(query);
		if(null!=list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public List<ScfUserRefUserDepartment> queryList(ScfUserRefUserDepartmentQuery query) {
		return scfUserRefUserDepartmentDao.selectByExample(query);
	}

	public int deleteById(long id) {
		return scfUserRefUserDepartmentDao.deleteByPrimaryKey(id);
	}

	public int delete(ScfUserRefUserDepartmentQuery query) {
		return scfUserRefUserDepartmentDao.deleteByExample(query);
	}

	public ScfUserRefUserDepartmentPage queryPageList(ScfUserRefUserDepartmentQuery query) {
		ScfUserRefUserDepartmentPage scfUserRefUserDepartmentPage = new ScfUserRefUserDepartmentPage();
		Integer itemCount = scfUserRefUserDepartmentDao.countByExample(query);
		query.setItemCount(itemCount);

		if (itemCount == 0) {
			scfUserRefUserDepartmentPage.setValues(null);
		} else {
			scfUserRefUserDepartmentPage.setValues(scfUserRefUserDepartmentDao.selectPageByExample(query));
		}

		scfUserRefUserDepartmentPage.setPagenation(new Pagenation(query.getPageNo(), query.getPageSize(), query.getItemCount()));
		return scfUserRefUserDepartmentPage;
	}

	public ScfUserDepartment findByQueryContionToDepartment(ScfUserRefUserDepartmentQuery query) {
		ScfUserDepartment scfUserDepartment = scfUserRefUserDepartmentDao.selectByExampleToUser(query);
		return scfUserDepartment;
	}
}
