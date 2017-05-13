package com.minixiao.api.recruiter.controller.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.NotificationTemplate;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.service.recruiters.NotificationTemplateService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/17 10:14
 */
@RestController
public class NotificationTemplateQuery {
  private Logger logger = LoggerFactory.getLogger(CandidateFilterQuery.class);

  @Autowired
  private NotificationTemplateService notificationTemplateService;

  /**
   * @Description 根据公司Id查询该公司下的所有邮件通知模板 返回List.
   * @Author xiachao
   * @CreateTime 2017/2/17 10:58
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @GetMapping("/recruiters/{recId}/templates/email")
  public ResponseEntity<List<NotificationTemplate>> findAllEmailTemplate(@PathVariable UUID recId) {
    if (recId != null) {
      RecruiterInfo recruiterInfo = new RecruiterInfo();
      recruiterInfo.setId(recId);
      List<NotificationTemplate> notificationTemplates = notificationTemplateService
          .findByRecruiterInfo(recruiterInfo);

      return ResponseEntity.ok(notificationTemplates);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }


  /**
   * @Description 查询某一个模板信息  .
   * @Author xiachao
   * @CreateTime 2017/2/17 11:05
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @GetMapping("/recruiters/{recId}/templates/email/{id}")
  public ResponseEntity<NotificationTemplate> findEmailTemplate(@PathVariable UUID recId,
                                                                @PathVariable UUID id) {
    if (recId != null && id != null) {
      NotificationTemplate notificationTemplate = notificationTemplateService.findById(id);
      return ResponseEntity.ok(notificationTemplate);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

}
