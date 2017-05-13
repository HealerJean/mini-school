package com.minixiao.api.recruiter.entity.jobrequisition.embedment;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Embeddable;
import java.time.LocalDate;

/**
 * @Description 网申日期.
 * @Author JiangYh
 * @CreateTime 2017/2/13 12:05
 */
@Embeddable
public class ApplyPeriod {

  //网申开始时间
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate applyBeginDate;

  //网申结束时间
  @JsonFormat(pattern = "yyyyMMdd")
  private LocalDate applyEndDate;

  public ApplyPeriod(LocalDate applyBeginDate, LocalDate applyEndDate) {
    this.applyBeginDate = applyBeginDate;
    this.applyEndDate = applyEndDate;
  }

  public ApplyPeriod() {
  }

  public LocalDate getApplyBeginDate() {
    return applyBeginDate;
  }

  public void setApplyBeginDate(LocalDate applyBeginDate) {
    this.applyBeginDate = applyBeginDate;
  }

  public LocalDate getApplyEndDate() {
    return applyEndDate;
  }

  public void setApplyEndDate(LocalDate applyEndDate) {
    this.applyEndDate = applyEndDate;
  }
}
