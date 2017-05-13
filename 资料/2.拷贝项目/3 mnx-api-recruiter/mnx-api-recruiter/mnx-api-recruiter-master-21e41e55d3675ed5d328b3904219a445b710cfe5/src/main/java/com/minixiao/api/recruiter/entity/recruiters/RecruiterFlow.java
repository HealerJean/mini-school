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
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Description 公司招聘流程实体类 .
 * @Author xiachao
 * @CreateTime 2017/2/10 18:04
 * @Param
 * @Return
 */
@Entity(name = "tb_rec_flow")
public class RecruiterFlow implements Serializable {
  private static final long serialVersionUID = 275939580869235990L;

  //流程唯一Id
  @Id
  @GenericGenerator(name = "flowIdGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "flowIdGenerator")
  private UUID id;
  //公司ID
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "rec_id")
  private RecruiterInfo recruiterInfo;

  //招聘流程名称
  @Column(length = 30, nullable = false)
  private String name;

  //流程类型(筛选，笔试，面试)
  @Column(length = 32, nullable = false)
  private String type;

  //流程次序
  @Column(nullable = false)
  private Integer flowOrder;

  //在该流程的申请人个数
  @Column
  private Integer candidateCount;

  //流程创建时间
  @CreationTimestamp
  @JsonFormat(pattern = "yyyyMMddHHmmss")
  private LocalDateTime createdOn;
  //流程修改时间
  @UpdateTimestamp
  @JsonFormat(pattern = "yyyyMMddHHmmss")
  private LocalDateTime updatedOn;

  //流程类型数据项
  public static enum Type {
    WRITTEN_EAXM("笔试类型"), FACETOFACE_EXAM("面试类型"), SCREEN("筛选类型");
    public String value;

    /**
     * .
     *
     * @param value value
     */
    private Type(String value) {
      this.value = value;
    }
  }

  /**
   * .
   */
  public RecruiterFlow() {
  }


  /**
   * .
   */

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public RecruiterInfo getRecruiterInfo() {
    return recruiterInfo;
  }

  public void setRecruiterInfo(RecruiterInfo recruiterInfo) {
    this.recruiterInfo = recruiterInfo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getFlowOrder() {
    return flowOrder;
  }

  public void setFlowOrder(Integer flowOrder) {
    this.flowOrder = flowOrder;
  }

  public Integer getCandidateCount() {
    return candidateCount;
  }

  public void setCandidateCount(Integer candidateCount) {
    this.candidateCount = candidateCount;
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


