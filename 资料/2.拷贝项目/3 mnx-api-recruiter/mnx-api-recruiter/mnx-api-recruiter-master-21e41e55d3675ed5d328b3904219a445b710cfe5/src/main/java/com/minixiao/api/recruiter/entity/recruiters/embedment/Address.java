package com.minixiao.api.recruiter.entity.recruiters.embedment;

import javax.persistence.Embeddable;

/**
 * @Description 公司地址 做为嵌入类使用.
 * @Author xiachao
 * @CreateTime 2017/2/13 12:05
 */

@Embeddable
public class Address {
  //公司所在省市
  private String city;

  //公司详细地址
  private String street;

  /**
   *  .
   */

  public Address() {
  }
  /**
   * .
   */

  public Address(String city, String street) {
    this.city = city;
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }
}
