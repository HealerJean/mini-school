package com.minixiao.api.recruiter.entity.recruiters;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minixiao.api.recruiter.entity.recruiters.embedment.CandidateProfile;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
 * @Description 筛选器实体类 .
 * @Author xiachao
 * @CreateTime 2017/2/13 15:19
 * @Param
 * @Return
 */
@Entity
@Table(name = "tb_rec_filter_candidate")
public class CandidateFilter implements Serializable {
  private static final long serialVersionUID = -1580713880900989872L;
  //个人资料筛选器ID
  @Id
  @GenericGenerator(name = "filterIdGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "filterIdGenerator")
  private UUID id;

  //筛选器名称
  @Column(length = 40, nullable = false,unique = true)
  private String name;

  //用户Id
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private RecruiterUser recruiterUser;
  //个人资料筛选辅助类
  @Embedded
  private CandidateProfile candidateProfile;
  //创建时间
  @CreationTimestamp
  @JsonFormat(pattern = "yyyyMMddHHmmss")
  private LocalDateTime createdOn;
  //修改时间
  @JsonFormat(pattern = "yyyyMMddHHmmss")
  @UpdateTimestamp
  private LocalDateTime updatedOn;

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

  public CandidateProfile getCandidateProfile() {
    return candidateProfile;
  }

  public void setCandidateProfile(CandidateProfile candidateProfile) {
    this.candidateProfile = candidateProfile;
  }

  public RecruiterUser getRecruiterUser() {
    return recruiterUser;
  }

  public void setRecruiterUser(RecruiterUser recruiterUser) {
    this.recruiterUser = recruiterUser;
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

