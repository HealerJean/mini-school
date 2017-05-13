package com.minixiao.api.recruiter.dto.jobrequisition;

import java.util.UUID;

/**
 * 职位统计DTO
 * @Author wangyj.
 * @Date 2017/3/11  17:17.
 */
public class JobStatDTO {
  private UUID jobId;
  
  private UUID recId;
  
  private String fromType;
  
  private String toType;

  /**
   * @Description .
   * @Author  wangyj
   * @CreateDate 2017/3/11 17:23
   */
  public JobStatDTO() {
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

  public String getFromType() {
    return fromType;
  }

  public void setFromType(String fromType) {
    this.fromType = fromType;
  }

  public String getToType() {
    return toType;
  }

  public void setToType(String toType) {
    this.toType = toType;
  }
}
