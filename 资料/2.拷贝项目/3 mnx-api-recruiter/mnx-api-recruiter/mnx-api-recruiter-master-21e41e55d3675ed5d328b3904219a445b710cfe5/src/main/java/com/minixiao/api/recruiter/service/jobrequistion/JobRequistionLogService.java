package com.minixiao.api.recruiter.service.jobrequistion;

import com.minixiao.api.recruiter.dto.jobrequisition.JobRequistionDTO;
import com.minixiao.api.recruiter.entity.jobrequisition.JobRequistion;
import com.minixiao.api.recruiter.entity.jobrequisition.JobRequistionLog;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * @Description .
 * @Author JiangYh
 * @CreateTime 2017/2/13 11:08
 */
public interface JobRequistionLogService {

  public Page<JobRequistionLog> findByJobId(UUID jobId, Pageable pageable);

  public UUID saveJobRequistionLog(JobRequistionLog jobRequistionLog);
}
