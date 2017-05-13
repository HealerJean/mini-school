package com.minixiao.api.recruiter.entity.recruiters.embedment;

import javax.persistence.Embeddable;

/**
 * @Description 公司行业 做为嵌入类使用.
 * @Author xiachao
 * @CreateTime 2017/2/13 12:05
 */
@Embeddable
public class Industry {
  //公司一级行业
  private String primary;
  //公司二级行业
  private String slave;

  /**
   * .
   */
  public Industry() {
  }
  /**
   * .
   */

  public Industry(String primary, String slave) {
    this.primary = primary;
    this.slave = slave;
  }

  public String getPrimary() {
    return primary;
  }

  public void setPrimary(String primary) {
    this.primary = primary;
  }

  public String getSlave() {
    return slave;
  }

  public void setSlave(String slave) {
    this.slave = slave;
  }
}
