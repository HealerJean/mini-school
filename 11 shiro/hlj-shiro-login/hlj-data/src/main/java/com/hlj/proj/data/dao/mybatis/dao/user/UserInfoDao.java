/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */package com.hlj.proj.data.dao.mybatis.dao.user;

import com.hlj.proj.data.dao.mybatis.dao.BaseDao;
import com.hlj.proj.data.pojo.user.UserInfo;
import com.hlj.proj.data.pojo.user.UserInfoQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author zhangyujin
 * @ClassName: UserInfoDao
 * @date 2099/1/1
 * @Description: UserInfoDao
 */
@Repository("userInfoDao")
public class UserInfoDao extends BaseDao {

	public int countByExample(UserInfoQuery example) {
		return super.getSqlSession().selectOne("UserInfoMapper.countByExample", example);
	}

	public int deleteByExample(UserInfoQuery example) {
		return super.getSqlSession().delete("UserInfoMapper.deleteByExample", example);
	}

	public int deleteByPrimaryKey(long id) {
		return super.getSqlSession().delete("UserInfoMapper.deleteByPrimaryKey", id);
	}

	public int insert(UserInfo record) {
		return super.getSqlSession().insert("UserInfoMapper.insert", record);
	}

	public int insertSelective(UserInfo record) {
		return super.getSqlSession().insert("UserInfoMapper.insertSelective", record);
	}
	
	public int batchInsert(List<UserInfo> list) {
		return super.batchInsert("UserInfoMapper.insertSelective", list);
	}

	public List<UserInfo> selectByExample(UserInfoQuery example) {
		return super.getSqlSession().selectList("UserInfoMapper.selectByExample", example);
	}

	public List<UserInfo> selectPageByExample(UserInfoQuery example) {
		return super.getSqlSession().selectList("UserInfoMapper.selectPageByExample", example);
	}

	public UserInfo selectByPrimaryKey(long id) {
		return super.getSqlSession().selectOne("UserInfoMapper.selectByPrimaryKey", id);
	}

	public int updateByPrimaryKeySelective(UserInfo record) {
		return super.getSqlSession().update("UserInfoMapper.updateByPrimaryKeySelective", record);
	}

	public int updateByPrimaryKey(UserInfo record) {
		return super.getSqlSession().update("UserInfoMapper.updateByPrimaryKey", record);
	}

}
