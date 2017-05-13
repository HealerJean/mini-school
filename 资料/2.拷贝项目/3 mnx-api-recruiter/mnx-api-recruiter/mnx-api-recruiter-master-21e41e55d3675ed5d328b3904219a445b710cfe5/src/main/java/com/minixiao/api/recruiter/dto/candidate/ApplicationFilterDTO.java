package com.minixiao.api.recruiter.dto.candidate;

import java.util.Date;
import java.util.UUID;

/**
 * @Author 王迎接 【wangyj@minixiao.com】.
 * @Date 2017/2/17  14:14.
 */
public class ApplicationFilterDTO {
  //企业Id
  private UUID rid;

  //职位名称
  private String pst;

  //最高学历
  private String dgr;

  //毕业学校(英文状态逗号切分)
  private String scl;

  //专业名称(英文状态逗号切分)
  private String mj;

  //阶段
  private UUID stg;

  //处理状态
  private String sta;

  //简历标签(英文状态逗号切分)
  private String lb;

  //性别
  private String gd;

  //聘用部门
  private String dp;

  //专业排名
  private String rank;

  //英语等级(英文状态逗号切分)
  private String egL;

  //获奖荣誉
  private String rd;

  //附件简历
  private String att;

  //候选人姓名
  private String nm;

  //手机号码
  private String mb;

  //家庭所在地(英文状态逗号切分)
  private String ct;

  //出生年份大于
  private String brF;

  //出生年份小于
  private String brT;

  //毕业年份大于
  private String gdF;

  //毕业年份小于
  private String gdT;

  //投递时间大于
  private String dlF;

  //投递时间小于
  private String dlT;

  //分页参数 页码
  private Integer no;

  //分页参数 每页数量
  private Integer sz;

  //排序参数
  private String st;

/**
 * @Description .
 * @Author  王迎接【wangyj@minixiao.com】
 * @CreateDate 2017/2/21 13:42
 */
  public ApplicationFilterDTO() {
  }

  public ApplicationFilterDTO(UUID rid, String pst, String dgr, String scl, String mj, UUID stg, String sta, String lb, String gd, String dp, String rank, String egL, String rd, String att, String nm, String mb, String ct, String brF, String brT, String gdF, String gdT, String dlF, String dlT, Integer no, Integer sz, String st) {
    this.rid = rid;
    this.pst = pst;
    this.dgr = dgr;
    this.scl = scl;
    this.mj = mj;
    this.stg = stg;
    this.sta = sta;
    this.lb = lb;
    this.gd = gd;
    this.dp = dp;
    this.rank = rank;
    this.egL = egL;
    this.rd = rd;
    this.att = att;
    this.nm = nm;
    this.mb = mb;
    this.ct = ct;
    this.brF = brF;
    this.brT = brT;
    this.gdF = gdF;
    this.gdT = gdT;
    this.dlF = dlF;
    this.dlT = dlT;
    this.no = no;
    this.sz = sz;
    this.st = st;
  }

  /**
   * @Description .
   * @Author  王迎接【wangyj@minixiao.com】
   * @CreateDate 2017/2/21 13:42
   */


  public UUID getRid() {
    return rid;
  }

  public void setRid(UUID rid) {
    this.rid = rid;
  }

  public String getPst() {
    return pst;
  }

  public void setPst(String pst) {
    this.pst = pst;
  }

  public String getDgr() {
    return dgr;
  }

  public void setDgr(String dgr) {
    this.dgr = dgr;
  }

  public String getScl() {
    return scl;
  }

  public void setScl(String scl) {
    this.scl = scl;
  }

  public String getMj() {
    return mj;
  }

  public void setMj(String mj) {
    this.mj = mj;
  }

  public String getSta() {
    return sta;
  }

  public void setSta(String sta) {
    this.sta = sta;
  }

  public String getLb() {
    return lb;
  }

  public void setLb(String lb) {
    this.lb = lb;
  }

  public String getGd() {
    return gd;
  }

  public void setGd(String gd) {
    this.gd = gd;
  }

  public String getDp() {
    return dp;
  }

  public void setDp(String dp) {
    this.dp = dp;
  }

  public String getRank() {
    return rank;
  }

  public void setRank(String rank) {
    this.rank = rank;
  }

  public String getEgL() {
    return egL;
  }

  public void setEgL(String egL) {
    this.egL = egL;
  }

  public String getRd() {
    return rd;
  }

  public void setRd(String rd) {
    this.rd = rd;
  }

  public String getAtt() {
    return att;
  }

  public void setAtt(String att) {
    this.att = att;
  }

  public String getNm() {
    return nm;
  }

  public void setNm(String nm) {
    this.nm = nm;
  }

  public String getMb() {
    return mb;
  }

  public void setMb(String mb) {
    this.mb = mb;
  }

  public String getCt() {
    return ct;
  }

  public void setCt(String ct) {
    this.ct = ct;
  }

  public String getBrF() {
    return brF;
  }

  public void setBrF(String brF) {
    this.brF = brF;
  }

  public String getBrT() {
    return brT;
  }

  public void setBrT(String brT) {
    this.brT = brT;
  }

  public String getGdF() {
    return gdF;
  }

  public void setGdF(String gdF) {
    this.gdF = gdF;
  }

  public String getGdT() {
    return gdT;
  }

  public void setGdT(String gdT) {
    this.gdT = gdT;
  }

  public String getDlF() {
    return dlF;
  }

  public void setDlF(String dlF) {
    this.dlF = dlF;
  }

  public String getDlT() {
    return dlT;
  }

  public void setDlT(String dlT) {
    this.dlT = dlT;
  }

  public Integer getNo() {
    return no;
  }

  public void setNo(Integer no) {
    this.no = no;
  }

  public Integer getSz() {
    return sz;
  }

  public void setSz(Integer sz) {
    this.sz = sz;
  }

  public String getSt() {
    return st;
  }

  public void setSt(String st) {
    this.st = st;
  }

  public UUID getStg() {
    return stg;
  }

  public void setStg(UUID stg) {
    this.stg = stg;
  }
}
