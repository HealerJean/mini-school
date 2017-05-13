package com.minixiao.api.recruiter.entity.jobrequisition.embedment;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

/**
 * @Description 审核实体类.
 * @Author JiangYh
 * @CreateTime 2017/2/13 12:05
 */
@Embeddable
public class Audit {

  //审核状态
  @Column(length = 10, nullable = false)
  private String status;

  //审核时间
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDateTime time;

  //失败原因
  @Column(length = 100)
  private String failReason;

  //审核状态数据项
  public static enum Status {
    STATUS_WAITING("waiting"), STATUS_PASS("pass"), STATUS_NOT_THROUGH("notThrough");
    private String value;

    /**
     * .
     */
    private Status(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  public Audit(String status, LocalDateTime time, String failReason) {
    this.status = status;
    this.time = time;
    this.failReason = failReason;
  }

  public Audit() {
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(LocalDateTime time) {
    this.time = time;
  }

  public String getFailReason() {
    return failReason;
  }

  public void setFailReason(String failReason) {
    this.failReason = failReason;
  }
}
