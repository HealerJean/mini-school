package com.minixiao.api.recruiter.controller.candidate;

import com.minixiao.api.recruiter.entity.candidate.Schedule;
import com.minixiao.api.recruiter.service.candidate.ScheduleService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
public class ScheduleQuery {

  @Autowired
  private ScheduleService service;

  /**
   * 查询任务安排列表.
   *
   * @param pageable 分页参数
   * @return ResponseEntity
   */
  @RequiresRoles("COMPANY")
  @GetMapping(value = "/candidate/schedule")
  public ResponseEntity<Page<Schedule>> findAll(Pageable pageable) {
    try {
      //如果请求正通过认证，获取其身份信息
      Subject currentUser = SecurityUtils.getSubject();
      if (currentUser.isAuthenticated()) {
        AuthUser authUser = (AuthUser) currentUser.getPrincipal();
        //获取用户所属机构的ID
        UUID recId = authUser.getOid();
        Page<Schedule> result = service.findByRecId(recId, pageable);
        return ResponseEntity.ok(result);
      }
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).cacheControl(
          CacheControl.noCache()).body(null);
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).cacheControl(
          CacheControl.noCache()).body(null);
    }
  }
}
