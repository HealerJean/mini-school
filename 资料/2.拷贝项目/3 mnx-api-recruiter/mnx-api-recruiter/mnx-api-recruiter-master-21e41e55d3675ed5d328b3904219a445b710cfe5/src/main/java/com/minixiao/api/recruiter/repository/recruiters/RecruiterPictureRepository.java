package com.minixiao.api.recruiter.repository.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterPicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/23 11:37
 */

public interface RecruiterPictureRepository extends JpaRepository<RecruiterPicture, UUID> {
  /**
   * 根据id查找该图片 .
   *
   * @param id id
   * @return RecruiterPicture
   */
  RecruiterPicture findById(UUID id);

  /**
   * 查询该公司下所有图片 .
   *
   * @param recruiterInfo recruiterInfo
   * @return list
   */
  List<RecruiterPicture> findByRecruiterInfoOrderByPictureOrder(RecruiterInfo recruiterInfo);
}
