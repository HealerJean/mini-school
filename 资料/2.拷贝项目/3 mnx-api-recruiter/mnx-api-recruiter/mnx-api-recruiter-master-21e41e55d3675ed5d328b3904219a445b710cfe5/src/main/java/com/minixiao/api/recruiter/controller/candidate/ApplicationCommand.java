
package com.minixiao.api.recruiter.controller.candidate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.minixiao.api.recruiter.dto.candidate.AppStageListDTO;
import com.minixiao.api.recruiter.dto.candidate.ApplicationDTO;
import com.minixiao.api.recruiter.dto.candidate.ApplicationStatDTO;
import com.minixiao.api.recruiter.dto.candidate.UpdateStageDTO;
import com.minixiao.api.recruiter.dto.candidate.UpdateStatusDTO;
import com.minixiao.api.recruiter.dto.jobrequisition.JobStatDTO;
import com.minixiao.api.recruiter.entity.candidate.Applications;
import com.minixiao.api.recruiter.entity.candidate.HandleHistory;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterFlow;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.service.candidate.ApplicationService;
import com.minixiao.api.recruiter.service.candidate.ApplicationStatService;
import com.minixiao.api.recruiter.service.candidate.HandleHistoryService;
import com.minixiao.api.recruiter.service.candidate.impl.ApplicationStatServiceImpl;
import com.minixiao.api.recruiter.service.jobrequistion.JobRequistionStatisticsService;
import com.minixiao.api.recruiter.service.recruiters.RecruiterFlowService;
import mnx.infra.apiauth.server.domain.AuthUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

/**
 * @Author 王迎接 【wangyj@minixiao.com】.
 * @Date 2017/2/15  15:03.
 */
@Transactional
@Controller
public class ApplicationCommand {

  @Autowired
  private ApplicationService applicationService;

  @Autowired
  private RecruiterFlowService recruiterFlowService;

  @Autowired
  private HandleHistoryService handleHistoryService;

  @Autowired
  private ApplicationStatService applicationStatService;

  @Autowired
  private JobRequistionStatisticsService jobRequistionStatisticsService;

  private Logger logger = LoggerFactory.getLogger(ApplicationCommand.class);
  /**
   * @Description 创建申请表.
   * @Author 王迎接【wangyj@minixiao.com】
   * @CreateDate 2017/2/15 15:10
   * @Param
   * @Return
   */
  @PostMapping("/candidate")
  public ResponseEntity<String> saveApplication(@RequestBody ApplicationDTO applicationDTO) {

    try {
      //保存申请表
      logger.info("{} creat application",applicationDTO.getStuId());
      applicationService.saveApplication(applicationDTO);

      return ResponseEntity
          .status(HttpStatus.CREATED)
          .cacheControl(CacheControl.noCache())
          .body("ok");
    } catch (JsonProcessingException e) {
      logger.error("{} creat application",applicationDTO.getStuId(),e);
      e.printStackTrace();
      return ResponseEntity
          .status(HttpStatus.UNPROCESSABLE_ENTITY)
          .cacheControl(CacheControl.noCache())
          .body("failed");
    }

  }

  /**
   * @Description 修改申请表 .
   * @Author 王迎接【wangyj@minixiao.com】
   * @CreateDate 2017/2/16 13:54
   * @Param
   * @Return
   */
  @PutMapping("/candidate/{id}")
  public ResponseEntity<String> updateApplication(@RequestBody ApplicationDTO applicationDTO
      , @PathVariable UUID id) {
    Applications applications = applicationService.findById(id);
    if (applications != null) {
      UUID currentId = applications.getId();
      try {
        logger.info("update id:{} application",id);
        String result = applicationService.updateApplication(applications, applicationDTO);
        return ResponseEntity
            .status(HttpStatus.OK)
            .cacheControl(CacheControl.noCache())
            .body(result);
      } catch (JsonProcessingException ex) {
        logger.error("update id:{} application",id,ex);
        ex.printStackTrace();
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .cacheControl(CacheControl.noCache())
            .body(null);
      }
    }
    return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .cacheControl(CacheControl.noCache())
        .body("The id is not exsited");
  }

  /**
   * @Description 更改申请表阶段-通过.
   * @Author 王迎接【wangyj@minixiao.com】
   * @CreateDate 2017/2/16 15:01
   * @Param
   * @Return
   */
  @PutMapping("/candidate/stage/pass")
  public ResponseEntity<String> upadateAppStagePass(@RequestBody UpdateStageDTO updateStageDTO) {

    Subject currentUser = SecurityUtils.getSubject();
    if (currentUser.isAuthenticated()) {
      List<AppStageListDTO> appStageListDTOs = updateStageDTO.getAppStageListDTOs();
      String fromStage = updateStageDTO.getFromStage();
      String toStage = updateStageDTO.getToStage();
      AuthUser authUser = (AuthUser) currentUser.getPrincipal();
      UUID recId = authUser.getOid();
      RecruiterInfo recruiterInfo = new RecruiterInfo();
      recruiterInfo.setId(recId);
      List<RecruiterFlow> recruiterFlowsList = recruiterFlowService.findByRecruiterInfoOrderByFlowOrderAsc(recruiterInfo);
      int size = updateStageDTO.getAppStageListDTOs().size();
      String optTyp = "";
      String nextStage = "";  //判断阶段改变是通过还是阶段跳转
      UUID fromId = null;  //获取fromStage对应ID
      UUID toId = null;  //获取toStage对应ID
      Integer fromCount = 0;
      Integer toCount = 0;
      String fromType = "";
      String toType = "";
      for (RecruiterFlow recruiterFlow : recruiterFlowsList) {
        if (fromStage.equals(recruiterFlow.getName())) {
          fromId = recruiterFlow.getId();
          fromCount = recruiterFlow.getCandidateCount();
          fromType = recruiterFlow.getType();
        }
        if (toStage.equals(recruiterFlow.getName())) {
          toId = recruiterFlow.getId();
          toCount = recruiterFlow.getCandidateCount();
          toType = recruiterFlow.getType();
          optTyp = "通过";
          break;
        }
      }

      if ("".equals(toStage)) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .cacheControl(CacheControl.noCache())
            .body("stage is not existed");
      } else {
        for (AppStageListDTO appStageListDTO : appStageListDTOs) {
          //判断fromstage是否为该公司的招聘流程内
          //修改申请表阶段
          String result = applicationService.updateApplicationStage(appStageListDTO.getId(), toStage, toId);
          //更改申请表阶段后重置状态
          applicationService.updateAppStatusInit(appStageListDTO.getId(), "1100");
          //保存操作历史
          HandleHistory handleHistory = new HandleHistory();
          handleHistory.setApplicationId(appStageListDTO.getId());
          handleHistory.setJobId(appStageListDTO.getJobId());
          handleHistory.setJobName(appStageListDTO.getJobName());
          handleHistory.setCandidateName(appStageListDTO.getCandidateName());
          handleHistory.setStatus(fromStage);
          handleHistory.setOptUid(authUser.getUid());
          handleHistory.setOptUname(authUser.getDnm());
          handleHistory.setOptType(optTyp);
          handleHistory.setRecId(recId);
          handleHistory.setRecName(authUser.getOnm());
          handleHistory.setDescription(appStageListDTO.getCandidateName() + "在" + fromStage + "阶段进行" + optTyp + "操作");
          handleHistoryService.saveHandleHistory(handleHistory);
          //更改职位统计相关数据
          JobStatDTO jobStatDTO = new JobStatDTO();
          jobStatDTO.setFromType(fromType);
          jobStatDTO.setToType(toType);
          jobStatDTO.setJobId(appStageListDTO.getJobId());
          jobStatDTO.setRecId(recId);
          jobRequistionStatisticsService.updateRequistionStatistic(jobStatDTO);
        }
      }
      //更改当前阶段人数
      recruiterFlowService.updateCandidateCount(fromCount - size, fromId);//修改fromStage
      recruiterFlowService.updateCandidateCount(toCount + size, toId);
      return ResponseEntity
          .status(HttpStatus.OK)
          .cacheControl(CacheControl.noCache())
          .body("ok");
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body("failed");
  }

  /**
   * @Description 更改申请表阶段-阶段跳转.
   * @Author 王迎接【wangyj@minixiao.com】
   * @CreateDate 2017/2/16 15:01
   * @Param
   * @Return
   */
  @PutMapping("/candidate/stage/skip")
  public ResponseEntity<String> upadateAppStageSkip(@RequestBody UpdateStageDTO updateStageDTO) {
    Subject currentUser = SecurityUtils.getSubject();
    if (currentUser.isAuthenticated()) {
      AuthUser authUser = (AuthUser) currentUser.getPrincipal();
      UUID recId = authUser.getOid();
      List<AppStageListDTO> appStageListDTOs = updateStageDTO.getAppStageListDTOs();
      String fromStage = updateStageDTO.getFromStage();
      String toStage = updateStageDTO.getToStage();
      RecruiterInfo recruiterInfo = new RecruiterInfo();
      recruiterInfo.setId(recId);
      List<RecruiterFlow> recruiterFlowsList = recruiterFlowService.findByRecruiterInfoOrderByFlowOrderAsc(recruiterInfo);
      int size = updateStageDTO.getAppStageListDTOs().size();
      String optTyp = "";
      String nextStage = "";  //判断阶段改变是通过还是阶段跳转
      UUID fromId = null;  //获取fromStage对应ID
      UUID toId = null;  //获取toStage对应ID
      Integer fromCount = 0;
      Integer toCount = 0;
      String fromType = "";
      String toType = "";

      for (RecruiterFlow recruiterFlow : recruiterFlowsList) {
        if (fromStage.equals(recruiterFlow.getName())) {
          fromId = recruiterFlow.getId();
          fromCount = recruiterFlow.getCandidateCount();
          fromType = recruiterFlow.getType();
          break;
        }

      }

      for (RecruiterFlow recruiterFlow : recruiterFlowsList) {
        if (toStage.equals(recruiterFlow.getName())) {
          toId = recruiterFlow.getId();
          toCount = recruiterFlow.getCandidateCount();
          optTyp = "阶段跳转";
          toType = recruiterFlow.getType();
          break;
        }
      }

      if ("".equals(toStage)) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .cacheControl(CacheControl.noCache())
            .body("stage is not existed");
      } else {
        for (AppStageListDTO appStageListDTO : appStageListDTOs) {
          //判断fromstage是否为该公司的招聘流程内
          //修改申请表阶段
          String result = applicationService.updateApplicationStage(appStageListDTO.getId(), toStage, toId);
          //更改申请表阶段后重置状态
          applicationService.updateAppStatusInit(appStageListDTO.getId(), "1100");
          //保存操作历史
          HandleHistory handleHistory = new HandleHistory();
          handleHistory.setApplicationId(appStageListDTO.getId());
          handleHistory.setJobId(appStageListDTO.getJobId());
          handleHistory.setJobName(appStageListDTO.getJobName());
          handleHistory.setCandidateName(appStageListDTO.getCandidateName());
          handleHistory.setStatus(fromStage);
          handleHistory.setOptUid(authUser.getUid());
          handleHistory.setOptUname(authUser.getDnm());
          handleHistory.setOptType(optTyp);
          handleHistory.setRecId(recId);
          handleHistory.setRecName(authUser.getOnm());
          handleHistory.setDescription(appStageListDTO.getCandidateName() + "在" + fromStage + "阶段进行" + optTyp + "操作");
          handleHistoryService.saveHandleHistory(handleHistory);
          //更改职位统计相关数据
          JobStatDTO jobStatDTO = new JobStatDTO();
          jobStatDTO.setFromType(fromType);
          jobStatDTO.setToType(toType);
          jobStatDTO.setJobId(appStageListDTO.getJobId());
          jobStatDTO.setRecId(recId);
          jobRequistionStatisticsService.updateRequistionStatistic(jobStatDTO);
        }

        //更改当前阶段人数
        recruiterFlowService.updateCandidateCount(fromCount - size, fromId);
        recruiterFlowService.updateCandidateCount(toCount + size, toId);
        return ResponseEntity
            .status(HttpStatus.OK)
            .cacheControl(CacheControl.noCache())
            .body("ok");

      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body("failed");

  }

  /**
   * @Description 更改申请表状态.
   * @Author wangyj
   * @CreateDate 2017/3/1 11:24
   * @Param
   * @Return
   */
  @PutMapping("/candidate/status")
  public ResponseEntity<String> updateApplicationStatus(@RequestBody UpdateStatusDTO updateStatusDTO) {
    Subject currentUser = SecurityUtils.getSubject();
    if (currentUser.isAuthenticated()) {
      AuthUser authUser = (AuthUser) currentUser.getPrincipal();
      String status = updateStatusDTO.getStatus();
      String stage = updateStatusDTO.getStage();
      for (AppStageListDTO dto : updateStatusDTO.getAppStageListDTOs()) {
        //修改申请表状态
        String result = applicationService.updateApplicationStatus(dto,status);
        //不通过时发送消息
        if(("不通过".equals(status))&("ok".equals(result))){

        }
        //保存操作历史
        UUID recId = authUser.getOid();
        HandleHistory handleHistory = new HandleHistory();
        handleHistory.setApplicationId(dto.getId());
        handleHistory.setJobId(dto.getJobId());
        handleHistory.setJobName(dto.getJobName());
        handleHistory.setCandidateName(dto.getCandidateName());
        handleHistory.setStatus(stage);
        handleHistory.setOptUid(authUser.getUid());
        handleHistory.setOptUname(authUser.getDnm());
        handleHistory.setOptType(status);
        handleHistory.setRecId(recId);
        handleHistory.setRecName(authUser.getOnm());
        handleHistory.setDescription(dto.getCandidateName() + "在" + stage + "阶段进行" + status + "操作");
        handleHistoryService.saveHandleHistory(handleHistory);
        if ("failed".equals(result)) {
          return ResponseEntity
              .status(HttpStatus.BAD_REQUEST)
              .cacheControl(CacheControl.noCache())
              .body("failed");
        }
      }
    }
    return ResponseEntity
        .status(HttpStatus.OK)
        .cacheControl(CacheControl.noCache())
        .body("ok");
  }


  /**
   * @Description 删除申请表 .
   * @Author 王迎接【wangyj@minixiao.com】
   * @CreateDate 2017/2/27 15:23
   * @Param
   * @Return
   */
  @DeleteMapping("/candidate/{id}")
  public ResponseEntity<String> deleteApplication(@PathVariable UUID id) {
    applicationService.deleteApplication(id);
    return ResponseEntity
        .status(HttpStatus.OK)
        .cacheControl(CacheControl.noCache())
        .body("ok");
  }


  /**
   * @Description 申请表统计（sass首页）.
   * @Author wangyj
   * @CreateDate 2017/3/3 17:29
   * @Param
   * @Return
   */
  @GetMapping("/candidate/{recId}/application/stat")
  public ApplicationStatDTO applicationStat(@PathVariable UUID recId) {
    ApplicationStatDTO applicationStatDTO = applicationStatService.applicationStat(recId);
    return applicationStatDTO;
  }
}

