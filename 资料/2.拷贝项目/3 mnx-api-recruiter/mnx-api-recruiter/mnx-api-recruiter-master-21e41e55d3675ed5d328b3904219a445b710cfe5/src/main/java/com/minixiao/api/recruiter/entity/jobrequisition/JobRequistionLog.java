package com.minixiao.api.recruiter.entity.jobrequisition;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Description 职位需求日志实体类.
 * @Author JiangYh
 * @CreateTime 2017/2/10 18:04
 */
@Entity
@Table(name="tb_jobreq_log")
public class JobRequistionLog {

  //主键
  @Id
  @GenericGenerator(name = "idGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "idGenerator")
  private UUID id;

  //操作人ID
  @Column(nullable = false)
  private UUID optUid;

  //操作人名称
  @Column(length = 20)
  private String optUname;

  //职位Id
  private UUID jobId;

  //招聘公司Id
  private UUID recId;

  //操作类型
  @Column(length = 10, nullable = false)
  private String optType;

  //操作描述
  @Column(length = 200)
  private String optDesc;

  @JsonFormat(pattern = "yyyyMMddHHmmss")
  @CreationTimestamp
  private LocalDateTime createdOn;

  public JobRequistionLog(UUID id, UUID optUid, String optUname, UUID jobId, UUID recId, String optType, String optDesc, LocalDateTime createdOn) {
    this.id = id;
    this.optUid = optUid;
    this.optUname = optUname;
    this.jobId = jobId;
    this.recId = recId;
    this.optType = optType;
    this.optDesc = optDesc;
    this.createdOn = createdOn;
  }

  public JobRequistionLog() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getOptUid() {
    return optUid;
  }

  public void setOptUid(UUID optUid) {
    this.optUid = optUid;
  }

  public String getOptUname() {
    return optUname;
  }

  public void setOptUname(String optUname) {
    this.optUname = optUname;
  }

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

  public String getOptType() {
    return optType;
  }

  public void setOptType(String optType) {
    this.optType = optType;
  }

  public String getOptDesc() {
    return optDesc;
  }

  public void setOptDesc(String optDesc) {
    this.optDesc = optDesc;
  }

  public LocalDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(LocalDateTime createdOn) {
    this.createdOn = createdOn;
  }

  //职位操作类型数据项
  public static enum OptType {
    OptType_CREATE("create"), OptType_Modify("update"), OptType_ONLINE("online"), OptType_OFFLINE("offline");
    private String value;

    /**
     * .
     */
    private OptType(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }
}
