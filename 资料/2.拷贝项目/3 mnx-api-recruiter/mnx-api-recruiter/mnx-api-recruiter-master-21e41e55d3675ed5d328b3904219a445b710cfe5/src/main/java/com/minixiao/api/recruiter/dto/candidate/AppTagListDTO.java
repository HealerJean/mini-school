package com.minixiao.api.recruiter.dto.candidate;

import java.util.UUID;

/**
 * @Author wangyj.
 * @Date 2017/3/7  18:29.
 */
public class AppTagListDTO {

    private UUID id;

    private UUID jobId;

    private String jobName;

    private String candidateName;

    public AppTagListDTO() {
    }

    public AppTagListDTO(UUID id, UUID jobId, String jobName, String candidateName) {
        this.id = id;
        this.jobId = jobId;
        this.jobName = jobName;
        this.candidateName = candidateName;
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
