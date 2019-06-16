/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.dao.mybatis.manager.system;

import com.hlj.proj.data.common.paging.Pagenation;
import com.hlj.proj.data.dao.mybatis.dao.system.SysDepartmentDao;
import com.hlj.proj.data.pojo.system.SysDepartment;
import com.hlj.proj.data.pojo.system.SysDepartmentPage;
import com.hlj.proj.data.pojo.system.SysDepartmentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * @author zhangyujin
 * @ClassName: SysDepartmentManager
 * @date 2099/1/1
 * @Description: SysDepartmentManager
 */
@Component("sysDepartmentManager")
public class SysDepartmentManager {

	@Autowired
	@Qualifier("sysDepartmentDao")
	private SysDepartmentDao sysDepartmentDao;

	public SysDepartmentDao getDao() {
		return this.sysDepartmentDao;
	}

	public int save(SysDepartment sysDepartment) {
		int cnt = 0;
		if (sysDepartment.getId() == null) {
			cnt = sysDepartmentDao.insertSelective(sysDepartment);
		} else {
			cnt = sysDepartmentDao.updateByPrimaryKeySelective(sysDepartment);
		}
		return cnt;
	}
	
	public int update(SysDepartment sysDepartment) {
		return sysDepartmentDao.updateByPrimaryKey(sysDepartment);
	}
	
	public int updateSelective(SysDepartment sysDepartment) {
		return sysDepartmentDao.updateByPrimaryKeySelective(sysDepartment);
	}
	
	public int insert(SysDepartment sysDepartment) {
		return sysDepartmentDao.insert(sysDepartment);
	}
	
	public int insertSelective(SysDepartment sysDepartment) {
		return sysDepartmentDao.insertSelective(sysDepartment);
	}
	
	public int batchInsert(List<SysDepartment> list){
		return sysDepartmentDao.batchInsert(list);
	}

	public SysDepartment findById(long id) {
		return sysDepartmentDao.selectByPrimaryKey(id);
	}
	
	public SysDepartment findByQueryContion(SysDepartmentQuery query) {
		List<SysDepartment> list = sysDepartmentDao.selectByExample(query);
		if(null!=list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public List<SysDepartment> queryList(SysDepartmentQuery query) {
		return sysDepartmentDao.selectByExample(query);
	}

	public int deleteById(long id) {
		return sysDepartmentDao.deleteByPrimaryKey(id);
	}

	public int delete(SysDepartmentQuery query) {
		return sysDepartmentDao.deleteByExample(query);
	}

	public SysDepartmentPage queryPageList(SysDepartmentQuery query) {
		SysDepartmentPage sysDepartmentPage = new SysDepartmentPage();
		Integer itemCount = sysDepartmentDao.countByExample(query);
		query.setItemCount(itemCount);

		if (itemCount == 0) {
			sysDepartmentPage.setValues(null);
		} else {
			sysDepartmentPage.setValues(sysDepartmentDao.selectPageByExample(query));
		}

		sysDepartmentPage.setPagenation(new Pagenation(query.getPageNo(), query.getPageSize(), query.getItemCount()));
		return sysDepartmentPage;
	}

}
