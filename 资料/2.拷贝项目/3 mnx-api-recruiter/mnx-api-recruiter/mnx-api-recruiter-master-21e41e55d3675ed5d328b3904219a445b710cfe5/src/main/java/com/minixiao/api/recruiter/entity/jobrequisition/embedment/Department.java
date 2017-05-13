package com.minixiao.api.recruiter.entity.jobrequisition.embedment;


import javax.persistence.Embeddable;
import java.util.UUID;

/**
 * @Description 部门.
 * @Author JiangYh
 * @CreateTime 2017/2/13 12:05
 */
@Embeddable
public class Department {

  //部门ID
  private UUID id;

  //部门名称
  private String name;

  public Department(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

  public Department() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
