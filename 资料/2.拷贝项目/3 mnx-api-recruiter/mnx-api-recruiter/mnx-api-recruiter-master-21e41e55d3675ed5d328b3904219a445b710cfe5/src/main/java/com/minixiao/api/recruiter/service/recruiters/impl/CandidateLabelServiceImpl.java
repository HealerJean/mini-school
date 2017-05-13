package com.minixiao.api.recruiter.service.recruiters.impl;

import com.minixiao.api.recruiter.entity.recruiters.CandidateLabel;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.repository.recruiters.CandidateLabelRepository;
import com.minixiao.api.recruiter.service.recruiters.CandidateLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/20 18:50
 */
@Service
@Transactional(readOnly = true)
public class CandidateLabelServiceImpl implements CandidateLabelService {

  @Autowired
  private CandidateLabelRepository candidateLabelRepository;

  /**
   * 保存标签到数据库.
   *
   * @param candidateLabel candidateLabel
   */
  @Override
  @Transactional(readOnly = false)
  public void saveCandidateLabel(CandidateLabel candidateLabel) {
    candidateLabelRepository.save(candidateLabel);
  }

  /**
   * 根据id查找该标签 .
   *
   * @param id id
   * @return CandidateLabel
   */
  @Override
  public CandidateLabel findById(UUID id) {
    return candidateLabelRepository.findById(id);
  }

  /**
   * 根据id删除该标签 .
   *
   * @param id id
   */
  @Override
  @Transactional(readOnly = false)
  public void deleteCandidateLabel(UUID id) {
    candidateLabelRepository.delete(id);
  }

  /**
   * 根据公司id查找该公司下的所有标签 .
   *
   * @param recruiterInfo recruiterInfo
   * @return Page
   */
  @Override
  public List<CandidateLabel> findByRecruiterInfo(RecruiterInfo recruiterInfo) {
    return candidateLabelRepository.findByRecruiterInfo(recruiterInfo);
  }

  /**
   * 修改标签状态 .
   *
   * @param status status
   * @param id     id
   */
  @Override
  @Transactional(readOnly = false)
  public void updateCandidateLabelStatus(boolean status, UUID id) {
    candidateLabelRepository.updateCandidateLabelStatus(status, id);
  }

  /**
   * 按照创建时间降序输出该公司的标签列表.
   *
   * @param recruiterInfo recruiterInfo
   * @return list
   */
  @Override
  public List<CandidateLabel> findByRecruiterInfoOrderByCreatedOnDesc(RecruiterInfo recruiterInfo) {
    return candidateLabelRepository.findByRecruiterInfoOrderByCreatedOnDesc(recruiterInfo);
  }
}
