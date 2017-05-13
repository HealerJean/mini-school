package com.minixiao.api.recruiter.entity.recruiters;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Description 邮件通知模板 .
 * @Author xiachao
 * @CreateTime 2017/2/13 15:22
 * @Param
 * @Return
 */
@Entity
@Table(name = "tb_rec_template")
public class NotificationTemplate implements Serializable {
  private static final long serialVersionUID = 9171113504124018759L;
  //模版ID
  @Id
  @GenericGenerator(name = "templateIdGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "templateIdGenerator")
  private UUID id;
  //模版名
  @Column(length = 40)
  private String name;

  //公司ID
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "rec_id")
  private RecruiterInfo recruiterInfo;

  //用户Id
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private RecruiterUser recruiterUser;

  //用户姓名
  @Column(nullable = false, length = 100)
  private String userName;

  //模板类型
  @Column(length = 20)
  private String type;

  //通知模版标题
  @Column(length = 50, nullable = false)
  private String title;

  //通知模版内容
  //其他变量全部存储于content中，无需再额外定义address,telephone等变量
  @Column(columnDefinition = "text")
  private String content;
  //创建时间
  @CreationTimestamp
  @JsonFormat(pattern = "yyyyMMddHHmmss")
  private LocalDateTime createdOn;
  //修改时间
  @UpdateTimestamp
  @JsonFormat(pattern = "yyyyMMddHHmmss")
  private LocalDateTime updatedOn;

  public static enum Type {
    EMAIL("邮件"), SMS("短信");
    public String value;

    /**
     * .
     *
     * @param value value
     */
    private Type(String value) {
      this.value = value;
    }
  }

  /**
   * .
   */
  public NotificationTemplate() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public RecruiterUser getRecruiterUser() {
    return recruiterUser;
  }

  public void setRecruiterUser(RecruiterUser recruiterUser) {
    this.recruiterUser = recruiterUser;
  }

  public RecruiterInfo getRecruiterInfo() {
    return recruiterInfo;
  }

  public void setRecruiterInfo(RecruiterInfo recruiterInfo) {
    this.recruiterInfo = recruiterInfo;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(LocalDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public LocalDateTime getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(LocalDateTime updatedOn) {
    this.updatedOn = updatedOn;
  }
}
