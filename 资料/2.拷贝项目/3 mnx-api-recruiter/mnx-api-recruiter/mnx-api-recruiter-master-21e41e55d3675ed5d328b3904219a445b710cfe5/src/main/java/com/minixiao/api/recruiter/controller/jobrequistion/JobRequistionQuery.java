package com.minixiao.api.recruiter.controller.jobrequistion;

import com.minixiao.api.recruiter.dto.jobrequisition.JobRequistionDTO;
import com.minixiao.api.recruiter.entity.jobrequisition.JobRequistion;
import com.minixiao.api.recruiter.service.jobrequistion.JobRequistionService;
import mnx.infra.apiauth.server.domain.AuthUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Description .
 * @Author JiangYh
 * @CreateTime 2017/2/13 11:09
 */
@RestController
@RequestMapping(value="/requisitions")
@Transactional(readOnly = true)
public class JobRequistionQuery {

  @Autowired
  private JobRequistionService jobRequistionService;

  /**
   * 列出所有职位需求.
   *
   * @param status  职位状态
   * @param jobTitle 职位名称
   * @param jobCategory 职位类别
   * @param jobArea 工作地区
   * @param beginDateFrom 网申开始时间区间起点
   * @param beginDateTo 网申开始时间区间终点
   * @param endDateFrom 网申结束时间区间起点
   * @param endDateTo 网申结束时间区间起点
   * @param page 关于分页、排序的参数
   * @return ResponseEntity.
   */
  @RequiresRoles("COMPANY")
  @GetMapping
  public ResponseEntity<Page<JobRequistionDTO>> list(String status, String jobTitle, String jobCategory, String jobArea, String beginDateFrom, String beginDateTo, String endDateFrom, String endDateTo, String departmentName, Pageable page) {
    Subject currentUser = SecurityUtils.getSubject();
    if (currentUser.isAuthenticated()) {
      AuthUser authUser = (AuthUser) currentUser.getPrincipal();
      //获取用户所属机构的ID
      UUID recId = authUser.getOid();
      Page<JobRequistionDTO> result = jobRequistionService.findJobRequistion(recId, status, jobTitle, jobCategory, jobArea, beginDateFrom, beginDateTo, endDateFrom, endDateTo, departmentName, page);
      return ResponseEntity.ok(result);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * 获取某职位需求详情.
   * @return ResponseEntity<JobPosting>.
   */
  @RequiresRoles("COMPANY")
  @GetMapping(value = "/{id}")
  public ResponseEntity<JobRequistion> detail(@PathVariable UUID id) {
    Subject currentUser = SecurityUtils.getSubject();
    if (currentUser.isAuthenticated()) {
      if (id != null) {
        JobRequistion job = jobRequistionService.findById(id);
        return ResponseEntity.ok(job);
      }
    }
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .cacheControl(CacheControl.noCache())
          .body(null);
  }

  /**
   * 获取某职位需求详情.
   * @return ResponseEntity<JobPosting>.
   */
 @RequiresRoles("COMPANY")
  @GetMapping(value = "/count")
  public ResponseEntity<Integer> jobCount() {
    Subject currentUser = SecurityUtils.getSubject();
    if (currentUser.isAuthenticated()) {
      AuthUser authUser = (AuthUser)currentUser.getPrincipal();
      UUID recId = authUser.getOid();
        int jobCount = jobRequistionService.findCountByRecruiterInfo(recId);
        return ResponseEntity.ok(jobCount);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * 列出所有职位需求.
   *
   * @param auditStatus  审核状态
   * @param jobTitle 职位名称
   * @param jobCategory 职位类别
   * @param jobArea 工作地区
   * @param beginDateFrom 网申开始时间区间起点
   * @param beginDateTo 网申开始时间区间终点
   * @param endDateFrom 网申结束时间区间起点
   * @param endDateTo 网申结束时间区间起点
   * @param page 关于分页、排序的参数
   * @return ResponseEntity.
   */
  @RequiresRoles("COMPANY")
  @GetMapping("/listForAudit")
  public ResponseEntity<Page<JobRequistion>> listForAudit(String auditStatus, String jobTitle, String jobCategory, String jobArea, String beginDateFrom, String beginDateTo, String endDateFrom, String endDateTo, String departmentName, Pageable page) {
    Subject currentUser = SecurityUtils.getSubject();
    if (currentUser.isAuthenticated()) {
      AuthUser authUser = (AuthUser) currentUser.getPrincipal();
      //获取用户所属机构的ID
      UUID recId = authUser.getOid();
      Page<JobRequistion> result = jobRequistionService.findListByConditionForAuditStatus(recId, auditStatus, jobTitle, jobCategory, jobArea, beginDateFrom, beginDateTo, endDateFrom, endDateTo, departmentName, page);
      return ResponseEntity.ok(result);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * 获取某职位需求详情.
   * @return ResponseEntity<JobPosting>.
   */
  @RequiresRoles("COMPANY")
  @GetMapping(value = "/jobArea")
  public ResponseEntity<String[]> jobArea() {
    Subject currentUser = SecurityUtils.getSubject();
    if (currentUser.isAuthenticated()) {
      AuthUser authUser = (AuthUser) currentUser.getPrincipal();
      UUID recId = authUser.getOid();
      String[] jobCount = jobRequistionService.findJobAreaByRecruiterInfo(recId);
      return ResponseEntity.ok(jobCount);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * 获取某职位需求详情.
   * @return ResponseEntity<JobPosting>.
   *//*
  @RequiresRoles("COMPANY")
  @GetMapping(value = "/statusCount")
  public ResponseEntity<Map<String, Object>> jobStatusCount() {
    Subject currentUser = SecurityUtils.getSubject();
    if (currentUser.isAuthenticated()) {
      AuthUser authUser = (AuthUser) currentUser.getPrincipal();
      UUID recId = authUser.getOid();
      Map<String, Object> jobCount = jobRequistionService.statusCountForList(recId);
      return ResponseEntity.ok(jobCount);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }*/

  @RequiresRoles("COMPANY")
  @GetMapping(value = "/jobTitle")
  public ResponseEntity<List> jobTitle() {
    Subject currentUser = SecurityUtils.getSubject();
    if (currentUser.isAuthenticated()) {
      AuthUser authUser = (AuthUser) currentUser.getPrincipal();
      UUID recId = authUser.getOid();
      List jobTitles = jobRequistionService.findTitleByRecruiterInfo(recId);
      return ResponseEntity.ok(jobTitles);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

}
