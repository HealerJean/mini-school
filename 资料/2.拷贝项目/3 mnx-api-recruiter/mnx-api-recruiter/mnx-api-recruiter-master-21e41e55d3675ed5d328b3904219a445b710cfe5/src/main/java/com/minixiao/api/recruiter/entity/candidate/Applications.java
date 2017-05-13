package com.minixiao.api.recruiter.entity.candidate;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by WangYingjie on 2017/2/15.
 */
@Entity
@Table(name = "tb_candidate_application")
@TypeDefs({@TypeDef(name = "StringJsonObject", typeClass = StringJsonUserType.class)})
public class Applications implements Serializable {
  private static final long SERIVALVERSIONUID = 876280513342148585L;

  @Id
  @GenericGenerator(name = "idGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "idGenerator")
  private UUID id;

  @Column(length = 32, nullable = false)
  private UUID stuId;

  @Column(length = 32, nullable = false)
  private UUID recId;

  @Column(length = 128, nullable = false)
  private String recName;

  //职位
  @Type(type = "StringJsonObject")
  @Column(nullable = false)
  private String jobTarget;

  //基础信息
  @Type(type = "StringJsonObject")
  @Column(nullable = false)
  private String basic;

  //教育背景
  @Type(type = "StringJsonObject")
  @Column(nullable = false)
  private String education;

  //实习经历
  @Column(nullable = false,length = 1000)
  private String work;

  //项目经验
  @Column(nullable = false,length = 1000)
  private String practices;

  //获奖荣誉
  @Column(nullable = true,length = 300)
  private String reward;

  //社团经历
  @Column(nullable = true,length = 300)
  private String club;

  //证书
  @Column(nullable = true)
  private String certificate;

  //简历
  @Type(type = "StringJsonObject")
  @Column(nullable = true)
  private String resume;

  //语言
  @Type(type = "StringJsonObject")
  @Column(nullable = false)
  private String language;

  //技能
  @Type(type = "StringJsonObject")
  @Column(nullable = true)
  private String skills;

  //定制拓展预留字段
  @Type(type = "StringJsonObject")
  @Column(nullable = true)
  private String metaData;

  //简历阶段id
  @Column( nullable = false)
  private UUID stageId;

  //简历阶段
  @Column( nullable = false)
  private String stage;

  //状态
  @Column(length = 15, nullable = false)
  private String status;

  //标签
  @OneToMany(cascade= CascadeType.ALL)
  @JoinColumn(name = "applicationId")
  private List<Tags> tags;

  @Column(nullable = false)
  @CreationTimestamp
  @JsonFormat(pattern = "yyyyMMddhhmmss")
  private LocalDateTime applyDate;

  @Column(nullable = true)
  @UpdateTimestamp
  private LocalDateTime updateTime;



  /**
   * @Description .
   * @Author  王迎接【wangyj@minixiao.com】
   * @CreateDate 2017/2/15 15:24
   */


  public Applications() {
  }


  public Applications(UUID id, UUID stuId, UUID recId, String recName, String jobTarget,
                      String basic, String education, String work, String practices, String reward,
                      String club, String certificate, String resume, String language,
                      String skills, String metaData, UUID stageId,String stage, String status, List<Tags> tags,
                      LocalDateTime applyDate, LocalDateTime updateTime) {
    this.id = id;
    this.stuId = stuId;
    this.recId = recId;
    this.recName = recName;
    this.jobTarget = jobTarget;
    this.basic = basic;
    this.education = education;
    this.work = work;
    this.practices = practices;
    this.reward = reward;
    this.club = club;
    this.certificate = certificate;
    this.resume = resume;
    this.language = language;
    this.skills = skills;
    this.metaData = metaData;
    this.stage = stage;
    this.stageId= stageId;
    this.status = status;
    this.tags = tags;
    this.applyDate = applyDate;
    this.updateTime = updateTime;
  }

  /**
   * @Description .
   * @Author  王迎接【wangyj@minixiao.com】
   * @CreateDate 2017/2/16 16:06
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

  public String getJobTarget() {
    return jobTarget;
  }

  public void setJobTarget(String jobTarget) {
    this.jobTarget = jobTarget;
  }

  public String getBasic() {
    return basic;
  }

  public void setBasic(String basic) {
    this.basic = basic;
  }

  public String getEducation() {
    return education;
  }

  public void setEducation(String education) {
    this.education = education;
  }

  public String getWork() {
    return work;
  }

  public void setWork(String work) {
    this.work = work;
  }

  public String getPractices() {
    return practices;
  }

  public void setPratices(String pratices) {
    this.practices = pratices;
  }

  public String getResume() {
    return resume;
  }

  public void setResume(String resume) {
    this.resume = resume;
  }

  public String getMetaData() {
    return metaData;
  }

  public void setMetaData(String metaData) {
    this.metaData = metaData;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public List<Tags> getTags() {
    return tags;
  }

  public void setTags(List<Tags> tags) {
    this.tags = tags;
  }

  public LocalDateTime getApplyDate() {
    return applyDate;
  }

  public void setApplyDate(LocalDateTime applyDate) {
    this.applyDate = applyDate;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
  }

  public void setPractices(String practices) {
    this.practices = practices;
  }

  public String getReward() {
    return reward;
  }

  public void setReward(String reward) {
    this.reward = reward;
  }

  public String getCertificate() {
    return certificate;
  }

  public void setCertificate(String certificate) {
    this.certificate = certificate;
  }

  public String getSkills() {
    return skills;
  }

  public void setSkills(String skills) {
    this.skills = skills;
  }

  public UUID getStageId() {
    return stageId;
  }

  public void setStageId(UUID stageId) {
    this.stageId = stageId;
  }

  public String getStage() {
    return stage;
  }

  public void setStage(String stage) {
    this.stage = stage;
  }

  public String getClub() {
    return club;
  }

  public void setClub(String club) {
    this.club = club;
  }

}
