package com.minixiao.api.recruiter.service.recruiters.impl;

import com.minixiao.api.recruiter.entity.recruiters.ExamArrangement;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.repository.recruiters.ExamArrangementRepository;
import com.minixiao.api.recruiter.service.recruiters.ExamArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/17 13:15
 */
@Service
@Transactional(readOnly = true)
public class ExamArrangementServiceImpl implements ExamArrangementService {
  @Autowired
  private ExamArrangementRepository examArrangementRepository;

  /**
   * 创建笔面试安排 .
   *
   * @param examArrangement exam
   */
  @Override
  @Transactional(readOnly = false)
  public void saveExamArrangement(ExamArrangement examArrangement) {
    examArrangementRepository.save(examArrangement);
  }

  /**
   * 根据id查询该笔面试安排信息 .
   *
   * @param id id
   * @return ExamArrangement
   */
  @Override
  public ExamArrangement findById(UUID id) {
    return examArrangementRepository.findById(id);
  }

  /**
   * 删除笔面试安排 .
   *
   * @param id id
   */
  @Override
  @Transactional(readOnly = false)
  public void deleteExamArrangement(UUID id) {
    examArrangementRepository.delete(id);
  }

  /**
   * 根据公司id查询该公司下的所有笔面试安排 .
   *
   * @param recruiterInfo recruiterInfo
   * @param pageable      pageable
   * @return Page
   */
  @Override
  public Page<ExamArrangement> findByRecruiterInfo(RecruiterInfo recruiterInfo, Pageable pageable) {
    return examArrangementRepository.findByRecruiterInfo(recruiterInfo, pageable);
  }
}
