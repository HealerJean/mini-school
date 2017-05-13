package com.minixiao.api.recruiter.repository.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.ExamArrangement;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/17 13:14
 */

public interface ExamArrangementRepository extends JpaRepository<ExamArrangement,UUID> {
  /**
   * 根据id查询该笔面试安排信息 .
   * @param id id
   * @return ExamArrangement
   */
  ExamArrangement findById(UUID id);

  /**
   * 根据公司id查询该公司下的所有笔面试安排 .
   * @param recruiterInfo recruiterInfo
   * @param pageable pageable
   * @return Page
   */
  Page<ExamArrangement> findByRecruiterInfo(RecruiterInfo recruiterInfo, Pageable pageable);
}
