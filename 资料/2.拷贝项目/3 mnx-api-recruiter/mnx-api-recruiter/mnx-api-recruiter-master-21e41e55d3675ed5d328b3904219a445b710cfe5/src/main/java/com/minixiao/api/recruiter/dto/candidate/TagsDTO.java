package com.minixiao.api.recruiter.dto.candidate;


import java.util.List;
import java.util.UUID;

/**
 * @Author 王迎接 【wangyj@minixiao.com】.
 * @Date 2017/2/22  13:17.
 */
public class TagsDTO {

  private List<AppTagListDTO> appTagList;

  private String tags;


  private UUID optId;

  private String optName;

  private String stage;


  public TagsDTO() {
  }

  public TagsDTO(List<AppTagListDTO> appTagList, String tags, UUID optId, String optName, String stage) {
    this.appTagList = appTagList;
    this.tags = tags;
    this.optId = optId;
    this.optName = optName;
    this.stage = stage;
  }

  public List<AppTagListDTO> getAppTagList() {
    return appTagList;
  }

  public void setAppTagList(List<AppTagListDTO> appTagList) {
    this.appTagList = appTagList;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public UUID getOptId() {
    return optId;
  }

  public void setOptId(UUID optId) {
    this.optId = optId;
  }

  public String getOptName() {
    return optName;
  }

  public void setOptName(String optName) {
    this.optName = optName;
  }

  public String getStage() {
    return stage;
  }

  public void setStage(String stage) {
    this.stage = stage;
  }
}
