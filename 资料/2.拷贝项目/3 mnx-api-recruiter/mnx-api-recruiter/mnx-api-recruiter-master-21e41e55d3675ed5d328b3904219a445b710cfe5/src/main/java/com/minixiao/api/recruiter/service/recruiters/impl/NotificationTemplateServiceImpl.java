package com.minixiao.api.recruiter.service.recruiters.impl;

import com.minixiao.api.recruiter.entity.recruiters.NotificationTemplate;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.repository.recruiters.NotificationTemplateRepository;
import com.minixiao.api.recruiter.service.recruiters.NotificationTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/17 10:13
 */
@Service
@Transactional(readOnly = true)
public class NotificationTemplateServiceImpl implements NotificationTemplateService {
  @Autowired
  private NotificationTemplateRepository notificationTemplateRepository;

  /**
   * 保存消息通知模板.
   *
   * @param notificationTemplate n
   */
  @Override
  @Transactional(readOnly = false)
  public void saveNotificationTemplate(NotificationTemplate notificationTemplate) {
    notificationTemplateRepository.save(notificationTemplate);
  }

  /**
   * 根据id查询该邮件通知模板.
   *
   * @param id i
   * @return n
   */
  @Override
  public NotificationTemplate findById(UUID id) {
    return notificationTemplateRepository.findById(id);
  }

  /**
   * 删除邮件通知模板 .
   *
   * @param id id
   */
  @Override
  @Transactional(readOnly = false)
  public void deleteNotificationTemplate(UUID id) {
    notificationTemplateRepository.delete(id);
  }

  /**
   * 查询该公司下的所有模板 .
   *
   * @param recruiterInfo recruiterInfo
   * @return page
   */
  @Override
  public List<NotificationTemplate> findByRecruiterInfo(RecruiterInfo recruiterInfo) {
    return notificationTemplateRepository.findByRecruiterInfo(recruiterInfo);
  }

  /**
   * 返回邮件模板内容
   *
   * @return string
   */
  @Override
  public String findContentById(UUID id) {
    return notificationTemplateRepository.findContentById(id);
  }
}
