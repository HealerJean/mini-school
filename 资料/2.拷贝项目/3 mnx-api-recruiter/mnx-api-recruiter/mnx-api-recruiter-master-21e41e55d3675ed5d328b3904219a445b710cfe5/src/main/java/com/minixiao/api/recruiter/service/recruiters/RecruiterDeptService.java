package com.minixiao.api.recruiter.service.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.RecruiterDept;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;

import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/17 11:58
 */

public interface RecruiterDeptService {
  /**
   * 创建公司部门 .
   *
   * @param recruiterDept r
   */
  void saveRecruiterDept(RecruiterDept recruiterDept);

  /**
   * 根据id查询部门信息.
   *
   * @param id id
   * @return RecruiterDept
   */
  RecruiterDept findById(UUID id);

  /**
   * 删除部门.
   *
   * @param id id
   */
  void deleteRecruiterDept(UUID id);

  /**
   * 查询所有部门 .
   *
   * @param recruiterInfo recruiterInfo
   * @return Page
   */
  List<RecruiterDept> findAllByRecruiterInfo(RecruiterInfo recruiterInfo);

  /**
   * 找出当前部门下子部门的个数 .
   *
   * @param recId recId
   * @return Integer
   */
  Long findDeptCount(UUID recId, Long parentId, Long parentId1);

  /**
   * 检查数据库中同级部门名称是否相同
   *
   * @param deptName  deptName
   * @param parentId  parentId
   * @param parentId1 parentId1
   * @return list
   */
  Integer checkDeptName(UUID recId, String deptName, Long parentId, Long parentId1);
}
