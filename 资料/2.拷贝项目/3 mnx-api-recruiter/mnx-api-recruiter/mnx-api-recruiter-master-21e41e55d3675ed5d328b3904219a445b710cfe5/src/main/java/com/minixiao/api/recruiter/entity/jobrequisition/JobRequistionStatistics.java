package com.minixiao.api.recruiter.entity.jobrequisition;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.rmi.server.UID;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @Description 职位需求统计实体类.
 * @Author JiangYh
 * @CreateTime 2017/2/10 18:04
 */
@Entity
@Table(name="tb_jobreq_stat")
public class JobRequistionStatistics {
  //主键
  @Id
  @GenericGenerator(name = "idGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "idGenerator")
  private UUID jobId;

  //招聘公司
  @JoinColumn(nullable = false)
  private UUID recId;

  //应聘总人数
  @Column(length = 8, nullable = false)
  private int totalCount;

  //初筛阶段人数
  @Column(length = 6, nullable = false)
  private int screenCount;

  //面试中阶段人数
  @Column(length = 6, nullable = false)
  private int interviewCount;

  //笔试阶段人数
  @Column(length = 6, nullable = false)
  private int writtenCount;

  //当前录用通过阶段人数
  @Column(length = 6, nullable = false)
  private int offerCount;

  //更新时间
  @JsonFormat(pattern = "yyyyMMdd")
  @UpdateTimestamp
  private LocalDate updatedOn;

  public UUID getJobId() {
    return jobId;
  }

  public void setJobId(UUID jobId) {
    this.jobId = jobId;
  }

  public UUID getRecId() {
    return recId;
  }

  public void setRecId(UUID recId) {
    this.recId = recId;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public int getScreenCount() {
    return screenCount;
  }

  public void setScreenCount(int screenCount) {
    this.screenCount = screenCount;
  }

  public int getInterviewCount() {
    return interviewCount;
  }

  public void setInterviewCount(int interviewCount) {
    this.interviewCount = interviewCount;
  }

  public int getWrittenCount() {
    return writtenCount;
  }

  public void setWrittenCount(int writtenCount) {
    this.writtenCount = writtenCount;
  }

  public int getOfferCount() {
    return offerCount;
  }

  public void setOfferCount(int offerCount) {
    this.offerCount = offerCount;
  }

  public LocalDate getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(LocalDate updatedOn) {
    this.updatedOn = updatedOn;
  }

  public JobRequistionStatistics() {
  }
}
