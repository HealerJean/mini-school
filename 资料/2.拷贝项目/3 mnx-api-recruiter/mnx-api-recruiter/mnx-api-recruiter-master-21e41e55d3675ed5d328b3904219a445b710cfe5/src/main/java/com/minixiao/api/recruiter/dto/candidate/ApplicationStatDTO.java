package com.minixiao.api.recruiter.dto.candidate;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

/**
 * @Author wangyj.
 * @Date 2017/3/3  17:42.
 */
public class ApplicationStatDTO {
  private Integer jobCout;

  private Integer applicationCount;

  private Integer dealedCount;

  private Integer offerCount;

  private List<Object> sevenDayStat;

  public ApplicationStatDTO() {
  }

  public ApplicationStatDTO(Integer jobCout, Integer applicationCount, Integer dealedCount, Integer offerCount, List<Object> sevenDayStat) {
    this.jobCout = jobCout;
    this.applicationCount = applicationCount;
    this.dealedCount = dealedCount;
    this.offerCount = offerCount;
    this.sevenDayStat = sevenDayStat;
  }

  public Integer getJobCout() {
    return jobCout;
  }

  public void setJobCout(Integer jobCout) {
    this.jobCout = jobCout;
  }

  public Integer getApplicationCount() {
    return applicationCount;
  }

  public void setApplicationCount(Integer applicationCount) {
    this.applicationCount = applicationCount;
  }

  public Integer getDealedCount() {
    return dealedCount;
  }

  public void setDealedCount(Integer dealedCount) {
    this.dealedCount = dealedCount;
  }

  public Integer getOfferCount() {
    return offerCount;
  }

  public void setOfferCount(Integer offerCount) {
    this.offerCount = offerCount;
  }

  public List<Object> getSevenDayStat() {
    return sevenDayStat;
  }

  public void setSevenDayStat(List<Object> sevenDayStat) {
    this.sevenDayStat = sevenDayStat;
  }
}
