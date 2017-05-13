package com.minixiao.api.recruiter.entity.candidate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_candidate_handle_history")
public class HandleHistory implements Serializable {

  private static final long SERIVALVERSIONUID = 876280513342148585L;

  //处理历史唯一标识
  @Id
  @GenericGenerator(name = "idGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "idGenerator")
  private UUID id;

  //候选人实体
  @Column( nullable = false)
  public UUID applicationId;

  //候选人名称
  @Column(length = 20, nullable = false)
  private String candidateName;

  //公司ID
  @Column(nullable = false)
  private UUID recId;

  //公司名称
  @Column(nullable = false)
  private String recName;

  //职位ID
  @Column(nullable = false)
  private UUID jobId;

  //职位名称
  @Column(length = 80, nullable = false)
  private String jobName;

  //操作人ID
  @Column(nullable = false)
  private UUID optUid;

  //操作人名称
  @Column(length = 20, nullable = false)
  private String optUname;

  //操作类型 （）
  @Column(length = 20, nullable = false)
  private String optType;

  //处理历史描述
  @Column(length = 250)
  private String description;

  //操作时间
  @Column(nullable = false)
  @CreationTimestamp
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate optDate;

  //候选人的状态
  @Column(length = 20, nullable = false)
  private String status;

  //备注
  @Column(length = 255, nullable = true)
  private String remark;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(UUID applicationId) {
    this.applicationId = applicationId;
  }

  public String getCandidateName() {
    return candidateName;
  }

  public void setCandidateName(String candidateName) {
    this.candidateName = candidateName;
  }

  public UUID getRecId() {
    return recId;
  }

  public void setRecId(UUID recId) {
    this.recId = recId;
  }

  public String getRecName() {
    return recName;
  }

  public void setRecName(String recName) {
    this.recName = recName;
  }

  public UUID getJobId() {
    return jobId;
  }

  public void setJobId(UUID jobId) {
    this.jobId = jobId;
  }

  public String getJobName() {
    return jobName;
  }

  public void setJobName(String jobName) {
    this.jobName = jobName;
  }

  public UUID getOptUid() {
    return optUid;
  }

  public void setOptUid(UUID optUid) {
    this.optUid = optUid;
  }

  public String getOptUname() {
    return optUname;
  }

  public void setOptUname(String optUname) {
    this.optUname = optUname;
  }

  public String getOptType() {
    return optType;
  }

  public void setOptType(String optType) {
    this.optType = optType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LocalDate getOptDate() {
    return optDate;
  }

  public void setOptDate(LocalDate optDate) {
    this.optDate = optDate;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  /**
   * 构造函数.
   */
  public HandleHistory() {
  }
}
