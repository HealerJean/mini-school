package com.minixiao.api.recruiter.entity.candidate;

import java.util.UUID;

public class CandidateInfo {
  private UUID candidateId;

  private String candidateName;

  private UUID jobId;

  private String jobName;

  /**
   * 构造函数.
   */
  public CandidateInfo() {
  }


  public UUID getCandidateId() {
    return candidateId;
  }

  public void setCandidateId(UUID candidateId) {
    this.candidateId = candidateId;
  }

  public String getCandidateName() {
    return candidateName;
  }

  public void setCandidateName(String candidateName) {
    this.candidateName = candidateName;
  }

  public UUID getJobId() {
    return jobId;
  }

  public void setJobId(UUID jobId) {
    this.jobId = jobId;
  }

  public String getJobName() {
    return jobName;
  }

  public void setJobName(String jobName) {
    this.jobName = jobName;
  }
}
