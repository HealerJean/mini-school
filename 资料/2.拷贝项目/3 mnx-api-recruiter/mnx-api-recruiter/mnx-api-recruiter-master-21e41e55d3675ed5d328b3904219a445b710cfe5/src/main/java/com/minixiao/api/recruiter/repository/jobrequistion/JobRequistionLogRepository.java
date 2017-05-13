package com.minixiao.api.recruiter.repository.jobrequistion;

import com.minixiao.api.recruiter.entity.jobrequisition.JobRequistion;
import com.minixiao.api.recruiter.entity.jobrequisition.JobRequistionLog;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @Description .
 * @Author JiangYh
 * @CreateTime 2017/2/14 17:44
 */
public interface JobRequistionLogRepository extends JpaRepository<JobRequistionLog, UUID> {

  /**
   * 根据JobId查找职位处理历史.
   *
   * @param jobId jobId
   */
  public Page<JobRequistionLog> findByJobId(UUID jobId, Pageable pageable);

}
