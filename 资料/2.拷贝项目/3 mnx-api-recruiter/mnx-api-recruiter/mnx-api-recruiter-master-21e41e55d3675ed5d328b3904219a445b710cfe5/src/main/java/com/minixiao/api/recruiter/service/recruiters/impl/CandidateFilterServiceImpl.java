package com.minixiao.api.recruiter.service.recruiters.impl;

import com.minixiao.api.recruiter.entity.recruiters.CandidateFilter;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterUser;
import com.minixiao.api.recruiter.repository.recruiters.CandidateFilterRepository;
import com.minixiao.api.recruiter.service.recruiters.CandidateFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/16 16:00
 */
@Service
@Transactional(readOnly = true)
public class CandidateFilterServiceImpl implements CandidateFilterService {

  @Autowired
  private CandidateFilterRepository candidateFilterRepository;

  @Override
  @Transactional(readOnly = false)
  public void saveCandidateFilter(CandidateFilter candidateFilter) {
    candidateFilterRepository.save(candidateFilter);
  }

  @Override
  public CandidateFilter findById(UUID id) {
    return candidateFilterRepository.findById(id);
  }

  @Override
  @Transactional(readOnly = false)
  public void deleteCandidateFilter(UUID id) {
    candidateFilterRepository.delete(id);
  }

  @Override
  public List<CandidateFilter> findByRecruiterUser(RecruiterUser recruiterUser) {
    return candidateFilterRepository.findByRecruiterUserOrderByCreatedOnDesc(recruiterUser);
  }

  /**
   * 删除用户所创建的筛选器
   */
  @Override
  @Transactional(readOnly = false)
  public void deleteCandidateFilterByRecruiterUser(UUID userId) {
    candidateFilterRepository.deleteCandidateFilterByRecruiterUser(userId);
  }
}
