package com.minixiao.api.recruiter.dto.recruiters;

/**
 * @Description 密码修改DTO.
 * @Author xiachao
 * @CreateTime 2017/2/15 18:47
 */

public class RecruiterUserPwdDTO {
  //旧密码
  private String oldPwd;
  //新密码
  private String newPwd;
  //重新输出新密码
  private String newPwdConfirm;


  /**
   * .
   */
  public RecruiterUserPwdDTO() {
  }

  public String getNewPwdConfirm() {
    return newPwdConfirm;
  }

  public void setNewPwdConfirm(String newPwdConfirm) {
    this.newPwdConfirm = newPwdConfirm;
  }

  public String getOldPwd() {
    return oldPwd;
  }

  public void setOldPwd(String oldPwd) {
    this.oldPwd = oldPwd;
  }

  public String getNewPwd() {
    return newPwd;
  }

  public void setNewPwd(String newPwd) {
    this.newPwd = newPwd;
  }
}
