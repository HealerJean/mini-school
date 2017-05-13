package com.minixiao.api.recruiter.service.candidate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.minixiao.api.recruiter.dto.candidate.AppStageListDTO;
import com.minixiao.api.recruiter.dto.candidate.ApplicationDTO;
import com.minixiao.api.recruiter.dto.candidate.ApplicationFilterDTO;
import com.minixiao.api.recruiter.dto.candidate.ApplicationListDTO;
import com.minixiao.api.recruiter.dto.candidate.UpdateStatusDTO;
import com.minixiao.api.recruiter.entity.candidate.Applications;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by WangYingjie on 2017/2/13.
 */
public interface ApplicationService {
  String saveApplication(ApplicationDTO applicationDTO) throws JsonProcessingException;

  Applications findById(UUID id);

  String updateApplication(Applications applications,ApplicationDTO applicationDTO )
      throws JsonProcessingException;

  ApplicationDTO getApplicaiton(UUID id) throws IOException;

  ApplicationListDTO listApplication(ApplicationFilterDTO applicationFilterDTO )throws IOException;

  void deleteApplication(UUID id);

  String updateApplicationStatus(AppStageListDTO dto,String status);

  /**
   * @Description 根据职位id查投递该职位的简历数量.
   * @Author  wangyj
   * @CreateDate 2017/3/6 11:13
   * @Param
   * @Return
   */
  Integer countByJobId(UUID recId,UUID jobId);

  String updateApplicationStage(UUID id, String toStage, UUID stgeId);

  void updateAppStatusInit(UUID id, String s);
}
