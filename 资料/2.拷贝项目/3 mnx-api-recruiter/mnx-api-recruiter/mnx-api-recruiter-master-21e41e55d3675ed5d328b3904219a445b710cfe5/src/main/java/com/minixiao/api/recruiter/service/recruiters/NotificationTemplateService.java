package com.minixiao.api.recruiter.service.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.NotificationTemplate;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;

import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/17 10:13
 */

public interface NotificationTemplateService {
  /**
   * 保存消息通知模板.
   *
   * @param notificationTemplate n
   */
  void saveNotificationTemplate(NotificationTemplate notificationTemplate);

  /**
   * 根据id查询该邮件通知模板.
   *
   * @param id i
   * @return n
   */
  NotificationTemplate findById(UUID id);

  /**
   * 删除邮件通知模板 .
   *
   * @param id id
   */
  void deleteNotificationTemplate(UUID id);

  /**
   * 查询该公司下的所有模板 .
   *
   * @param recruiterInfo recruiterInfo
   * @return page
   */
  List<NotificationTemplate> findByRecruiterInfo(RecruiterInfo recruiterInfo);

  /**
   * 返回邮件模板内容
   * @return string
   */
  String findContentById(UUID id);
}
