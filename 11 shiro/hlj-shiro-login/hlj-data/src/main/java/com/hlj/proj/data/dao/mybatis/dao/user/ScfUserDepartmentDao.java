/*
 * Copyright (C) 2019 xiaomi.com, Inc. All Rights Reserved.
 */
package com.hlj.proj.data.dao.mybatis.dao.user;

import com.hlj.proj.data.dao.mybatis.dao.BaseDao;
import com.hlj.proj.data.pojo.user.ScfUserDepartment;
import com.hlj.proj.data.pojo.user.ScfUserDepartmentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author duyang
 * @ClassName: ScfUserDepartmentDao
 * @date 2099/1/1
 * @Description: ScfUserDepartmentDao
 */
@Repository("scfUserDepartmentDao")
public class ScfUserDepartmentDao extends BaseDao {

    public int countByExample(ScfUserDepartmentQuery example) {
        return super.getSqlSession().selectOne("ScfUserDepartmentMapper.countByExample", example);
    }

    public int deleteByExample(ScfUserDepartmentQuery example) {
        return super.getSqlSession().delete("ScfUserDepartmentMapper.deleteByExample", example);
    }

    public int deleteByPrimaryKey(long id) {
        return super.getSqlSession().delete("ScfUserDepartmentMapper.deleteByPrimaryKey", id);
    }

    public int insert(ScfUserDepartment record) {
        return super.getSqlSession().insert("ScfUserDepartmentMapper.insert", record);
    }

    public int insertSelective(ScfUserDepartment record) {
        return super.getSqlSession().insert("ScfUserDepartmentMapper.insertSelective", record);
    }

    public int batchInsert(List<ScfUserDepartment> list) {
        return super.batchInsert("ScfUserDepartmentMapper.insertSelective", list);
    }

    public List<ScfUserDepartment> selectByExample(ScfUserDepartmentQuery example) {
        return super.getSqlSession().selectList("ScfUserDepartmentMapper.selectByExample", example);
    }

    public List<ScfUserDepartment> selectPageByExample(ScfUserDepartmentQuery example) {
        return super.getSqlSession().selectList("ScfUserDepartmentMapper.selectPageByExample", example);
    }

    public ScfUserDepartment selectByPrimaryKey(long id) {
        return super.getSqlSession().selectOne("ScfUserDepartmentMapper.selectByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(ScfUserDepartment record) {
        return super.getSqlSession().update("ScfUserDepartmentMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(ScfUserDepartment record) {
        return super.getSqlSession().update("ScfUserDepartmentMapper.updateByPrimaryKey", record);
    }

}
