package com.minixiao.api.recruiter.entity.candidate;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_candidate_stat")
public class Statistics implements Serializable {

  private static final long SERIVALVERSIONUID = 876280513342148585L;

  //数据统计的唯一标识
  @Id
  @GenericGenerator(name = "idGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "idGenerator")
  private UUID id;

  //当日的申请数
  @Column(nullable = false)
  private int applyCount;

  //当日申请的总职位数
  @Column(nullable = false)
  private int perJobCount;

  //当日申请的总公司数
  @Column(nullable = false)
  private int perRecCount;

  //统计日期
  @Column(nullable = false)
  @CreationTimestamp
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate statDate;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public int getApplyCount() {
    return applyCount;
  }

  public void setApplyCount(int applyCount) {
    this.applyCount = applyCount;
  }

  public int getPerJobCount() {
    return perJobCount;
  }

  public void setPerJobCount(int perJobCount) {
    this.perJobCount = perJobCount;
  }

  public int getPerRecCount() {
    return perRecCount;
  }

  public void setPerRecCount(int perRecCount) {
    this.perRecCount = perRecCount;
  }

  public LocalDate getStatDate() {
    return statDate;
  }

  public void setStatDate(LocalDate statDate) {
    this.statDate = statDate;
  }

  /**
   * 构造函数.
   */
  public Statistics() {
  }

  /**
   * 构造函数.
   *
   * @param id          数据统计的唯一标识
   * @param applyCount  当日的申请数
   * @param perJobCount 当日申请的总职位数
   * @param perRecCount 申请的总公司数
   * @param statDate    统计日期
   */
  public Statistics(UUID id, int applyCount, int perJobCount, int perRecCount, LocalDate statDate) {
    this.id = id;
    this.applyCount = applyCount;
    this.perJobCount = perJobCount;
    this.perRecCount = perRecCount;
    this.statDate = statDate;
  }
}
