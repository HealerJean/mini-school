package com.minixiao.api.recruiter.controller.candidate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minixiao.api.recruiter.dto.candidate.ScheduleDTO;
import com.minixiao.api.recruiter.entity.candidate.CandidateInfo;
import com.minixiao.api.recruiter.entity.candidate.HandleHistory;
import com.minixiao.api.recruiter.entity.candidate.Schedule;
import com.minixiao.api.recruiter.service.candidate.HandleHistoryService;
import com.minixiao.api.recruiter.service.candidate.ScheduleService;
import mnx.infra.apiauth.server.domain.AuthUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/candidate")
public class ScheduleCommand {

  @Autowired
  private ScheduleService service;

  @Autowired
  private HandleHistoryService handleHistoryService;


  /**
   * 创建任务安排.
   *
   * @param scheduleDTO 任务安排的dto对象
   * @return ResponseEntity
   */
  @RequiresRoles("COMPANY")
  @PostMapping(value = "/schedule")
  public ResponseEntity<Schedule> saveSchedule(@RequestBody ScheduleDTO scheduleDTO) {
    try {
      Subject currentUser = SecurityUtils.getSubject();
      if (currentUser.isAuthenticated()) {
        AuthUser authUser = (AuthUser) currentUser.getPrincipal();
        //获取用户唯一的标识ID
        UUID userId = authUser.getUid();
        //获取用户的名称（昵称）
        String disPlayName = authUser.getDnm();
        service.saveSchedule(scheduleDTO, userId, disPlayName);
        return ResponseEntity.status(HttpStatus.OK).cacheControl(
            CacheControl.noCache()).body(null);
      }
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).cacheControl(
          CacheControl.noCache()).body(null);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).cacheControl(
          CacheControl.noCache()).body(null);
    }

  }

  /**
   * 修改任务安排.
   *
   * @param scheduleDTO 任务安排的dto对象
   * @return ResponseEntity
   */
  @RequiresRoles("COMPANY")
  @PutMapping(value = "/schedule/{scheduleId}")
  public ResponseEntity ModifySchedule(@RequestBody ScheduleDTO scheduleDTO, @PathVariable UUID scheduleId) {
    try {
      //如果请求正通过认证，获取其身份信息
      Subject currentUser = SecurityUtils.getSubject();
      if (currentUser.isAuthenticated()) {
        AuthUser authUser = (AuthUser) currentUser.getPrincipal();
        //获取用户唯一的标识ID
        UUID userId = authUser.getUid();
        //获取用户的名称（昵称）
        String disPlayName = authUser.getDnm();
        service.modifySchedule(scheduleDTO, scheduleId, userId, disPlayName);
        return ResponseEntity.status(HttpStatus.OK).cacheControl(
            CacheControl.noCache()).body(null);
      }
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).cacheControl(
          CacheControl.noCache()).body(null);
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).cacheControl(
          CacheControl.noCache()).body(null);
    }

  }

  /**
   * 删除任务安排.
   *
   * @param scheduleId 任务安排的dto对象
   * @return ResponseEntity
   */
  @RequiresRoles("COMPANY")
  @DeleteMapping(value = "/schedule/{scheduleId}")
  public ResponseEntity deleteSchedule(@PathVariable UUID scheduleId) {
    try {
      Subject currentUser = SecurityUtils.getSubject();
      if (currentUser.isAuthenticated()) {
        AuthUser authUser = (AuthUser) currentUser.getPrincipal();
        //获取用户唯一的标识ID
        UUID userId = authUser.getUid();
        //获取用户的名称（昵称）
        String disPlayName = authUser.getDnm();
        //获取用户的身份类型
        service.deleteSchedule(scheduleId, userId, disPlayName);
          return ResponseEntity.status(HttpStatus.OK).cacheControl(
              CacheControl.noCache()).body(null);
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
