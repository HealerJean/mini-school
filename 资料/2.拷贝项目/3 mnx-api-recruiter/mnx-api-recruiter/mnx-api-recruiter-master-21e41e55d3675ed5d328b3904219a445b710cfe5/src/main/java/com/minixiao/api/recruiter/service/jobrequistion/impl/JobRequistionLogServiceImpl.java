package com.minixiao.api.recruiter.service.jobrequistion.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minixiao.api.recruiter.dto.candidate.ScheduleDTO;
import com.minixiao.api.recruiter.dto.jobrequisition.JobRequistionDTO;
import com.minixiao.api.recruiter.entity.candidate.CandidateInfo;
import com.minixiao.api.recruiter.entity.candidate.Schedule;
import com.minixiao.api.recruiter.entity.jobrequisition.JobRequistion;
import com.minixiao.api.recruiter.entity.jobrequisition.JobRequistionLog;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.repository.candidate.ApplicationRepository;
import com.minixiao.api.recruiter.repository.jobrequistion.JobRequistionLogRepository;
import com.minixiao.api.recruiter.repository.jobrequistion.JobRequistionRepository;
import com.minixiao.api.recruiter.service.jobrequistion.JobRequistionLogService;
import com.minixiao.api.recruiter.service.jobrequistion.JobRequistionService;
import com.minixiao.api.recruiter.service.recruiters.RecruiterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author JiangYh
 * @CreateTime 2017/2/14 17:42
 */
@Service
public class JobRequistionLogServiceImpl implements JobRequistionLogService {

  @Autowired
  private JobRequistionLogRepository repository;
  /**
   * 通过公司Id查询任务安排列表.
   *
   * @param jobId    公司Id
   * @param pageable 分页参数
   * @return Page
   */
  @Override
  public Page<JobRequistionLog> findByJobId(UUID jobId, Pageable pageable) {
      Page<JobRequistionLog> jobRequistionLogPage = repository.findByJobId(jobId, pageable);
      return jobRequistionLogPage;
  }

  @Override
  public UUID saveJobRequistionLog(JobRequistionLog jobRequistionLog){
    jobRequistionLog = repository.save(jobRequistionLog);
    return jobRequistionLog.getId();
  }

}
