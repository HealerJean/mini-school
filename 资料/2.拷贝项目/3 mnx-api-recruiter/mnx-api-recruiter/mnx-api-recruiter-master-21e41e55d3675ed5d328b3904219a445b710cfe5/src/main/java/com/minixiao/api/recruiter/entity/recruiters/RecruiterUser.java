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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Description 公司用户 实体类.
 * @Author xiachao
 * @CreateTime 2017/2/9 10:51
 */
@Entity(name = "tb_rec_user")
public class RecruiterUser implements Serializable {

  private static final long serialVersionUID = 1013879370717338103L;
  //用户唯一ID
  @Id
  @GenericGenerator(name = "userIdGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "userIdGenerator")
  private UUID id;

  //公司ID
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "rec_id")
  private RecruiterInfo recruiterInfo;

  //邮箱地址作为用户的登录账号
  @Column(length = 40, nullable = false)
  private String email;

  //用户的真实姓名
  @Column(length = 40, nullable = false)
  private String realName;

  //登录密码
  @Column(length = 100, nullable = false)
  private String pwd;

  //固定电话
  @Column(length = 40)
  private String tel;

  //手机号
  @Column(length = 40)
  private String mobile;

  //所属部门唯一Id
  @Column(length = 50, nullable = false)
  private String deptId;

  //所属部门名称
  @Column(length = 100, nullable = false)
  private String deptName;

  //账号状态(using:正常,disabled:已停用)
  @Column(length = 20, nullable = false)
  private String status;

  //是否为管理员账号
  @Column(columnDefinition = "bool default false")
  private boolean isAdmin;
  //用户创建时间
  @CreationTimestamp
  @JsonFormat(pattern = "yyyyMMddHHmmss")
  private LocalDateTime createdOn;
  //用户修改时间
  @UpdateTimestamp
  @JsonFormat(pattern = "yyyyMMddHHmmss")
  private LocalDateTime updatedOn;

  /**
   * .
   */
  public RecruiterUser() {
  }

  /**
   * .
   */
  public RecruiterUser(UUID id, RecruiterInfo recruiterInfo, String email, String realName,
                       String pwd, String tel, String mobile, String deptId, String deptName,
                       String status, boolean isAdmin, LocalDateTime createdOn,
                       LocalDateTime updatedOn) {
    this.id = id;
    this.recruiterInfo = recruiterInfo;
    this.email = email;
    this.realName = realName;
    this.pwd = pwd;
    this.tel = tel;
    this.mobile = mobile;
    this.deptId = deptId;
    this.deptName = deptName;
    this.status = status;
    this.isAdmin = isAdmin;
    this.createdOn = createdOn;
    this.updatedOn = updatedOn;
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

  public void setRecruiterInfo(RecruiterInfo recruiterInfo) {
    this.recruiterInfo = recruiterInfo;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getDeptId() {
    return deptId;
  }

  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public boolean getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(boolean admin) {
    this.isAdmin = admin;
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
