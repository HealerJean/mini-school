package com.minixiao.api.recruiter.service.recruiters.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterFlow;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.repository.recruiters.RecruiterFlowRepository;
import com.minixiao.api.recruiter.service.recruiters.RecruiterFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/17 14:29
 */
@Service
@Transactional(readOnly = true)
public class RecruiterFlowServiceImpl implements RecruiterFlowService {
  @Autowired
  private RecruiterFlowRepository recruiterFlowRepository;

  /**
   * 保存公司招聘流程 .
   *
   * @param recruiterFlow recruiterFlow
   */
  @Override
  @Transactional(readOnly = false)
  public void saveRecruiterFlow(RecruiterFlow recruiterFlow) {
    recruiterFlowRepository.save(recruiterFlow);
  }

  /**
   * 根据id查询某一个招聘流程信息 .
   *
   * @param id id
   * @return RecruiterFlow
   */
  @Override
  public RecruiterFlow findById(UUID id) {
    return recruiterFlowRepository.findById(id);
  }

  /**
   * 根据id删除该招聘流程 .
   *
   * @param id id
   */
  @Override
  @Transactional(readOnly = false)
  public void deleteRecruiterFlow(UUID id) {
    recruiterFlowRepository.delete(id);
  }

  /**
   * 根据公司id查询该公司的所有招聘流程 .
   *
   * @param recruiterInfo recruiterInfo
   * @return Page
   */
  @Override
  public List<RecruiterFlow> findByRecruiterInfoOrderByFlowOrderAsc(RecruiterInfo recruiterInfo) {
    return recruiterFlowRepository.findByRecruiterInfoOrderByFlowOrderAsc(recruiterInfo);
  }

  /**
   * 更新某一招聘流程下的申请表数量.
   *
   * @param candidateCount candidateCount
   * @param id             id
   */
  @Override
  @Transactional(readOnly = false)
  @Async
  public void updateCandidateCount(Integer candidateCount, UUID id) {
    recruiterFlowRepository.updateCandidateCount(candidateCount, id);
  }

  /**
   * 根据公司id和招聘流程名称查询数据库表中的记录 .
   *
   * @param recruiterInfo recruiterInfo
   * @param name          name
   * @return list
   */
  @Override
  public List<RecruiterFlow> findByRecruiterInfoAndName(RecruiterInfo recruiterInfo, String name) {
    return recruiterFlowRepository.findByRecruiterInfoAndName(recruiterInfo, name);
  }

  /**
   * 判断当前招聘流程中各阶段是否都有申请表 如任何一个有 返回false .
   *
   * @param recId recId
   * @return boolean
   */
  @Override
  public boolean checkCandidateCount(UUID recId) {
    boolean flag = true;
    List<RecruiterFlow> recruiterFlowList = recruiterFlowRepository.checkCandidateCount(recId);
    if (recruiterFlowList.size() > 0) {
      flag = false;
    }
    return flag;
  }
}
