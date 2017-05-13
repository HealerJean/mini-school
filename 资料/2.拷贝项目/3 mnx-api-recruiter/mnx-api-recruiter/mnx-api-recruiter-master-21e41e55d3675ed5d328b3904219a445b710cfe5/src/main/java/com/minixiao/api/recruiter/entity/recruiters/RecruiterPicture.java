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
 * @Description 公司图片实体类.
 * @Author xiachao
 * @CreateTime 2017/2/22 12:53
 */

@Entity
@Table(name = "tb_rec_info_picture")
public class RecruiterPicture implements Serializable {

  private static final long serialVersionUID = 475762096136241217L;

  //公司图片唯一Id
  @Id
  @GenericGenerator(name = "pictureIdGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "pictureIdGenerator")
  private UUID id;

  //公司ID
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "rec_id")
  private RecruiterInfo recruiterInfo;

  //公司图片地址
  @Column(nullable = false)
  private String url;

  //公司图片次序
  @Column
  private Integer pictureOrder;

  //创建时间
  @CreationTimestamp
  @JsonFormat(pattern = "yyyyMMddHHmmss")
  private LocalDateTime createdOn;

  //修改时间
  @UpdateTimestamp
  @JsonFormat(pattern = "yyyyMMddHHmmss")
  private LocalDateTime updatedOn;

  /**
   * .
   */
  public RecruiterPicture() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public RecruiterInfo getRecruiterInfo() {
    return recruiterInfo;
  }

  public void setRecruiterInfo(RecruiterInfo recruiterInfo) {
    this.recruiterInfo = recruiterInfo;
  }

  public Integer getPictureOrder() {
    return pictureOrder;
  }

  public void setPictureOrder(Integer pictureOrder) {
    this.pictureOrder = pictureOrder;
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
