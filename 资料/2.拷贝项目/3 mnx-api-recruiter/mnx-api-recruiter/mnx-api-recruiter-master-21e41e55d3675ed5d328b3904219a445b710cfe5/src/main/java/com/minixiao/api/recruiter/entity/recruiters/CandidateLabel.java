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
 * @Description 候选人标签实体类.
 * @Author xiachao
 * @CreateTime 2017/2/20 18:33
 */
@Entity
@Table(name = "tb_rec_candidate_label")
public class CandidateLabel implements Serializable {

  private static final long serialVersionUID = 29521970677683248L;

  //标签唯一id
  @Id
  @GenericGenerator(name = "labelIdGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "labelIdGenerator")
  private UUID id;

  //公司唯一Id
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "rec_id")
  private RecruiterInfo recruiterInfo;

  //标签名称
  @Column(length = 30, nullable = false, unique = true)
  private String name;

  //标签状态 正常:true和停用:false两种状态
  @Column(columnDefinition = "bool default true")
  private boolean status;

  //标签使用次数
  @Column
  private Integer useCount;

  //创建时间
  @CreationTimestamp
  @JsonFormat(pattern = "yyyyMMddHHmmss")
  private LocalDateTime createdOn;

  //修改时间
  @UpdateTimestamp
  @JsonFormat(pattern = "yyyyMMddHHmmss")
  private LocalDateTime updatedOn;

  /**
   * .
   */
  public CandidateLabel() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public RecruiterInfo getRecruiterInfo() {
    return recruiterInfo;
  }

  public void setRecruiterInfo(RecruiterInfo recruiterInfo) {
    this.recruiterInfo = recruiterInfo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public Integer getUseCount() {
    return useCount;
  }

  public void setUseCount(Integer useCount) {
    this.useCount = useCount;
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
