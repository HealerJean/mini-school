package com.minixiao.api.recruiter.service.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.CandidateFilter;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterUser;

import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/16 15:59
 */

public interface CandidateFilterService {
  /**
   * 保存筛选器.
   *
   * @param candidateFilter c
   */
  void saveCandidateFilter(CandidateFilter candidateFilter);

  /**
   * 查找筛选器.
   *
   * @param id c
   */
  CandidateFilter findById(UUID id);

  /**
   * 删除删选器.
   *
   * @param id c
   */
  void deleteCandidateFilter(UUID id);

  /**
   * 根据公司Id查询该公司的所有筛选器.
   *
   * @param recruiterUser recruiterUser
   */
  List<CandidateFilter> findByRecruiterUser(RecruiterUser recruiterUser);

  /**
   * 删除用户所创建的筛选器
   */
  void deleteCandidateFilterByRecruiterUser(UUID userId);
}
