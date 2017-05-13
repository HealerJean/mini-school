package com.minixiao.api.recruiter.dto.candidate;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * Created by WangYingjie on 2017/2/13.
 */
public class JobTarget {
  private UUID id;

  private UUID stuId;

  private String area;

  @JsonProperty(value = "job_name")
  private String jobName;

  private String department;

  @JsonProperty(value = "job_category")
  private String jobCategory;

  @JsonProperty(value = "is_current")
  private boolean isCurrent;

  @JsonProperty(value = "current_status")
  private String currentStatus;

  private float score;

  /**
   *空构造方法.
   */
  public JobTarget() {
  }

  public JobTarget(UUID id, UUID stuId, String area, String jobName, String department,
                   String jobCategory, boolean isCurrent, String currentStatus, float score) {
    this.id = id;
    this.stuId = stuId;
    this.area = area;
    this.jobName = jobName;
    this.department = department;
    this.jobCategory = jobCategory;
    this.isCurrent = isCurrent;
    this.currentStatus = currentStatus;
    this.score = score;
  }

  /**
   * 默认构造方法.
   */


  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getStuId() {
    return stuId;
  }

  public void setStuId(UUID stuId) {
    this.stuId = stuId;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getJobCategory() {
    return jobCategory;
  }

  public void setJobCategory(String jobCategory) {
    this.jobCategory = jobCategory;
  }

  public boolean getIsCurrent() {
    return isCurrent;
  }

  public void setIsCurrent(boolean current) {
    this.isCurrent = current;
  }

  public String getCurrentStatus() {
    return currentStatus;
  }

  public void setCurrentStatus(String currentStatus) {
    this.currentStatus = currentStatus;
  }

  public float getScore() {
    return score;
  }

  public void setScore(float score) {
    this.score = score;
  }

  public String getJobName() {
    return jobName;
  }

  public void setJobName(String jobName) {
    this.jobName = jobName;
  }
}
