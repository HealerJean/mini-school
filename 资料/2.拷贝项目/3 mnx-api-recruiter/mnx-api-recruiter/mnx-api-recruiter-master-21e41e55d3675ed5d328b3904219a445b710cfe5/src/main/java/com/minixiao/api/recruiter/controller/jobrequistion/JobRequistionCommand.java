package com.minixiao.api.recruiter.controller.jobrequistion;

import com.minixiao.api.recruiter.dto.jobrequisition.JobRequistionDTO;
import com.minixiao.api.recruiter.dto.jobrequisition.embedmentDTO.ApplyPeriodDTO;
import com.minixiao.api.recruiter.entity.jobrequisition.JobRequistion;
import com.minixiao.api.recruiter.entity.jobrequisition.JobRequistionLog;
import com.minixiao.api.recruiter.entity.jobrequisition.embedment.ApplyPeriod;
import com.minixiao.api.recruiter.entity.jobrequisition.embedment.Audit;
import com.minixiao.api.recruiter.entity.jobrequisition.embedment.Sarlary;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.service.jobrequistion.JobRequistionLogService;
import com.minixiao.api.recruiter.service.jobrequistion.JobRequistionService;
import com.minixiao.api.recruiter.service.recruiters.RecruiterInfoService;
import mnx.infra.apiauth.server.domain.AuthUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author JiangYh
 * @CreateTime 2017/2/13 11:09
 */
@RestController
@RequestMapping(value = "/requisitions")
@Transactional
public class JobRequistionCommand {

  @Autowired
  private JobRequistionService jobService;

  @Autowired
  private RecruiterInfoService recruiterInfoService;

  @Autowired
  private JobRequistionLogService jobRequistionLogService;

  /**
   * @Description 提交一条职位需求
   * @Author JiangYh
   * @CreateTime 2017/2/14 18:09
   * @Param
   * @Return
   */
  @PostMapping
  @RequiresRoles("COMPANY")
  public ResponseEntity requisitions(@RequestBody JobRequistionDTO jobDTO) {
    Subject currentUser = SecurityUtils.getSubject();
    if (currentUser.isAuthenticated()) {
      AuthUser authUser = (AuthUser) currentUser.getPrincipal();
      UUID userId = authUser.getUid();
      UUID id = authUser.getOid();
      //获取用户的名称（昵称）
      String disPlayName = authUser.getDnm();
      RecruiterInfo recruiterInfo = recruiterInfoService.findById(id);
      if (jobDTO != null) {
        if (recruiterInfo != null) {
          String jobArea = jobDTO.getJobArea();
          String[] jobAreas = jobArea.split(",");
          for (int i = 0; i < jobAreas.length; i++) {
            String title = jobDTO.getTitle();
            String area = jobAreas[i];
            Integer count = jobService.findCountByTitleAndJobArea(recruiterInfo, title, jobArea);
            if (count != null && count >= 1) {
              return ResponseEntity
                  .status(HttpStatus.CONFLICT)
                  .cacheControl(CacheControl.noCache())
                  .body(null);
            }
            JobRequistion newJob = new JobRequistion();
            newJob.setTitle(title);
            newJob.setCareerLevel(jobDTO.getCareerLevel());
            newJob.setDescription(jobDTO.getDescription());
            newJob.setHeadcount(jobDTO.getHeadcount());
            ApplyPeriodDTO applyPeriodDTO = jobDTO.getApplyPeriod();
            String begin = applyPeriodDTO.getApplyBeginDate();
            String end = applyPeriodDTO.getApplyEndDate();
            ApplyPeriod applyPeriod = new ApplyPeriod();
            LocalDate applyBegin = LocalDate.parse(begin);
            LocalDate applyEnd = LocalDate.parse(end);
            applyPeriod.setApplyBeginDate(applyBegin);
            applyPeriod.setApplyEndDate(applyEnd);
            newJob.setApplyPeriod(applyPeriod);
            newJob.setJobArea(area);
            newJob.setRecruiter(recruiterInfo);
            if (jobDTO.getSarlary().getType() == null) {
              jobDTO.getSarlary().setType(Sarlary.Type.TYPE_MONTHLY.toString());
            }
            newJob.setSarlary(jobDTO.getSarlary());
            //审核状态
            if (jobDTO.getAudit() != null) {
              newJob.setAudit(jobDTO.getAudit());
            } else {
              Audit audit = new Audit();
              audit.setStatus(Audit.Status.STATUS_WAITING.toString());
              newJob.setAudit(audit);
            }
            newJob.setJobCategory(jobDTO.getJobCategory());
            newJob.setStatus(null);
            if (jobDTO.getDepartment() != null) {
              //非必填项
              newJob.setDepartment(jobDTO.getDepartment());
            }
            if (jobDTO.getApplyUrl() != null) {
              newJob.setApplyUrl(jobDTO.getApplyUrl());
            }
            if (jobDTO.getInnerNo() != null) {
              newJob.setInnerNo(jobDTO.getInnerNo());
            }
            if (jobDTO.getJobType() != null) {
              newJob.setJobType(jobDTO.getJobType());
            } else {//默认工作类型是全职
              newJob.setJobType(JobRequistion.Type.TYPE_FULL_TIME.toString());
            }
            JobRequistion jobRequistion = jobService.saveJobRequistion(newJob);
            JobRequistionLog jobRequistionLog = new JobRequistionLog();
            jobRequistionLog.setJobId(jobRequistion.getId());
            jobRequistionLog.setOptUname(disPlayName);
            jobRequistionLog.setOptType(JobRequistionLog.OptType.OptType_CREATE.toString());
            jobRequistionLog.setOptUid(userId);
            jobRequistionLog.setOptDesc(disPlayName + "创建该职位");
            jobRequistionLog.setRecId(id);
            jobRequistionLogService.saveJobRequistionLog(jobRequistionLog);
          }
          return ResponseEntity.status(HttpStatus.OK).cacheControl(
              CacheControl.noCache()).body(null);
        }
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 修改职位需求
   * @Author JiangYh
   * @CreateTime 2017/2/14 18:09
   * @Param
   * @Return
   */
  @PutMapping(value = "/{id}")
  @RequiresRoles("COMPANY")
  public ResponseEntity update(@RequestBody JobRequistionDTO job, @PathVariable UUID id) {
    Subject currentUser = SecurityUtils.getSubject();
    if (currentUser.isAuthenticated()) {
      AuthUser authUser = (AuthUser)currentUser.getPrincipal();
      UUID userId = authUser.getUid();
      //获取用户的名称（昵称）
      String disPlayName = authUser.getDnm();
      if (job != null) {
        JobRequistion newJob = jobService.findById(id);
        //职位修改不能修改职位名称、职位类别、工作地区业务领域属性
        if (job.getInnerNo() != null) {
          newJob.setInnerNo(job.getInnerNo());
        }
        if (job.getDepartment() != null) {
          newJob.setDepartment(job.getDepartment());
        }
        if (job.getApplyPeriod() != null) {
          ApplyPeriod applyPeriod = newJob.getApplyPeriod();
          ApplyPeriodDTO applyPeriodDTO = job.getApplyPeriod();
          String begin = applyPeriodDTO.getApplyBeginDate();
          String end = applyPeriodDTO.getApplyEndDate();
          LocalDate applyBegin = LocalDate.parse(begin);
          LocalDate applyEnd = LocalDate.parse(end);
          applyPeriod.setApplyBeginDate(applyBegin);
          applyPeriod.setApplyEndDate(applyEnd);
          newJob.setApplyPeriod(applyPeriod);
        }
        if (job.getSarlary() != null) {
          newJob.setSarlary(job.getSarlary());
        }
        if (job.getHeadcount() != null) {
          //招聘人数
          newJob.setHeadcount(job.getHeadcount());
        }
        if (job.getCareerLevel() != null) {
          //学历要求
          newJob.setCareerLevel(job.getCareerLevel());
        }
        if (job.getDescription() != null) {
          if (newJob.getDescription() != null) {
            if (!job.getDescription().equals(newJob.getDescription())) {
              newJob.getAudit().setStatus(Audit.Status.STATUS_WAITING.toString());
              newJob.setDescription(job.getDescription());
            } else {
              newJob.setDescription(job.getDescription());
            }
          } else if (newJob.getDescription() == null) {
            newJob.getAudit().setStatus(Audit.Status.STATUS_WAITING.toString());
            newJob.setDescription(job.getDescription());
          }
        }

        if (job.getApplyUrl() != null) {
          if (newJob.getApplyUrl() != null) {
            if (!job.getApplyUrl().equals(newJob.getApplyUrl())) {
              newJob.getAudit().setStatus(Audit.Status.STATUS_WAITING.toString());
              newJob.setApplyUrl(job.getApplyUrl());
            } else {
              newJob.setApplyUrl(job.getApplyUrl());
            }
          } else if (newJob.getApplyUrl() == null) {
            newJob.getAudit().setStatus(Audit.Status.STATUS_WAITING.toString());
            newJob.setApplyUrl(job.getApplyUrl());
          }
        }
        JobRequistion jobRequistion = jobService.saveJobRequistion(newJob);
        JobRequistionLog jobRequistionLog = new JobRequistionLog();
        jobRequistionLog.setJobId(jobRequistion.getId());
        jobRequistionLog.setOptUname(disPlayName);
        jobRequistionLog.setOptType(JobRequistionLog.OptType.OptType_Modify.toString());
        jobRequistionLog.setOptUid(userId);
        jobRequistionLog.setOptDesc(disPlayName + "对该职位进行修改操作");
        jobRequistionLog.setRecId(id);
        jobRequistionLogService.saveJobRequistionLog(jobRequistionLog);
        return ResponseEntity.ok(newJob.getId());
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 职位需求上线
   * @Author JiangYh
   * @CreateTime 2017/2/14 18:09
   * @Param
   * @Return
   */
  @PutMapping(value = "{id}/online")
  @RequiresRoles("COMPANY")
  public ResponseEntity online(@PathVariable UUID id) {
    Subject currentUser = SecurityUtils.getSubject();
    if (currentUser.isAuthenticated()) {
      AuthUser authUser= (AuthUser)currentUser.getPrincipal();
      UUID userId = authUser.getUid();
      //获取用户的名称（昵称）
      String disPlayName = authUser.getDnm();
      if (id != null) {
        JobRequistion job = jobService.findById(id);
        if (job != null) {
          job.setStatus(JobRequistion.Status.STATUS_OPEN.toString());
          JobRequistion jobRequistion = jobService.saveJobRequistion(job);
          JobRequistionLog jobRequistionLog = new JobRequistionLog();
          jobRequistionLog.setJobId(jobRequistion.getId());
          jobRequistionLog.setOptUname(disPlayName);
          jobRequistionLog.setOptType(JobRequistionLog.OptType.OptType_ONLINE.toString());
          jobRequistionLog.setOptUid(userId);
          jobRequistionLog.setOptDesc(disPlayName + "对该职位进行上线操作");
          jobRequistionLog.setRecId(id);
          jobRequistionLogService.saveJobRequistionLog(jobRequistionLog);
          return ResponseEntity.ok(job.getId());
        }
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 职位需求下线
   * @Author JiangYh
   * @CreateTime 2017/2/14 18:09
   * @Param
   * @Return
   */
  @PutMapping(value = "{id}/offline")
  @RequiresRoles("COMPANY")
  public ResponseEntity offline(@PathVariable UUID id) {
    Subject currentUser = SecurityUtils.getSubject();
    if (currentUser.isAuthenticated()) {
      AuthUser authUser= (AuthUser)currentUser.getPrincipal();
      UUID userId = authUser.getUid();
      //获取用户的名称（昵称）
      String disPlayName = authUser.getDnm();
      if (id != null) {
        JobRequistion job = jobService.findById(id);
        if (job != null) {
          job.setStatus(JobRequistion.Status.STATUS_ON_HOLD.toString());
          JobRequistion jobRequistion = jobService.saveJobRequistion(job);
          JobRequistionLog jobRequistionLog = new JobRequistionLog();
          jobRequistionLog.setJobId(jobRequistion.getId());
          jobRequistionLog.setOptUname(disPlayName);
          jobRequistionLog.setOptType(JobRequistionLog.OptType.OptType_OFFLINE.toString());
          jobRequistionLog.setOptUid(userId);
          jobRequistionLog.setOptDesc(disPlayName + "对该职位进行下线操作");
          jobRequistionLog.setRecId(id);
          jobRequistionLogService.saveJobRequistionLog(jobRequistionLog);
          return ResponseEntity.ok(job.getId());
        }
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

}
