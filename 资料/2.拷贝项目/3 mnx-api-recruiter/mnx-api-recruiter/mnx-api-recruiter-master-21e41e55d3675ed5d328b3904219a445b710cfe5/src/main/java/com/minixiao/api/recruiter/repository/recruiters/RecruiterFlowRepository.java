package com.minixiao.api.recruiter.repository.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.RecruiterDept;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterFlow;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * @Description 公司招聘流程repository.
 * @Author xiachao
 * @CreateTime 2017/2/17 14:28
 */

public interface RecruiterFlowRepository extends JpaRepository<RecruiterFlow, UUID> {
  /**
   * 根据id查询该招聘流程的信息 .
   *
   * @param id id
   * @return RecruiterFlow
   */
  RecruiterFlow findById(UUID id);

  /**
   * 根据id查询某一个招聘流程的信息 .
   *
   * @param recruiterInfo recruiterInfo
   * @return Page
   */
  List<RecruiterFlow> findByRecruiterInfoOrderByFlowOrderAsc(RecruiterInfo recruiterInfo);

  /**
   * 根据公司Id查询该公司下的招聘流程
   *
   * @param recruiterInfo recruiterInfo
   * @return list
   */
  List<RecruiterFlow> findByRecruiterInfo(RecruiterInfo recruiterInfo);

  /**
   * 更新某一招聘流程下的申请表数量.
   *
   * @param candidateCount candidateCount
   * @param id             id
   */
  @Modifying
  @Query(value = "update tb_rec_flow set candidate_count = ?1 where id = ?2 ", nativeQuery = true)
  void updateCandidateCount(Integer candidateCount, UUID id);

  /**
   * 根据公司id和招聘流程名称查询数据库表中的记录 .
   *
   * @param recruiterInfo recruiterInfo
   * @param name          name
   * @return list
   */
  List<RecruiterFlow> findByRecruiterInfoAndName(RecruiterInfo recruiterInfo, String name);

  /**
   * 判断当前招聘流程中各阶段是否都有申请表 如任何一个有 返回false .
   *
   * @param recId recId
   * @return boolean
   */
  @Query(value = "select * from tb_rec_flow t1 where t1.rec_id = ?1 and t1.candidate_count != 0 and t1.type != '筛选类型'", nativeQuery = true)
  List<RecruiterFlow> checkCandidateCount(UUID recId);
}
