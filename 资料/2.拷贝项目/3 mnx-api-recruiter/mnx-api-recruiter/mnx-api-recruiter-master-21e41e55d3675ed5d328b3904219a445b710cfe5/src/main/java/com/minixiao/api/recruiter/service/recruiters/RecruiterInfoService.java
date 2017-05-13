package com.minixiao.api.recruiter.service.recruiters;

import com.minixiao.api.recruiter.dto.recruiters.RecruiterPictureDto;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/14 17:42
 */

public interface RecruiterInfoService {
  /**
   * 保存实体.
   *
   * @param recruiterInfo r
   */
  void saveRecruiterInfo(RecruiterInfo recruiterInfo);

  /**
   * 根据Id查询该公司信息.
   *
   * @param id id
   * @return RecruiterInfo
   */
  RecruiterInfo findById(UUID id);

  /**
   * 根据Id删除该公司 .
   *
   * @param id id
   */
  void deleteRecruiter(UUID id);

  /**
   * 查询所有公司 .
   *
   * @param pageable p
   * @return page
   */
  Page<RecruiterInfo> findAll(Pageable pageable);

  /**
   * 上传公司logo .
   *
   * @param recId      recId
   * @param pictureDto pictureDto
   */
  String uploadLogo(UUID recId, RecruiterPictureDto pictureDto);
}
