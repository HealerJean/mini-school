package com.minixiao.api.recruiter.repository.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.NotificationTemplate;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/17 10:12
 */

public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplate, UUID> {
  /**
   * 根据Id查询该邮件通知模板 .
   *
   * @param id id
   * @return NotificationTemplate
   */
  NotificationTemplate findById(UUID id);

  /**
   * 查询该公司下的所有模板 .
   *
   * @param recruiterInfo recruiterInfo
   * @return page
   */
  List<NotificationTemplate> findByRecruiterInfo(RecruiterInfo recruiterInfo);

  /**
   * 返回邮件模板内容
   *
   * @return string
   */
  @Query(value = "select content from tb_rec_template where id =?1", nativeQuery = true)
  String findContentById(UUID id);
}
