package com.minixiao.api.recruiter.dto.recruiters;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterUser;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by xiachao on 2017/3/11.
 */
public class NotificationTemplateDTO {
  //模版ID
  private UUID id;
  //模版名
  private String name;

  //公司ID
  private UUID recId;

  //用户Id
  private UUID userId;

  //模板类型
  private String type;

  //通知模版标题
  private String title;

  //通知模版内容
  //其他变量全部存储于content中，无需再额外定义address,telephone等变量
  private String content;
  //创建时间
  private LocalDateTime createdOn;
  //修改时间
  private LocalDateTime updatedOn;


}
