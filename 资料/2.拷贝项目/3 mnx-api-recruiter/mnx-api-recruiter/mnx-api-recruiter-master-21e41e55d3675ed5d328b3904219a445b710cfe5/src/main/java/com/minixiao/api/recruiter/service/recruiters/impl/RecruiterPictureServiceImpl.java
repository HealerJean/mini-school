package com.minixiao.api.recruiter.service.recruiters.impl;

import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterPicture;
import com.minixiao.api.recruiter.repository.recruiters.RecruiterPictureRepository;
import com.minixiao.api.recruiter.service.recruiters.RecruiterPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/23 11:38
 */
@Service
@Transactional(readOnly = true)
public class RecruiterPictureServiceImpl implements RecruiterPictureService {

  @Autowired
  private RecruiterPictureRepository recruiterPictureRepository;

  /**
   * 上传公司图片 .
   *
   * @param recruiterPicture recruiterPicture
   */
  @Override
  @Transactional(readOnly = false)
  public void saveRecruiterPicture(RecruiterPicture recruiterPicture) {
    recruiterPictureRepository.save(recruiterPicture);
  }

  /**
   * 删除公司图片 .
   *
   * @param id 图片id
   */
  @Override
  @Transactional(readOnly = false)
  public void deleteRecruiterPicture(UUID id) {
    recruiterPictureRepository.delete(id);
  }

  /**
   * 根据id查找改图片 .
   *
   * @param id id
   * @return RecruiterPicture
   */
  @Override
  public RecruiterPicture findById(UUID id) {
    return recruiterPictureRepository.findById(id);
  }

  /**
   * 查询该公司下所有图片 .
   *
   * @param recruiterInfo recruiterInfo
   * @return list
   */
  @Override
  public List<RecruiterPicture> findByRecruiterInfo(RecruiterInfo recruiterInfo) {
    return recruiterPictureRepository.findByRecruiterInfoOrderByPictureOrder(recruiterInfo);
  }
}
