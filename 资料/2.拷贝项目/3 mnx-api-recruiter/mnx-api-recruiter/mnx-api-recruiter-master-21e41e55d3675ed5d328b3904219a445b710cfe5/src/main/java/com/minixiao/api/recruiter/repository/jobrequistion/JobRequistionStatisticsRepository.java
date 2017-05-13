package com.minixiao.api.recruiter.repository.jobrequistion;

import com.minixiao.api.recruiter.entity.jobrequisition.JobRequistion;
import com.minixiao.api.recruiter.entity.jobrequisition.JobRequistionStatistics;
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
public interface JobRequistionStatisticsRepository extends JpaRepository<JobRequistionStatistics, UUID> {

  /**
   *
   * @param jobId
   * @return
   */

  public JobRequistionStatistics findByJobId(UUID jobId);

}
