package com.minixiao.api.recruiter.controller.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.NotificationTemplate;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterUser;
import com.minixiao.api.recruiter.service.recruiters.NotificationTemplateService;
import mnx.infra.apiauth.server.domain.AuthUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Description 通知模板增删改.
 * @Author xiachao
 * @CreateTime 2017/2/17 10:13
 */
@RestController
public class NotificationTemplateCommand {
  private Logger logger = LoggerFactory.getLogger(CandidateFilterQuery.class);

  @Autowired
  private NotificationTemplateService notificationTemplateService;


  /**
   * @Description 创建邮件通知模板.
   * @Author xiachao
   * @CreateTime 2017/2/17 10:33
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PostMapping("/recruiters/{recId}/templates/email")
  public ResponseEntity createEmailTemplate(@RequestBody NotificationTemplate notificationTemplate,
                                            @PathVariable UUID recId) {
    if (recId != null) {
      Subject currentUser = SecurityUtils.getSubject();
      if (currentUser.isAuthenticated()) {
        AuthUser authUser = (AuthUser) currentUser.getPrincipal();
        UUID recId2 = authUser.getOid();
        UUID userId = authUser.getUid();
        String userName = authUser.getDnm();
        RecruiterInfo recruiterInfo = new RecruiterInfo();
        recruiterInfo.setId(recId2);
        RecruiterUser recruiterUser = new RecruiterUser();
        recruiterUser.setId(userId);
        notificationTemplate.setRecruiterInfo(recruiterInfo);
        notificationTemplate.setRecruiterUser(recruiterUser);
        notificationTemplate.setUserName(userName);
        notificationTemplateService.saveNotificationTemplate(notificationTemplate);
        return ResponseEntity.ok(HttpStatus.CREATED);
      }

    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 修改邮件通知模板 .
   * @Author xiachao
   * @CreateTime 2017/2/17 10:45
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PutMapping("/recruiters/{recId}/templates/email/{id}")
  public ResponseEntity updateEmailTemplate(@RequestBody NotificationTemplate notificationTemplate,
                                            @PathVariable UUID recId, @PathVariable UUID id) {
    if (recId != null && id != null) {
      NotificationTemplate notificationTemplateNew = notificationTemplateService.findById(id);
      if (notificationTemplateNew != null) {
        notificationTemplateNew.setName(notificationTemplate.getName());
        notificationTemplateNew.setTitle(notificationTemplate.getTitle());
        notificationTemplateNew.setContent(notificationTemplate.getContent());
        //保存修改到数据库
        notificationTemplateService.saveNotificationTemplate(notificationTemplateNew);
        return ResponseEntity.ok(null);
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 删除邮件通知模板 .
   * @Author xiachao
   * @CreateTime 2017/2/17 10:53
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @DeleteMapping("/recruiters/{recId}/templates/email/{id}")
  public ResponseEntity deleteEmailTemplate(@PathVariable UUID recId, @PathVariable UUID id) {
    if (recId != null && id != null) {
      notificationTemplateService.deleteNotificationTemplate(id);
      return ResponseEntity.ok(null);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

}
