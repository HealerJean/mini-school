package com.minixiao.api.recruiter.repository.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.CandidateFilter;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/16 15:58
 */

public interface CandidateFilterRepository extends JpaRepository<CandidateFilter, UUID> {
  /**
   * 根据id查询该筛选器详细信息.
   *
   * @param id id
   * @return c
   */
  CandidateFilter findById(UUID id);

  /**
   * @Description 根据公司ID 查询该公司下的所有筛选器.
   * @Author xiachao
   * @CreateTime 2017/2/17 10:07
   * @Param
   * @Return
   */
  List<CandidateFilter> findByRecruiterUser(RecruiterUser recruiterUser);

  /**
   * 删除该用户的所有筛选器 .
   *
   * @param userId userId
   */
  @Modifying
  @Query(value = "delete from tb_rec_filter_candidate where user_id = ?1", nativeQuery = true)
  void deleteCandidateFilterByRecruiterUser(UUID userId);

  /**
   * 根据公司ID 查询该公司下的所有筛选器 按照创建时间降序排列.
   *
   * @param recruiterUser recruiterUser
   * @return list
   */
  List<CandidateFilter> findByRecruiterUserOrderByCreatedOnDesc(RecruiterUser recruiterUser);
}
