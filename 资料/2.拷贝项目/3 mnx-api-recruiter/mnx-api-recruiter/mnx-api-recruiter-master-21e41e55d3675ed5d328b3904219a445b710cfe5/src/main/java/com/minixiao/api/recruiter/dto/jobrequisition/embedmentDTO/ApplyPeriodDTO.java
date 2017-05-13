package com.minixiao.api.recruiter.dto.jobrequisition.embedmentDTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Embeddable;
import java.time.LocalDate;

/**
 * @Description 网申日期.
 * @Author JiangYh
 * @CreateTime 2017/2/13 12:05
 */
@Embeddable
public class ApplyPeriodDTO {

  //网申开始时间
  private String applyBeginDate;

  //网申结束时间
  private String applyEndDate;

  public ApplyPeriodDTO() {
  }

  public String getApplyBeginDate() {
    return applyBeginDate;
  }

  public void setApplyBeginDate(String applyBeginDate) {
    this.applyBeginDate = applyBeginDate;
  }

  public String getApplyEndDate() {
    return applyEndDate;
  }

  public void setApplyEndDate(String applyEndDate) {
    this.applyEndDate = applyEndDate;
  }

}
