package com.minixiao.api.recruiter.dto.candidate;

import java.util.Date;

/**
 * Created by WangYingjie on 2017/2/13.
 */
public class Basic {
  private String name;

  private String gender;

  private String email;

  private String mobile;

  private String birthday;

  private String province;

  private String city;

  private String political;

  /**
   * 空构造函数.
   */
  public Basic() {
  }

  /**
   * 默认构造函数.
   */
  public Basic(String name, String gender, String email, String mobile, String birthday,
               String province, String city, String political) {
    this.name = name;
    this.gender = gender;
    this.email = email;
    this.mobile = mobile;
    this.birthday = birthday;
    this.province = province;
    this.city = city;
    this.political = political;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPolitical() {
    return political;
  }

  public void setPolitical(String political) {
    this.political = political;
  }
}
