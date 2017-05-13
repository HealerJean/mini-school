package com.minixiao.api.recruiter.entity.jobrequisition.embedment;


import javax.persistence.Embeddable;

/**
 * @Description 薪资.
 * @Author JiangYh
 * @CreateTime 2017/2/13 12:05
 */
@Embeddable
public class Sarlary {

  //薪资类型
  private String type;

  //薪资区间起点
  private int start;

  //薪资区间结点
  private int end;

  //薪酬类型数据项
  public static enum Type {
    TYPE_YEARLY("yearly"), TYPE_MONTHLY("monthly"), TYPE_DAILY("daily"),
    TYPE_HOURLY("hourly");
    private String value;

    /**
     * .
     */
    private Type(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  public Sarlary(String type, int start, int end) {
    this.type = type;
    this.start = start;
    this.end = end;
  }

  public Sarlary() {
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getStart() {
    return start;
  }

  public void setStart(int start) {
    this.start = start;
  }

  public int getEnd() {
    return end;
  }

  public void setEnd(int end) {
    this.end = end;
  }
}
