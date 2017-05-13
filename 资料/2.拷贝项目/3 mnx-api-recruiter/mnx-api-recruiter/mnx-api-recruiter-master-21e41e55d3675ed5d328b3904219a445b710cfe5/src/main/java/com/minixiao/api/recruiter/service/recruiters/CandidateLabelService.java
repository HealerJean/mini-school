package com.minixiao.api.recruiter.service.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.CandidateLabel;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/20 18:50
 */

public interface CandidateLabelService {
  /**
   * 保存标签到数据库.
   *
   * @param candidateLabel candidateLabel
   */
  void saveCandidateLabel(CandidateLabel candidateLabel);

  /**
   * 根据id查找该标签 .
   *
   * @param id id
   * @return CandidateLabel
   */
  CandidateLabel findById(UUID id);

  /**
   * 根据id删除该标签 .
   *
   * @param id id
   */
  void deleteCandidateLabel(UUID id);

  /**
   * 根据公司id查找该公司下的所有标签 .
   *
   * @param recruiterInfo recruiterInfo
   * @return Page
   */
  List<CandidateLabel> findByRecruiterInfo(RecruiterInfo recruiterInfo);

  /**
   * 修改标签状态 .
   *
   * @param status status
   * @param id     id
   */
  void updateCandidateLabelStatus(boolean status, UUID id);

  /**
   * 按照创建时间降序输出该公司的标签列表.
   *
   * @param recruiterInfo recruiterInfo
   * @return list
   */
  List<CandidateLabel> findByRecruiterInfoOrderByCreatedOnDesc(RecruiterInfo recruiterInfo);
}
