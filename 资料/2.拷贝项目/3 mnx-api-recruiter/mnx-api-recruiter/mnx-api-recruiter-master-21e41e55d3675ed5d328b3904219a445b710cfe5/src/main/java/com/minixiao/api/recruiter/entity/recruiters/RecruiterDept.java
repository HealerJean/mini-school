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
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Description 公司部门实体类.
 * @Author xiachao
 * @CreateTime 2017/2/9 14:02
 */

@Entity
@Table(name = "tb_rec_dept")
public class RecruiterDept implements Serializable {
    private static final long serialVersionUID = 324189933206420691L;
    //该记录唯一ID
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private UUID id;
    //部门唯一Id
    @Column(nullable = false, length = 50)
    private String deptId;
    //公司ID
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rec_id")
    private RecruiterInfo recruiterInfo;
    //部门名称
    @Column(length = 100, nullable = false)
    private String name;
    //流程创建时间
    @CreationTimestamp
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime createdOn;
    //流程修改时间
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime updatedOn;

    /**
     * .
     */
    public RecruiterDept() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RecruiterInfo getRecruiterInfo() {
        return recruiterInfo;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
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
