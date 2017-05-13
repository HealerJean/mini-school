package com.minixiao.api.recruiter.service.recruiters.impl;

import com.minixiao.api.recruiter.entity.recruiters.RecruiterDept;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.repository.recruiters.RecruiterDeptRepository;
import com.minixiao.api.recruiter.service.recruiters.RecruiterDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/17 11:58
 */
@Service
@Transactional(readOnly = true)
public class RecruiterDeptServiceImpl implements RecruiterDeptService {

  @Autowired
  private RecruiterDeptRepository recruiterDeptRepository;

  /**
   * 创建公司部门 .
   *
   * @param recruiterDept r
   */
  @Override
  @Transactional(readOnly = false)
  public void saveRecruiterDept(RecruiterDept recruiterDept) {
    recruiterDeptRepository.save(recruiterDept);
  }

  /**
   * 根据id查询部门信息.
   *
   * @param id id
   * @return RecruiterDept
   */
  @Override
  public RecruiterDept findById(UUID id) {
    return recruiterDeptRepository.findById(id);
  }

  /**
   * 删除部门.
   *
   * @param id id
   */
  @Override
  @Transactional(readOnly = false)
  public void deleteRecruiterDept(UUID id) {
    recruiterDeptRepository.delete(id);
  }

  /**
   * 查询所有部门 .
   *
   * @param recruiterInfo recruiterInfo
   * @return Page
   */
  @Override
  public List<RecruiterDept> findAllByRecruiterInfo(RecruiterInfo recruiterInfo) {
    return recruiterDeptRepository.findAllByRecruiterInfo(recruiterInfo);
  }

  /**
   * 找出当前部门下子部门的个数 .
   *
   * @param recId recId
   * @return Integer
   */
  @Override
  public Long findDeptCount(UUID recId, Long parentId, Long parentId1) {
    return recruiterDeptRepository.findDeptCount(recId, parentId, parentId1);
  }

  /**
   * 检查数据库中同级部门名称是否相同
   *
   * @param deptName  deptName
   * @param parentId  parentId
   * @param parentId1 parentId1
   * @return list
   */
  @Override
  public Integer checkDeptName(UUID recId, String deptName, Long parentId, Long parentId1) {
    return recruiterDeptRepository.checkDeptName(recId, deptName, parentId, parentId1);
  }
}
