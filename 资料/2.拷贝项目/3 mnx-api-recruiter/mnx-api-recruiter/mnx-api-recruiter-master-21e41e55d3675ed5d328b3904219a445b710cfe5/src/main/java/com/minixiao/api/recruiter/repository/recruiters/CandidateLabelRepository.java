package com.minixiao.api.recruiter.repository.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.CandidateLabel;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/20 18:49
 */

public interface CandidateLabelRepository extends JpaRepository<CandidateLabel, UUID> {
  /**
   * 根据id查找该标签 .
   *
   * @param id id
   * @return CandidateLabel
   */
  CandidateLabel findById(UUID id);

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
  @Modifying
  @Query(value = "update tb_rec_candidate_label set status = ?1 where id = ?2", nativeQuery = true)
  void updateCandidateLabelStatus(boolean status, UUID id);

  /**
   * 按照创建时间降序输出该公司的标签列表.
   *
   * @param recruiterInfo recruiterInfo
   * @return list
   */
  List<CandidateLabel> findByRecruiterInfoOrderByCreatedOnDesc(RecruiterInfo recruiterInfo);
}
