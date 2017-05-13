package com.minixiao.api.recruiter.entity.jobrequisition;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minixiao.api.recruiter.entity.jobrequisition.embedment.*;
import com.minixiao.api.recruiter.entity.jobrequisition.embedment.Audit;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


/**
 * @Description 职位需求实体类.
 * @Author JiangYh
 * @CreateTime 2017/2/10 18:04
 * @Param
 * @Return
 */
@Entity
@Table(name="tb_jobreq")
public class JobRequistion implements Serializable {
    private static final long serialVersionUID = -7094185289384760338L;
    //主键
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private UUID id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = javax.persistence.CascadeType.DETACH)
    @JoinColumn(name = "rec_id", nullable = false)
    private RecruiterInfo recruiter;

    //职位名称
    @Column(length = 80, nullable = false)
    private String title;

    //职位描述
    @Column(columnDefinition = "text", nullable = false)
    private String description;

    //职位状态
    @Column(length = 10)
    private String status;

    //职位类型
    @Column(nullable = false)
    private String jobType;

    //招聘人数
    @Column(nullable = false)
    private Integer headcount;

    //职位类别
    @Column(nullable = false)
    private String jobCategory;

    //职位级别
    @Column(length = 10, nullable = false)
    private String careerLevel;

    //工作地区
    @Column(nullable = false)
    private String jobArea;

    //部门
    @Embedded
    @AttributeOverrides(
        {
            @AttributeOverride(name = "id", column = @Column(name = "department_id")),
            @AttributeOverride(name = "name", column = @Column(name = "department_name"))
        }
    )
    private Department department;

    //薪资
    @Embedded
    @AttributeOverrides(
        {
            @AttributeOverride(name = "type", column = @Column(name = "sarlary_type")),
            @AttributeOverride(name = "start", column = @Column(name = "sarlary_start")),
            @AttributeOverride(name = "end", column = @Column(name = "sarlary_end"))
        }
    )
    private Sarlary sarlary;

    //网申时间
    @Embedded
    @AttributeOverrides(
        {
            @AttributeOverride(name = "applyBeginDate", column = @Column(name = "apply_begin_date", nullable = false)),
            @AttributeOverride(name = "applyEndDate", column = @Column(name = "apply_end_date", nullable = false))
        }
    )
    private ApplyPeriod applyPeriod;

    //审核相关
    @Embedded
    @AttributeOverrides(
        {
            @AttributeOverride(name = "status", column = @Column(name = "audit_status", nullable = false)),
            @AttributeOverride(name = "time", column = @Column(name = "audit_time")),
            @AttributeOverride(name = "failReason", column = @Column(name = "audit_fail_reason"))
        }
    )
    private Audit audit;

    //投递链接
    @Column(length = 250)
    private String applyUrl;

    //内部编号
    private String innerNo;

    //创建时间
    @CreationTimestamp
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime createdOn;

    //更新时间
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime updatedOn;

    //职位状态数据项
    public static enum Status {
        STATUS_OPEN("open"), STATUS_ON_HOLD("onHold");
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

    //职位类型数据项
    public static enum Type {
        TYPE_FULL_TIME("full_time"), TYPE_PART_TIME("part_time"), INTERSHIP("in");
        private String value;

        /**
         * .
         */
        private Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="jobId")
    private JobRequistionStatistics statistics;


    public JobRequistionStatistics getStatistics() {
        return statistics;
    }

    public void setStatistics(JobRequistionStatistics statistics) {
        this.statistics = statistics;
    }

    public JobRequistion() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RecruiterInfo getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(RecruiterInfo recruiter) {
        this.recruiter = recruiter;
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

    public ApplyPeriod getApplyPeriod() {
        return applyPeriod;
    }

    public void setApplyPeriod(ApplyPeriod applyPeriod) {
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
}
