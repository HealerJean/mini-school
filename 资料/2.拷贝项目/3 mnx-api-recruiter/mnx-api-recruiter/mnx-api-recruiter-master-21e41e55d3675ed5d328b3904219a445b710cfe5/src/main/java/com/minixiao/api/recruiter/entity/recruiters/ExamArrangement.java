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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/*
 * @Description 笔面试安排实体类.
 * @Author xiachao
 * @CreateTime 2017/2/9 14:06
 */

@Entity
@Table(name = "tb_rec_exam_arrangement")
public class ExamArrangement implements Serializable {
  private static final long serialVersionUID = -3803884981687726715L;

  //任务安排唯一标识
  @Id
  @GenericGenerator(name = "idGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "idGenerator")
  private UUID id;
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
  //阶段（笔试/面试）
  @Column(length = 30, nullable = false)
  private String stage;
  //场地名称
  @Column(length = 50, nullable = false)
  private String areaName;
  //笔面地点
  @Column(length = 100, nullable = false)
  private String location;
  //笔面城市
  @Column(length = 30, nullable = false)
  private String city;
  //笔面开始时间
  @Column(nullable = false)
  private LocalDate startTime;
  //时长
  @Column(length = 20, nullable = false)
  private String duration;
  //人数
  @Column(length = 15, nullable = false)
  private String accommodateNo;
  //面试官
  @Column(length = 128, nullable = false)
  private String interviewer;
  // 笔试/面试
  @Column(length = 20, nullable = false)
  private String type;
  //笔面试创建时间
  @CreationTimestamp
  @JsonFormat(pattern = "yyyyMMddHHmmss")
  private LocalDateTime createdOn;
  //笔面试修改时间
  @UpdateTimestamp
  @JsonFormat(pattern = "yyyyMMddHHmmss")
  private LocalDateTime updatedOn;

  /**
   * .
   */
  public ExamArrangement() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public RecruiterUser getRecruiterUser() {
    return recruiterUser;
  }

  public void setRecruiterUser(RecruiterUser recruiterUser) {
    this.recruiterUser = recruiterUser;
  }

  public String getStage() {
    return stage;
  }

  public void setStage(String stage) {
    this.stage = stage;
  }

  public String getAreaName() {
    return areaName;
  }

  public RecruiterInfo getRecruiterInfo() {
    return recruiterInfo;
  }

  public void setRecruiterInfo(RecruiterInfo recruiterInfo) {
    this.recruiterInfo = recruiterInfo;
  }

  public void setAreaName(String areaName) {
    this.areaName = areaName;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public LocalDate getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalDate startTime) {
    this.startTime = startTime;
  }

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public String getAccommodateNo() {
    return accommodateNo;
  }

  public void setAccommodateNo(String accommodateNo) {
    this.accommodateNo = accommodateNo;
  }

  public String getInterviewer() {
    return interviewer;
  }

  public void setInterviewer(String interviewer) {
    this.interviewer = interviewer;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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
