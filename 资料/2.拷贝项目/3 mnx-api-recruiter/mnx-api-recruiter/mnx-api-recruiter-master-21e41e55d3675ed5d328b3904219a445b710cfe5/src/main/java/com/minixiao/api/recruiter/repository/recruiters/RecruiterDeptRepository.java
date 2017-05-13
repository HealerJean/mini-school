package com.minixiao.api.recruiter.repository.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.RecruiterDept;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/17 11:58
 */

public interface RecruiterDeptRepository extends JpaRepository<RecruiterDept, UUID> {
  /**
   * 根据id查询部门信息.
   *
   * @param id id
   * @return RecruiterDept
   */
  RecruiterDept findById(UUID id);

  /**
   * 根据公司id查询该公司下所有部门 .
   *
   * @param recruiterInfo recruiterInfo
   * @return Page
   */
  List<RecruiterDept> findAllByRecruiterInfo(RecruiterInfo recruiterInfo);

  /**
   * 查询一条部门记录 .
   *
   * @param recruiterInfo recruiterInfo
   * @return RecruiterDept
   */
  RecruiterDept findByRecruiterInfo(RecruiterInfo recruiterInfo);

  /**
   * 找出当前部门下子部门的个数 .
   *
   * @param recId recId
   * @return Integer
   */
  @Query(value = "select count(*) from tb_rec_dept where rec_id = ?1 and cast(dept_id as int8) BETWEEN ?2*100 "
      +
      "and ?3*100+99",
      nativeQuery = true)
  Long findDeptCount(UUID recId, Long parentId, Long parentId1);

  /**
   * 检查数据库中同级部门名称是否相同
   *
   * @param deptName  deptName
   * @param parentId  parentId
   * @param parentId1 parentId1
   * @return list
   */
  @Query(value = "select count(*) from tb_rec_dept where rec_id = ?1 and name = ?2 and cast(dept_id as int8) BETWEEN ?3*100 and ?4*100+99 ", nativeQuery = true)
  Integer checkDeptName(UUID recId, String deptName, Long parentId, Long parentId1);
}
