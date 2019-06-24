/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.dao.mybatis.manager.user;

import com.hlj.proj.data.common.paging.Pagenation;
import com.hlj.proj.data.dao.mybatis.dao.user.ScfUserDepartmentDao;
import com.hlj.proj.data.pojo.user.ScfUserDepartment;
import com.hlj.proj.data.pojo.user.ScfUserDepartmentPage;
import com.hlj.proj.data.pojo.user.ScfUserDepartmentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author duyang
 * @ClassName: ScfUserDepartmentManager
 * @date 2099/1/1
 * @Description: ScfUserDepartmentManager
 */
@Component("scfUserDepartmentManager")
public class ScfUserDepartmentManager {

	@Autowired
	@Qualifier("scfUserDepartmentDao")
	private ScfUserDepartmentDao scfUserDepartmentDao;

	public ScfUserDepartmentDao getDao() {
		return this.scfUserDepartmentDao;
	}

	public int save(ScfUserDepartment scfUserDepartment) {
		int cnt = 0;
		if (scfUserDepartment.getId() == null) {
			cnt = scfUserDepartmentDao.insertSelective(scfUserDepartment);
		} else {
			cnt = scfUserDepartmentDao.updateByPrimaryKeySelective(scfUserDepartment);
		}
		return cnt;
	}

	public int update(ScfUserDepartment scfUserDepartment) {
		return scfUserDepartmentDao.updateByPrimaryKey(scfUserDepartment);
	}

	public int updateSelective(ScfUserDepartment scfUserDepartment) {
		return scfUserDepartmentDao.updateByPrimaryKeySelective(scfUserDepartment);
	}

	public int insert(ScfUserDepartment scfUserDepartment) {
		return scfUserDepartmentDao.insert(scfUserDepartment);
	}

	public int insertSelective(ScfUserDepartment scfUserDepartment) {
		return scfUserDepartmentDao.insertSelective(scfUserDepartment);
	}

	public int batchInsert(List<ScfUserDepartment> list){
		return scfUserDepartmentDao.batchInsert(list);
	}

	public ScfUserDepartment findById(long id) {
		return scfUserDepartmentDao.selectByPrimaryKey(id);
	}

	public ScfUserDepartment findByQueryContion(ScfUserDepartmentQuery query) {
		List<ScfUserDepartment> list = scfUserDepartmentDao.selectByExample(query);
		if(null!=list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public List<ScfUserDepartment> queryList(ScfUserDepartmentQuery query) {
		return scfUserDepartmentDao.selectByExample(query);
	}

	public int deleteById(long id) {
		return scfUserDepartmentDao.deleteByPrimaryKey(id);
	}

	public int delete(ScfUserDepartmentQuery query) {
		return scfUserDepartmentDao.deleteByExample(query);
	}

	public ScfUserDepartmentPage queryPageList(ScfUserDepartmentQuery query) {
		ScfUserDepartmentPage scfUserDepartmentPage = new ScfUserDepartmentPage();
		Integer itemCount = scfUserDepartmentDao.countByExample(query);
		query.setItemCount(itemCount);

		if (itemCount == 0) {
			scfUserDepartmentPage.setValues(null);
		} else {
			scfUserDepartmentPage.setValues(scfUserDepartmentDao.selectPageByExample(query));
		}

		scfUserDepartmentPage.setPagenation(new Pagenation(query.getPageNo(), query.getPageSize(), query.getItemCount()));
		return scfUserDepartmentPage;
	}

}
