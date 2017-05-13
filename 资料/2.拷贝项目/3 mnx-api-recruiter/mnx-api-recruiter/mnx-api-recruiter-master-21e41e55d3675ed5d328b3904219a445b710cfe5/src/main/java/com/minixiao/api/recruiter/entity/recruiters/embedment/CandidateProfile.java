package com.minixiao.api.recruiter.entity.recruiters.embedment;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @Description 筛选器中的嵌入类 .
 * @Author xiachao
 * @CreateTime 2017/2/13 15:20
 * @Param
 * @Return
 */
@Embeddable
public class CandidateProfile {
  //职位名称
  @Column(name = "profile_position")
  private String position;

  //最高学历
  //筛选建议用最低学历做筛选
  @Column(name = "profile_degree", length = 20)
  private String degree;

  //毕业学校（可多选）
  @Column(name = "profile_school")
  private String school;

  //专业名称
  @Column(name = "profile_major")
  private String major;

  //处理状态
  @Column(name = "profile_status", length = 20)
  private String status;

  //简历标签(英文状态逗号切分)
  @Column(name = "profile_label")
  private String label;

  //性别
  @Column(name = "profile_gender", length = 10)
  private String gender;

  //聘用部门
  @Column(name = "profile_department")
  private String department;

  //专业排名
  @Column(name = "profile_rank", length = 20)
  private String rank;

  //英语等级
  @Column(name = "profile_english_level", length = 100)
  private String englishLevel;

  //奖学金
  @Column(name = "profile_reward", length = 30)
  private String reward;

  //附件简历
  @Column(name = "profile_attachment", length = 30)
  private String attachment;

  //手机号码
  @Column(name = "profile_mobile", length = 30)
  private String mobile;

  //城市，英文状态逗号切分
  @Column(name = "profile_city")
  private String city;

  //出生年份大于
  @Column(name = "profile_birth_from", length = 50)
  private String birthFrom;

  //出生年份小于
  @Column(name = "profile_birth_to", length = 50)
  private String birthTo;

  //毕业年份大于
  @Column(name = "profile_grad_from", length = 50)
  private String graduateFrom;

  //毕业年份小于
  @Column(name = "profile_grad_to", length = 50)
  private String graduateTo;

  //投递时间大于
  @Column(name = "profile_delivery_from", length = 50)
  private String deliveryFrom;

  //投递时间小于
  @Column(name = "profile_delivery_to", length = 50)
  private String deliveryTo;


  /**
   * .
   */
  public CandidateProfile() {
  }

  /**
   * .
   */


  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getMajor() {
    return major;
  }

  public void setMajor(String major) {
    this.major = major;
  }

  public String getAttachment() {
    return attachment;
  }

  public void setAttachment(String attachment) {
    this.attachment = attachment;
  }

  public String getReward() {
    return reward;
  }

  public void setReward(String reward) {
    this.reward = reward;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getSchool() {
    return school;
  }

  public void setSchool(String school) {
    this.school = school;
  }

  public String getEnglishLevel() {
    return englishLevel;
  }

  public void setEnglishLevel(String englishLevel) {
    this.englishLevel = englishLevel;
  }

  public String getBirthFrom() {
    return birthFrom;
  }

  public void setBirthFrom(String birthFrom) {
    this.birthFrom = birthFrom;
  }

  public String getBirthTo() {
    return birthTo;
  }

  public void setBirthTo(String birthTo) {
    this.birthTo = birthTo;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getGraduateTo() {
    return graduateTo;
  }

  public void setGraduateTo(String graduateTo) {
    this.graduateTo = graduateTo;
  }

  public String getGraduateFrom() {
    return graduateFrom;
  }

  public void setGraduateFrom(String graduateFrom) {
    this.graduateFrom = graduateFrom;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getDeliveryTo() {
    return deliveryTo;
  }

  public void setDeliveryTo(String deliveryTo) {
    this.deliveryTo = deliveryTo;
  }

  public String getDeliveryFrom() {
    return deliveryFrom;
  }

  public void setDeliveryFrom(String deliveryFrom) {
    this.deliveryFrom = deliveryFrom;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getDegree() {
    return degree;
  }

  public void setDegree(String degree) {
    this.degree = degree;
  }

  public String getRank() {
    return rank;
  }

  public void setRank(String rank) {
    this.rank = rank;
  }
}