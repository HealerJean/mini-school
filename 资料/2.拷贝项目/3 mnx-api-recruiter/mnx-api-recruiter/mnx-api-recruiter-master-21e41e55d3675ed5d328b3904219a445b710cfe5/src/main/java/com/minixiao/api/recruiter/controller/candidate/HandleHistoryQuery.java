package com.minixiao.api.recruiter.controller.candidate;

import com.minixiao.api.recruiter.entity.candidate.HandleHistory;
import com.minixiao.api.recruiter.service.candidate.HandleHistoryService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
public class HandleHistoryQuery {


  @Autowired
  private HandleHistoryService service;

  /**
   * .
   *
   * @return ResponseEntity
   */
  @RequiresRoles("COMPANY")
  @GetMapping(value = "/candidate/{applicationId}/history")
  public ResponseEntity<Page<HandleHistory>> findList(@PathVariable UUID applicationId, Pageable page) {
    try {
      //如果请求正通过认证，获取其身份信息
      Subject currentUser = SecurityUtils.getSubject();
      if (currentUser.isAuthenticated()) {
        Page<HandleHistory> result = service.findByApplicationId(applicationId, page);
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
