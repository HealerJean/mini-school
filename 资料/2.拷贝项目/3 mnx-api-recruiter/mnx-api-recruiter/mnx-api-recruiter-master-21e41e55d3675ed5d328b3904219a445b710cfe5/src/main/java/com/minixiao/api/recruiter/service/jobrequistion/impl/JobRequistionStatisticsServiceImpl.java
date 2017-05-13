package com.minixiao.api.recruiter.service.jobrequistion.impl;

import com.minixiao.api.recruiter.dto.jobrequisition.JobStatDTO;
import com.minixiao.api.recruiter.entity.jobrequisition.JobRequistion;
import com.minixiao.api.recruiter.entity.jobrequisition.JobRequistionStatistics;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.repository.jobrequistion.JobRequistionRepository;
import com.minixiao.api.recruiter.repository.jobrequistion.JobRequistionStatisticsRepository;
import com.minixiao.api.recruiter.service.jobrequistion.JobRequistionService;
import com.minixiao.api.recruiter.service.jobrequistion.JobRequistionStatisticsService;
import com.minixiao.api.recruiter.service.recruiters.RecruiterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author JiangYh
 * @CreateTime 2017/2/14 17:42
 */
@Service
@Transactional
public class JobRequistionStatisticsServiceImpl implements JobRequistionStatisticsService {

  @Autowired
  JobRequistionStatisticsRepository repository;
  @Override
  public JobRequistionStatistics findByJobId(UUID jobId) {
    return repository.findByJobId(jobId);
  }

  @Override
  @Async
  public void initRequistionStatistic (UUID jobId, UUID recId) {
    JobRequistionStatistics jobRequistionStatistics = repository.findByJobId(jobId);
    if (jobRequistionStatistics != null) {
      jobRequistionStatistics.setTotalCount(jobRequistionStatistics.getTotalCount()+1);
      jobRequistionStatistics.setScreenCount(jobRequistionStatistics.getScreenCount()+1);
      repository.save(jobRequistionStatistics);
    } else {
      jobRequistionStatistics = new JobRequistionStatistics();
      jobRequistionStatistics.setJobId(jobId);
      jobRequistionStatistics.setRecId(recId);
      jobRequistionStatistics.setInterviewCount(0);
      jobRequistionStatistics.setOfferCount(0);
      jobRequistionStatistics.setTotalCount(1);
      jobRequistionStatistics.setScreenCount(1);
      jobRequistionStatistics.setWrittenCount(0);
      repository.save(jobRequistionStatistics);
    }

  }

  @Override
  @Async
  public void updateRequistionStatistic (JobStatDTO jobStatDTO) {
    UUID jobId = jobStatDTO.getJobId();
    JobRequistionStatistics jobRequistionStatistics = repository.findByJobId(jobId);
    if  (jobRequistionStatistics != null) {
      String fromType = jobStatDTO.getFromType();
      String toType = jobStatDTO.getToType();
      if (fromType.equals("筛选类型")) {
        jobRequistionStatistics.setScreenCount(jobRequistionStatistics.getScreenCount() - 1);
      } else if (fromType.equals("笔试类型")) {
        jobRequistionStatistics.setWrittenCount(jobRequistionStatistics.getWrittenCount() - 1);
      } else if (fromType.equals("面试类型")) {
        jobRequistionStatistics.setInterviewCount(jobRequistionStatistics.getInterviewCount() - 1);
      } else if (fromType.equals("录用类型")) {
        jobRequistionStatistics.setOfferCount(jobRequistionStatistics.getOfferCount() - 1);
      }
      if (toType.equals("筛选类型")) {
        jobRequistionStatistics.setScreenCount(jobRequistionStatistics.getScreenCount() + 1);
      } else if (toType.equals("笔试类型")) {
        jobRequistionStatistics.setWrittenCount(jobRequistionStatistics.getWrittenCount() + 1);
      } else if (toType.equals("面试类型")) {
        jobRequistionStatistics.setInterviewCount(jobRequistionStatistics.getInterviewCount() + 1);
      } else if (toType.equals("录用类型")) {
        jobRequistionStatistics.setOfferCount(jobRequistionStatistics.getOfferCount() + 1);
      }
      repository.save(jobRequistionStatistics);
    }
  }
}