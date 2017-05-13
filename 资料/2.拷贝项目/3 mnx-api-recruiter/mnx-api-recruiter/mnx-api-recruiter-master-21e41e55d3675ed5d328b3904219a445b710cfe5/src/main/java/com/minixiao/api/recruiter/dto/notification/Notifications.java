package com.minixiao.api.recruiter.dto.notification;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author WangYingjie.
 */
public class Notifications {

  private UUID id;

  private Sender sender;

  private To to;

  private Target target;

  private String action;

  private String type;

  private String content;

  private String var;

  private Map<String, String> vars;

  private byte status;

  private Date sendTime;

  private String tel;

  private String email;

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Sender getSender() {
    return sender;
  }

  public void setSender(Sender sender) {
    this.sender = sender;
  }

  public To getTo() {
    return to;
  }

  public void setTo(To to) {
    this.to = to;
  }

  public Target getTarget() {
    return target;
  }

  public void setTarget(Target target) {
    this.target = target;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public byte getStatus() {
    return status;
  }

  public void setStatus(byte status) {
    this.status = status;
  }

  public Date getSendTime() {
    return sendTime;
  }

  public void setSendTime(Date sendTime) {
    this.sendTime = sendTime;
  }

  /**
   * .
   */
  public Notifications() {

  }


  public String getVar() {
    return var;
  }

  public void setVar(String var) {
    this.var = var;
  }

  public Map<String, String> getVars() {
    return vars;
  }

  public void setVars(Map<String, String> vars) {
    this.vars = vars;
  }

}
