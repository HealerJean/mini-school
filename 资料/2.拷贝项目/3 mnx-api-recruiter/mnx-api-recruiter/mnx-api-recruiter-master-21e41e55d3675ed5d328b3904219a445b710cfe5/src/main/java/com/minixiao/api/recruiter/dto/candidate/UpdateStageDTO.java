package com.minixiao.api.recruiter.dto.candidate;

import java.util.List;

/**
 * @Author wangyj.
 * @Date 2017/3/6  17:20.
 */
public class UpdateStageDTO {

    private List<AppStageListDTO> appStageListDTOs;

    private String fromStage;

    private String toStage;

    public UpdateStageDTO() {
    }

    public UpdateStageDTO(List<AppStageListDTO> appStageListDTOs, String fromStage, String toStage) {
        this.appStageListDTOs = appStageListDTOs;
        this.fromStage = fromStage;
        this.toStage = toStage;
    }

    public List<AppStageListDTO> getAppStageListDTOs() {
        return appStageListDTOs;
    }

    public void setAppStageListDTOs(List<AppStageListDTO> appStageListDTOs) {
        this.appStageListDTOs = appStageListDTOs;
    }

    public String getFromStage() {
        return fromStage;
    }

    public void setFromStage(String fromStage) {
        this.fromStage = fromStage;
    }

    public String getToStage() {
        return toStage;
    }

    public void setToStage(String toStage) {
        this.toStage = toStage;
    }
}
