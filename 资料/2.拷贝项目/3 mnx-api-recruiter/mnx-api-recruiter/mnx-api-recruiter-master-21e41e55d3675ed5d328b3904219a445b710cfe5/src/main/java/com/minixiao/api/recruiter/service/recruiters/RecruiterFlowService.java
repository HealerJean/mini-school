package com.minixiao.api.recruiter.service.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.RecruiterFlow;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/17 14:29
 */

public interface RecruiterFlowService {
  /**
   * 保存公司招聘流程 .
   *
   * @param recruiterFlow recruiterFlow
   */
  void saveRecruiterFlow(RecruiterFlow recruiterFlow);

  /**
   * 根据id查询某一个招聘流程信息 .
   *
   * @param id id
   * @return RecruiterFlow
   */
  RecruiterFlow findById(UUID id);

  /**
   * 根据id删除该招聘流程 .
   *
   * @param id id
   */
  void deleteRecruiterFlow(UUID id);

  /**
   * 根据公司id查询该公司下的招聘流程 按照flowOrder 排序
   *
   * @param recruiterInfo 公司id
   * @return list
   */
  List<RecruiterFlow> findByRecruiterInfoOrderByFlowOrderAsc(RecruiterInfo recruiterInfo);

  /**
   * 更新某一招聘流程下的申请表数量.
   *
   * @param candidateCount candidateCount
   * @param id             id
   */
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
  boolean checkCandidateCount(UUID recId);
}
