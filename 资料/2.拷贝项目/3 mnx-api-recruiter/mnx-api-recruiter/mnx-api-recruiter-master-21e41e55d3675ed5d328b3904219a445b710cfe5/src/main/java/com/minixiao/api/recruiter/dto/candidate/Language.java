package com.minixiao.api.recruiter.dto.candidate;

/**
 * @Author 王迎接 【wangyj@minixiao.com】.
 * @Date 2017/2/17  13:45.
 */
public class Language
{
  private String type;
  private String level;

  /**
   * @Description .
   * @Author  王迎接【wangyj@minixiao.com】
   * @CreateDate 2017/2/17 13:46
   */
  public Language() {
  }

  /**
   * @Description .
   * @Author  王迎接【wangyj@minixiao.com】
   * @CreateDate 2017/2/17 13:46
   */

  public Language(String type, String level) {
    this.type = type;
    this.level = level;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }
}
