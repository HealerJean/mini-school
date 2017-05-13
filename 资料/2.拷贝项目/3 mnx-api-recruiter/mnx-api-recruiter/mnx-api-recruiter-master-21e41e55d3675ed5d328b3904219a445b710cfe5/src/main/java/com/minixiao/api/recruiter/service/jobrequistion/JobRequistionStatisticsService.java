package com.minixiao.api.recruiter.service.jobrequistion;

import com.minixiao.api.recruiter.dto.jobrequisition.JobStatDTO;
import com.minixiao.api.recruiter.entity.jobrequisition.JobRequistion;
import com.minixiao.api.recruiter.entity.jobrequisition.JobRequistionStatistics;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author JiangYh
 * @CreateTime 2017/2/13 11:08
 */
public interface JobRequistionStatisticsService {

  public JobRequistionStatistics findByJobId(UUID jobId);
  public void initRequistionStatistic (UUID jobId, UUID recId);
  public void updateRequistionStatistic (JobStatDTO jobStatDTO);
}
