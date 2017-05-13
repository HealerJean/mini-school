package com.minixiao.api.recruiter.service.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterPicture;

import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/23 11:37
 */

public interface RecruiterPictureService {
  /**
   * 上传公司图片 .
   *
   * @param recruiterPicture recruiterPicture
   */
  void saveRecruiterPicture(RecruiterPicture recruiterPicture);

  /**
   * 删除公司图片 .
   *
   * @param id 图片id
   */
  void deleteRecruiterPicture(UUID id);

  /**
   * 根据id查找改图片 .
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
  List<RecruiterPicture> findByRecruiterInfo(RecruiterInfo recruiterInfo);
}
