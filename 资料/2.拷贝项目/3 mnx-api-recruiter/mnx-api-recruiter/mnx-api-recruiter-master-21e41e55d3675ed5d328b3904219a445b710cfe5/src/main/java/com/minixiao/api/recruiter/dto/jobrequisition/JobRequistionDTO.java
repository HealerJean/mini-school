package com.minixiao.api.recruiter.dto.jobrequisition;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.minixiao.api.recruiter.dto.jobrequisition.embedmentDTO.ApplyPeriodDTO;
import com.minixiao.api.recruiter.entity.jobrequisition.embedment.Audit;
import com.minixiao.api.recruiter.entity.jobrequisition.embedment.Department;
import com.minixiao.api.recruiter.entity.jobrequisition.embedment.Sarlary;
import java.time.LocalDateTime;
import java.util.UUID;


/**
 * @Description 职位需求DTO实体类.
 * @Author JiangYh
 * @CreateTime 2017/2/10 18:04
 * @Param
 * @Return
 */
public class JobRequistionDTO {

    private UUID id;

    private String title;

    private String description;

    private String status;

    private String jobType;

    private Integer headcount;

    private String jobCategory;

    private String careerLevel;

    private String jobArea;

    private Department department;

    private Sarlary sarlary;

    private ApplyPeriodDTO applyPeriod;

    private Audit audit;

    private String applyUrl;

    private String innerNo;

    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime createdOn;

    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime updatedOn;

    private int applicationNum;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public Integer getHeadcount() {
        return headcount;
    }

    public void setHeadcount(Integer headcount) {
        this.headcount = headcount;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public String getCareerLevel() {
        return careerLevel;
    }

    public void setCareerLevel(String careerLevel) {
        this.careerLevel = careerLevel;
    }

    public String getJobArea() {
        return jobArea;
    }

    public void setJobArea(String jobArea) {
        this.jobArea = jobArea;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Sarlary getSarlary() {
        return sarlary;
    }

    public void setSarlary(Sarlary sarlary) {
        this.sarlary = sarlary;
    }

    public ApplyPeriodDTO getApplyPeriod() {
        return applyPeriod;
    }

    public void setApplyPeriod(ApplyPeriodDTO applyPeriod) {
        this.applyPeriod = applyPeriod;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public String getApplyUrl() {
        return applyUrl;
    }

    public void setApplyUrl(String applyUrl) {
        this.applyUrl = applyUrl;
    }

    public String getInnerNo() {
        return innerNo;
    }

    public void setInnerNo(String innerNo) {
        this.innerNo = innerNo;
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

    public int getApplicationNum() {
        return applicationNum;
    }

    public void setApplicationNum(int applicationNum) {
        this.applicationNum = applicationNum;
    }

    public JobRequistionDTO() {
    }
}
