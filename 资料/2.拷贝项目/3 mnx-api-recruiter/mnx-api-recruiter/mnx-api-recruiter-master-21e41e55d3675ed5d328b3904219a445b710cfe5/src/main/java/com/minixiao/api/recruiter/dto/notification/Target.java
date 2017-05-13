package com.minixiao.api.recruiter.dto.notification;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.UUID;

public class Target {

  private UUID oid;

  private String otype;

  public UUID getOid() {
    return oid;
  }

  public void setOid(UUID oid) {
    this.oid = oid;
  }

  public String getOtype() {
    return otype;
  }

  public void setOtype(String otype) {
    this.otype = otype;
  }



  /**
   * .
   */
  public Target() {
    // TODO Auto-generated constructor stub
  }
}
