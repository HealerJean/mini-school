package com.minixiao.api.recruiter.dto.candidate;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.minixiao.api.recruiter.entity.candidate.Tags;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by WangYingjie on 2017/2/15.
 */
public class ApplicationDTO {

  private UUID id;

  private UUID stuId;

  private UUID recId;

  private String recName;

  private JobTarget[] jobTarget;

  private Basic basic;

  private Education[] education;

  private String work;

  private String practices;

  private Resume resume;

  private String club;

  private String reward;

  private String certificate;

  private Object metaData;

  private Language[] language;

  private Skills[] skills;

  private UUID stageId;

  private String stage;

  private String status;

  private List<Tags> tags;

 // @JsonFormat(pattern = "yyyyMMddhhmmss")
  private LocalDateTime applyDate;
  /**
   * @Description .
   * @Author  王迎接【wangyj@minixiao.com】
   * @CreateDate 2017/2/15 15:16
   */
  public ApplicationDTO() {
  }

  public ApplicationDTO(UUID id, UUID stuId, UUID recId, String recName, JobTarget[] jobTarget, Basic basic, Education[] education, String work, String practices, Resume resume, String club, String reward, String certificate, Object metaData, Language[] language, Skills[] skills, UUID stageId, String stage, String status, List<Tags> tags, LocalDateTime applyDate) {
    this.id = id;
    this.stuId = stuId;
    this.recId = recId;
    this.recName = recName;
    this.jobTarget = jobTarget;
    this.basic = basic;
    this.education = education;
    this.work = work;
    this.practices = practices;
    this.resume = resume;
    this.club = club;
    this.reward = reward;
    this.certificate = certificate;
    this.metaData = metaData;
    this.language = language;
    this.skills = skills;
    this.stageId = stageId;
    this.stage = stage;
    this.status = status;
    this.tags = tags;
    this.applyDate = applyDate;
  }

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

  public JobTarget[] getJobTarget() {
    return jobTarget;
  }

  public void setJobTarget(JobTarget[] jobTarget) {
    this.jobTarget = jobTarget;
  }

  public Basic getBasic() {
    return basic;
  }

  public void setBasic(Basic basic) {
    this.basic = basic;
  }

  public Education[] getEducation() {
    return education;
  }

  public void setEducation(Education[] education) {
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

  public void setPractices(String practices) {
    this.practices = practices;
  }

  public Resume getResume() {
    return resume;
  }

  public void setResume(Resume resume) {
    this.resume = resume;
  }

  public String getClub() {
    return club;
  }

  public void setClub(String club) {
    this.club = club;
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

  public Object getMetaData() {
    return metaData;
  }

  public void setMetaData(Object metaData) {
    this.metaData = metaData;
  }

  public Language[] getLanguage() {
    return language;
  }

  public void setLanguage(Language[] language) {
    this.language = language;
  }

  public Skills[] getSkills() {
    return skills;
  }

  public void setSkills(Skills[] skills) {
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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
}
