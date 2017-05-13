package com.minixiao.api.recruiter.entity.candidate;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Author 王迎接 【wangyj@minixiao.com】.
 * @Date 2017/2/22  11:11.
 */
@Entity
@Table(name = "tb_candidate_tags")
public class Tags {

  @Id
  @GenericGenerator(name = "idGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "idGenerator")
  private UUID id;

  @Column(nullable = false)
  private UUID applicationId;

  @Column(nullable = false)
  private String tag;

  @Column(nullable = false)
  private UUID optId;

  @Column(nullable = false)
  private String optName;

  @Column(nullable = false)
  @CreationTimestamp
  //@JsonFormat(pattern = "yyyyMMddhhmmss")
  private LocalDateTime creatDate;


  public Tags() {
  }

  public Tags(UUID id, UUID applicationId, String tag, UUID optId, String optName, LocalDateTime creatDate) {
    this.id = id;
    this.applicationId = applicationId;
    this.tag = tag;
    this.optId = optId;
    this.optName = optName;
    this.creatDate = creatDate;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }


  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public UUID getOptId() {
    return optId;
  }

  public void setOptId(UUID optId) {
    this.optId = optId;
  }

  public String getOptName() {
    return optName;
  }

  public void setOptName(String optName) {
    this.optName = optName;
  }

  public LocalDateTime getCreatDate() {
    return creatDate;
  }

  public void setCreatDate(LocalDateTime creatDate) {
    this.creatDate = creatDate;
  }

  public UUID getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(UUID applicationId) {
    this.applicationId = applicationId;
  }
}
