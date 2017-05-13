package com.minixiao.api.recruiter.controller.jobrequistion;

import com.minixiao.api.recruiter.dto.jobrequisition.JobRequistionDTO;
import com.minixiao.api.recruiter.entity.jobrequisition.JobRequistion;
import com.minixiao.api.recruiter.entity.jobrequisition.JobRequistionLog;
import com.minixiao.api.recruiter.service.jobrequistion.JobRequistionLogService;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Description .
 * @Author JiangYh
 * @CreateTime 2017/2/13 11:09
 */
@RestController
@RequestMapping(value="/requisitionLog")
@Transactional(readOnly = true)
public class JobRequistionLogQuery {

  @Autowired
  private JobRequistionLogService service;

  @RequiresRoles("COMPANY")
  @GetMapping(value = "/{jobId}")
  public ResponseEntity<Page<JobRequistionLog>> list(@PathVariable UUID jobId, Pageable page) {
    Subject currentUser = SecurityUtils.getSubject();
    if (currentUser.isAuthenticated()) {
      AuthUser authUser = (AuthUser) currentUser.getPrincipal();
      //获取用户所属机构的ID
      UUID recId = authUser.getOid();
      Page<JobRequistionLog> result = service.findByJobId(jobId, page);
      return ResponseEntity.ok(result);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }


}
