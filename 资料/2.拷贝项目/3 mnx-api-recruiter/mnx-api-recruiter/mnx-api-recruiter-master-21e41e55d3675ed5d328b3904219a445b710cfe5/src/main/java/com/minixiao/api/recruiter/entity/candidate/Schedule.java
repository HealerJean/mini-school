package com.minixiao.api.recruiter.entity.candidate;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_candidate_schedule")
@TypeDefs({@TypeDef(name = "StringJsonObject", typeClass = StringJsonUserType.class)})
public class Schedule implements Serializable {

  private static final long SERIVALVERSIONUID = 876280513342148585L;

  //任务安排唯一标识
  @Id
  @GenericGenerator(name = "idGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "idGenerator")
  private UUID id;

  //候选人信息[{"candidateId":"1233333","candidateName":"王诗语"}]
  @Type(type = "StringJsonObject")
  @Column(nullable = false)
  private String candidateInfo;

  //笔面试Id
  @Column(nullable = false)
  private UUID examManagerId;

  //公司Id
  @Column(nullable = false)
  private UUID recId;

  //公司名称
  @Column(nullable = false)
  private String recName;

  //考试地点
  @Column(length = 30, nullable = false)
  private String location;

  //考试城市
  @Column(length = 20, nullable = false)
  private String city;

  //开始时间
  @Column(nullable = false)
  @CreationTimestamp
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate startTime;

  //时长
  @Column(length = 80, nullable = false)
  private String duration;

  //容纳人数
  @Column(nullable = false)
  private String accommodateNo;

  //考官
  @Column(length = 20, nullable = false)
  private String interviewer;

  //类型
  @Column(length = 20, nullable = false)
  private String type;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }


  public String getCandidateInfo() {
    return candidateInfo;
  }

  public void setCandidateInfo(String candidateInfo) {
    this.candidateInfo = candidateInfo;
  }

  public UUID getExamManagerId() {
    return examManagerId;
  }

  public void setExamManagerId(UUID examManagerId) {
    this.examManagerId = examManagerId;
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

  /**
   * 构造函数.
   */
  public Schedule() {
  }
}
