package com.minixiao.api.recruiter.service.jobrequistion.impl;

import com.minixiao.api.recruiter.dto.jobrequisition.JobRequistionDTO;
import com.minixiao.api.recruiter.dto.jobrequisition.embedmentDTO.ApplyPeriodDTO;
import com.minixiao.api.recruiter.entity.jobrequisition.JobRequistion;
import com.minixiao.api.recruiter.entity.jobrequisition.JobRequistionLog;
import com.minixiao.api.recruiter.entity.jobrequisition.embedment.ApplyPeriod;
import com.minixiao.api.recruiter.entity.jobrequisition.embedment.Department;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.repository.candidate.ApplicationRepository;
import com.minixiao.api.recruiter.repository.jobrequistion.JobRequistionRepository;
import com.minixiao.api.recruiter.service.jobrequistion.JobRequistionService;
import com.minixiao.api.recruiter.service.recruiters.RecruiterInfoService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Description .
 * @Author JiangYh
 * @CreateTime 2017/2/14 17:42
 */
@Service
public class JobRequistionServiceImpl implements JobRequistionService {

  @Autowired
  private JobRequistionRepository jobRepository;

  @Autowired
  private RecruiterInfoService recruiterInfoService;

  @Autowired
  private ApplicationRepository applicationRepository;

  @Autowired
  private JobRequistionLogServiceImpl jobRequistionLogService;

  @Override
  public Page<JobRequistionDTO> findJobRequistion(UUID recId, String status, String jobTitle, String jobCategory, String jobArea, String beginDateFrom, String beginDateTo, String endDateFrom, String endDateTo, String departmentName, Pageable page) {
    RecruiterInfo recruiterInfo = recruiterInfoService.findById(recId);
    Page<JobRequistion> jobRequistionPage = null;
    String[] jobAreas = null;
    if (jobArea != null && !"".equals(jobArea)) {
      jobAreas = jobArea.split(",");
    }
    String[] departmentNames = null;
    if (departmentName != null && !"".equals(departmentName)) {
      departmentNames = departmentName.split(",");
    }
    if ((beginDateFrom != null && !"".equals(beginDateFrom)) && (endDateFrom != null && !"".equals(endDateFrom))) {
      LocalDate beginFrom = LocalDate.parse(beginDateFrom);
      LocalDate beginTo = LocalDate.parse(beginDateTo);
      LocalDate endFrom = LocalDate.parse(endDateFrom);
      LocalDate endTo = LocalDate.parse(endDateTo);
      jobRequistionPage = jobRepository.findListByCondition(recruiterInfo, status, jobTitle, jobCategory, jobAreas, beginFrom, beginTo, endFrom, endTo, departmentNames, page);
    } else if (beginDateFrom == null && (endDateFrom != null && !"".equals(endDateFrom))) {
      LocalDate endFrom = LocalDate.parse(endDateFrom);
      LocalDate endTo = LocalDate.parse(endDateTo);
      jobRequistionPage = jobRepository.findListByConditionForEndDate(recruiterInfo, status, jobTitle, jobCategory, jobAreas, endFrom, endTo, departmentNames, page);
    } else if (endDateFrom == null && (beginDateFrom != null && !"".equals(beginDateFrom))) {
      LocalDate beginFrom = LocalDate.parse(beginDateFrom);
      LocalDate beginTo = LocalDate.parse(beginDateTo);
      jobRequistionPage = jobRepository.findListByConditionForBeginDate(recruiterInfo, status, jobTitle, jobCategory, jobAreas, beginFrom, beginTo, departmentNames, page);
    } else {
      jobRequistionPage = jobRepository.findListByConditionNoDate(recruiterInfo, status, jobTitle, jobCategory, jobAreas, departmentNames, page);
    }
    List<JobRequistion> list = jobRequistionPage.getContent();
    List<JobRequistionDTO> requistionDTOList = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      JobRequistionDTO jobRequistionDTO = new JobRequistionDTO();
      JobRequistion jobRequistion = list.get(i);
      jobRequistionDTO.setId(jobRequistion.getId());
      jobRequistionDTO.setStatus(jobRequistion.getStatus());
      ApplyPeriod applyPeriod = jobRequistion.getApplyPeriod();
      ApplyPeriodDTO applyPeriodDTO = new ApplyPeriodDTO();
      applyPeriodDTO.setApplyBeginDate(dateParse(applyPeriod.getApplyBeginDate()));
      applyPeriodDTO.setApplyEndDate(dateParse(applyPeriod.getApplyEndDate()));
      jobRequistionDTO.setApplyPeriod(applyPeriodDTO);
      jobRequistionDTO.setApplyUrl(jobRequistion.getApplyUrl());
      jobRequistionDTO.setAudit(jobRequistion.getAudit());
      jobRequistionDTO.setCareerLevel(jobRequistion.getCareerLevel());
      jobRequistionDTO.setCreatedOn(jobRequistion.getCreatedOn());
      jobRequistionDTO.setDepartment(jobRequistion.getDepartment());
      jobRequistionDTO.setDescription(jobRequistion.getDescription());
      jobRequistionDTO.setHeadcount(jobRequistion.getHeadcount());
      jobRequistionDTO.setInnerNo(jobRequistion.getInnerNo());
      jobRequistionDTO.setJobArea(jobRequistion.getJobArea());
      jobRequistionDTO.setJobCategory(jobRequistion.getJobCategory());
      jobRequistionDTO.setJobType(jobRequistion.getJobType());
      jobRequistionDTO.setSarlary(jobRequistion.getSarlary());
      jobRequistionDTO.setTitle(jobRequistion.getTitle());
      jobRequistionDTO.setUpdatedOn(jobRequistion.getUpdatedOn());
      Integer applicationNum = applicationRepository.countByJobId(recId, jobRequistion.getId());
      jobRequistionDTO.setApplicationNum(applicationNum);
      requistionDTOList.add(jobRequistionDTO);
    }
    return new PageImpl(requistionDTOList, page, jobRequistionPage.getTotalElements());
  }

  @Override
  public Integer findCountByTitleAndJobArea(RecruiterInfo recruiter, String title, String jobArea) {
    return jobRepository.findCountByTitleAndJobArea(recruiter, title, jobArea);
  }

  @Override
  public JobRequistion saveJobRequistion(JobRequistion job) {
    return jobRepository.save(job);
  }

  /**
   * @param id 职位id
   * @Description
   */
  @Override
  public void online(UUID id) {

  }

  @Override
  public JobRequistion findById(UUID id) {
    return jobRepository.findById(id);
  }

  @Override
  public int findCountByRecruiterInfo(UUID recId) {
    RecruiterInfo recruiterInfo = recruiterInfoService.findById(recId);
    return jobRepository.findCountByRecruiterInfo(recruiterInfo);
  }

  @Override
  public String[] findJobAreaByRecruiterInfo(UUID recId) {
    RecruiterInfo recruiterInfo = recruiterInfoService.findById(recId);
    return jobRepository.findJobAreaByRecruiterInfo(recruiterInfo);
  }

  @Override
  public Page<JobRequistion> findListByConditionForAuditStatus(UUID recId, String auditStatus, String jobTitle, String jobCategory, String jobArea, String beginDateFrom, String beginDateTo, String endDateFrom, String endDateTo, String departmentName, Pageable page) {
    RecruiterInfo recruiterInfo = recruiterInfoService.findById(recId);
    Page<JobRequistion> jobRequistionPage = null;
    String[] jobAreas = null;
    if (jobArea != null && !"".equals(jobArea)) {
      jobAreas = jobArea.split(",");
    }
    String[] departmentNames = null;
    if (departmentName != null && !"".equals(departmentName)) {
      departmentNames = departmentName.split(",");
    }
    if ((beginDateFrom != null && !"".equals(beginDateFrom)) && (endDateFrom != null && !"".equals(endDateFrom))) {
      LocalDate beginFrom = LocalDate.parse(beginDateFrom);
      LocalDate beginTo = LocalDate.parse(beginDateTo);
      LocalDate endFrom = LocalDate.parse(endDateFrom);
      LocalDate endTo = LocalDate.parse(endDateTo);
      jobRequistionPage = jobRepository.findListByConditionForAuditStatus(recruiterInfo, auditStatus, jobTitle, jobCategory, jobAreas, beginFrom, beginTo, endFrom, endTo, departmentNames, page);
    } else if (beginDateFrom == null && (endDateFrom != null && !"".equals(endDateFrom))) {
      LocalDate endFrom = LocalDate.parse(endDateFrom);
      LocalDate endTo = LocalDate.parse(endDateTo);
      jobRequistionPage = jobRepository.findListByConditionForAuditStatusAndEndDate(recruiterInfo, auditStatus, jobTitle, jobCategory, jobAreas,endFrom, endTo, departmentNames, page);
    } else if (endDateFrom == null && (beginDateFrom != null && !"".equals(beginDateFrom))) {
      LocalDate beginFrom = LocalDate.parse(beginDateFrom);
      LocalDate beginTo = LocalDate.parse(beginDateTo);
      jobRequistionPage = jobRepository.findListByConditionForAuditStatusAndBeginDate(recruiterInfo, auditStatus, jobTitle, jobCategory, jobAreas, beginFrom, beginTo, departmentNames, page);
    } else {
      jobRequistionPage = jobRepository.findListByConditionForAuditStatusNoDate(recruiterInfo, auditStatus, jobTitle, jobCategory, jobAreas, departmentNames, page);
    }
    List<JobRequistion> list = jobRequistionPage.getContent();
    List<JobRequistionDTO> requistionDTOList = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      JobRequistionDTO jobRequistionDTO = new JobRequistionDTO();
      JobRequistion jobRequistion = list.get(i);
      jobRequistionDTO.setId(jobRequistion.getId());
      jobRequistionDTO.setStatus(jobRequistion.getStatus());
      ApplyPeriod applyPeriod = jobRequistion.getApplyPeriod();
      ApplyPeriodDTO applyPeriodDTO = new ApplyPeriodDTO();
      applyPeriodDTO.setApplyBeginDate(dateParse(applyPeriod.getApplyBeginDate()));
      applyPeriodDTO.setApplyEndDate(dateParse(applyPeriod.getApplyEndDate()));
      jobRequistionDTO.setApplyPeriod(applyPeriodDTO);
      jobRequistionDTO.setApplyUrl(jobRequistion.getApplyUrl());
      jobRequistionDTO.setAudit(jobRequistion.getAudit());
      jobRequistionDTO.setCareerLevel(jobRequistion.getCareerLevel());
      jobRequistionDTO.setCreatedOn(jobRequistion.getCreatedOn());
      jobRequistionDTO.setDepartment(jobRequistion.getDepartment());
      jobRequistionDTO.setDescription(jobRequistion.getDescription());
      jobRequistionDTO.setHeadcount(jobRequistion.getHeadcount());
      jobRequistionDTO.setInnerNo(jobRequistion.getInnerNo());
      jobRequistionDTO.setJobArea(jobRequistion.getJobArea());
      jobRequistionDTO.setJobCategory(jobRequistion.getJobCategory());
      jobRequistionDTO.setJobType(jobRequistion.getJobType());
      jobRequistionDTO.setSarlary(jobRequistion.getSarlary());
      jobRequistionDTO.setTitle(jobRequistion.getTitle());
      jobRequistionDTO.setUpdatedOn(jobRequistion.getUpdatedOn());
      Integer applicationNum = applicationRepository.countByJobId(recId, jobRequistion.getId());
      jobRequistionDTO.setApplicationNum(applicationNum);
      requistionDTOList.add(jobRequistionDTO);
    }
    return new PageImpl(requistionDTOList, page, jobRequistionPage.getTotalElements());
  }

  private String dateParse(LocalDate date) {
    String dateOld = date.toString();
    String year = dateOld.substring(0, 4);
    String month = dateOld.substring(5, 7);
    String day = dateOld.substring(8, 10);
    return year + month + day;
  }

  /*@Override
  public Map<String, Object> statusCountForList(UUID recId) {
    RecruiterInfo recruiterInfo = recruiterInfoService.findById(recId);
    Map<String, Object> map = new HashedMap();
    map.put("open",jobRepository.findOpenCount(recruiterInfo));
    map.put("on_hold",jobRepository.findOnHoldCount(recruiterInfo));
    map.put("waiting",jobRepository.findWaitingCount(recruiterInfo));
    map.put("notThrough",jobRepository.findNotThroughCount(recruiterInfo));
    return map;
  }*/

  @Override
  public List<String> findTitleByRecruiterInfo(UUID recId) {
    RecruiterInfo recruiterInfo = recruiterInfoService.findById(recId);
    return jobRepository.findTitleByRecruiterInfo(recruiterInfo);
  }

  @Override
  public boolean findByDepartment(UUID departmentId) {
    int count = jobRepository.findByDepartment(departmentId);
    if (count > 0) {
      return true;
    }
    return false;
  }
}
