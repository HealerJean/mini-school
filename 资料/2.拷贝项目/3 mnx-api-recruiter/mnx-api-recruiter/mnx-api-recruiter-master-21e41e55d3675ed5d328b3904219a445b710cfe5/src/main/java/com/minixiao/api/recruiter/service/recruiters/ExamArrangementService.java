package com.minixiao.api.recruiter.service.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.ExamArrangement;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/17 13:14
 */

public interface ExamArrangementService {
  /**
   * 创建笔面试安排 .
   *
   * @param examArrangement exam
   */
  void saveExamArrangement(ExamArrangement examArrangement);

  /**
   * 根据id查询该笔面试安排信息 .
   *
   * @param id id
   * @return ExamArrangement
   */
  ExamArrangement findById(UUID id);

  /**
   * 删除笔面试安排 .
   *
   * @param id id
   */
  void deleteExamArrangement(UUID id);

  /**
   * 根据公司id查询该公司下的所有笔面试安排 .
   *
   * @param recruiterInfo recruiterInfo
   * @param pageable      pageable
   * @return Page
   */
  Page<ExamArrangement> findByRecruiterInfo(RecruiterInfo recruiterInfo, Pageable pageable);
}
