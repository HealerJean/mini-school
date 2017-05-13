package com.minixiao.api.recruiter.dto.candidate;

import java.util.UUID;

/**
 * 候选人批量改变阶段时的候选人信息
 * @Author wangyj.
 * @Date 2017/3/7  17:41.
 */
public class AppStageListDTO {
    private UUID id;

    private UUID jobId;

    private String jobName;

    private UUID stuId;

    private String candidateName;

    private String email;

    public AppStageListDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getStuId() {
        return stuId;
    }

    public void setStuId(UUID stuId) {
        this.stuId = stuId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }
}
