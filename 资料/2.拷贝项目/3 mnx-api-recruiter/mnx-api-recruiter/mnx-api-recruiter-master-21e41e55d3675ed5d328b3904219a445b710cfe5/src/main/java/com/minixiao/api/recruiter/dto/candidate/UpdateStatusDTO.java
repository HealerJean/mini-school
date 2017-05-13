package com.minixiao.api.recruiter.dto.candidate;

import java.util.List;

/**
 * @Author wangyj.
 * @Date 2017/3/6  17:20.
 */
public class UpdateStatusDTO {

  private List<AppStageListDTO> appStageListDTOs;

  private String stage;

  private String status;

  public UpdateStatusDTO() {
  }

  public UpdateStatusDTO(List<AppStageListDTO> appStageListDTOs, String stage, String status) {
    this.appStageListDTOs = appStageListDTOs;
    this.stage = stage;
    this.status = status;
  }

  public List<AppStageListDTO> getAppStageListDTOs() {
    return appStageListDTOs;
  }

  public void setAppStageListDTOs(List<AppStageListDTO> appStageListDTOs) {
    this.appStageListDTOs = appStageListDTOs;
  }

  public String getStage() {
    return stage;
  }

  public void setStage(String stage) {
    this.stage = stage;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
