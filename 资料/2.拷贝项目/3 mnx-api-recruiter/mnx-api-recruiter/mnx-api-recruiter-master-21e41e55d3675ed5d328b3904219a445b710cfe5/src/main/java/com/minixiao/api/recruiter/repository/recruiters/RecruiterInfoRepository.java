package com.minixiao.api.recruiter.repository.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/14 17:44
 */

public interface RecruiterInfoRepository extends CrudRepository<RecruiterInfo, UUID> {
  /**
   * 根据id查找企业.
   *
   * @param id id
   */
  RecruiterInfo findById(UUID id);

  /**
   * 查询所有.
   *
   * @param pageable p
   * @return Page
   */
  Page<RecruiterInfo> findAll(Pageable pageable);

}
