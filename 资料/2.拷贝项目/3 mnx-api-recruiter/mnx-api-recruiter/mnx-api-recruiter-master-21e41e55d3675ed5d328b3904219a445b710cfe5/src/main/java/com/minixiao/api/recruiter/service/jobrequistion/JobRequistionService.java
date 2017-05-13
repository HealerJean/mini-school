package com.minixiao.api.recruiter.service.jobrequistion;

import com.minixiao.api.recruiter.dto.jobrequisition.JobRequistionDTO;
import com.minixiao.api.recruiter.entity.jobrequisition.JobRequistion;
import com.minixiao.api.recruiter.entity.jobrequisition.embedment.Department;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Description .
 * @Author JiangYh
 * @CreateTime 2017/2/13 11:08
 */
public interface JobRequistionService {

  /**
   * @Description
   * @param recId 公司id
   * @param status 职位状态
   * @param jobTitle 职位名称
   * @param jobCategory 职位类别
   * @param jobArea 工作地区
   * @param beginDateFrom  网申开始时间区间起点
   * @param beginDateTo 网申开始时间区间终点
   * @param endDateFrom 网申结束时间区间起点
   * @param endDateTo 网申结束时间区间终点
   * @param page
   */
  public Page<JobRequistionDTO> findJobRequistion(UUID recId, String status, String jobTitle, String jobCategory, String jobArea, String beginDateFrom, String beginDateTo, String endDateFrom, String endDateTo, String departmentName,Pageable page);

  /**
   * @Description
   * @param id 职位需求id
   */
  public JobRequistion findById(UUID id);

  /**
   * @Description
   * @param job 职位需求对象
   */
  public JobRequistion saveJobRequistion(JobRequistion job);

  /**
   * @Description
   * @param id 职位id
   */
  public void online(UUID id);

  /**
   * @Description
   * @param recruiter 企业
   * @param title 职位名称
   * @param jobArea 工作地区
   */
  public Integer findCountByTitleAndJobArea(RecruiterInfo recruiter, String title, String jobArea);

  public int findCountByRecruiterInfo(UUID recId);

  public String[] findJobAreaByRecruiterInfo(UUID recId);

  public Page<JobRequistion> findListByConditionForAuditStatus(UUID recId, String auditStatus, String jobTitle, String jobCategory, String jobArea, String beginDateFrom, String beginDateTo, String endDateFrom, String endDateTo, String departmentName, Pageable page);

  //public Map<String, Object> statusCountForList(UUID recId);

  public List<String> findTitleByRecruiterInfo(UUID recId);

  public boolean findByDepartment(UUID departmentId);


}
