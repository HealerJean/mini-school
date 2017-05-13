package com.minixiao.api.recruiter.dto.notification;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.UUID;

public class To {

  private UUID uid;

  private String utype;

  private String nickname;

  public UUID getUid() {
    return uid;
  }

  public void setUid(UUID uid) {
    this.uid = uid;
  }

  public String getUtype() {
    return utype;
  }

  public void setUtype(String utype) {
    this.utype = utype;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  /**
   * .
   */
  public To() {
    // TODO Auto-generated constructor stub
  }


}
