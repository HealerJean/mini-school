package com.minixiao.api.recruiter.dto.candidate;

import com.minixiao.api.recruiter.entity.candidate.CandidateInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class ScheduleDTO {

  private UUID id;

  private CandidateInfo[] candidateInfo;

  private UUID examManagerId;

  private UUID recId;

  private String recName;

  private String location;

  private String city;

  private String duration;

  private String accommodateNo;

  private String interviewer;

  private String type;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public CandidateInfo[] getCandidateInfo() {
    return candidateInfo;
  }

  public void setCandidateInfo(CandidateInfo[] candidateInfo) {
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
  public ScheduleDTO() {
  }
}
