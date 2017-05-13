package com.minixiao.api.recruiter.dto.candidate;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by WangYingjie on 2017/2/13.
 */
public class Education {
  private String rank;

  private String major;

  private String degree;

  private String school;

  @JsonProperty(value = "end_date")
  private String endDate;

  @JsonProperty(value = "is_highest")
  private boolean isHighest;

  @JsonProperty(value = "start_date")
  private String startDate;

  /**
   * 空构造函数.
   */
  public Education() {
  }

  /**
   * 默认构造函数.
   */
  public Education(String rank, String major, String degree, String school, String endDate,
                   boolean isHighest, String startDate) {
    this.rank = rank;
    this.major = major;
    this.degree = degree;
    this.school = school;
    this.endDate = endDate;
    this.isHighest = isHighest;
    this.startDate = startDate;
  }

  public String getRank() {
    return rank;
  }

  public void setRank(String rank) {
    this.rank = rank;
  }

  public String getMajor() {
    return major;
  }

  public void setMajor(String major) {
    this.major = major;
  }

  public String getDegree() {
    return degree;
  }

  public void setDegree(String degree) {
    this.degree = degree;
  }

  public String getSchool() {
    return school;
  }

  public void setSchool(String school) {
    this.school = school;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public boolean getIsHighest() {
    return isHighest;
  }

  public void setIsHighest(boolean isHighest) {
    this.isHighest = isHighest;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
}
