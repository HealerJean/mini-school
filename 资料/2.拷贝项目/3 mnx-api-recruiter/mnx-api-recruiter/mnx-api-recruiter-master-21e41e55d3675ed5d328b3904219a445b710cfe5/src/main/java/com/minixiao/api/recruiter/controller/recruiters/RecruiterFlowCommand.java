package com.minixiao.api.recruiter.controller.recruiters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterFlow;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.service.recruiters.RecruiterFlowService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/17 14:31
 */
@RestController
public class RecruiterFlowCommand {

  @Autowired
  private RecruiterFlowService recruiterFlowService;

  private ObjectMapper objectMapper = new ObjectMapper();

  /**
   * @Description 根据公司Id为公司创建一个默认的招聘流程 .
   * @Author xiachao
   * @CreateTime 2017/2/20 18:00
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PostMapping("recruiters/{recId}/flows/init")
  public ResponseEntity initRecruiterFlow(@PathVariable UUID recId) {
    if (recId != null) {
      RecruiterInfo recruiterInfo = new RecruiterInfo();
      recruiterInfo.setId(recId);
      //查询该公司是否已经有招聘流程 如果有 返回 400
      List<RecruiterFlow> recruiterFlows = recruiterFlowService.
          findByRecruiterInfoOrderByFlowOrderAsc(recruiterInfo);
      if (recruiterFlows.size() > 0) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .cacheControl(CacheControl.noCache())
            .body(null);
      }
      //创建初筛流程
      RecruiterFlow recruiterFlow1 = new RecruiterFlow();
      recruiterFlow1.setName("初筛");
      recruiterFlow1.setFlowOrder(0);
      recruiterFlow1.setCandidateCount(0);
      recruiterFlow1.setType(RecruiterFlow.Type.SCREEN.value);
      recruiterFlow1.setRecruiterInfo(recruiterInfo);

      //创建笔试流程
      RecruiterFlow recruiterFlow2 = new RecruiterFlow();
      recruiterFlow2.setName("笔试");
      recruiterFlow2.setFlowOrder(10000);
      recruiterFlow2.setCandidateCount(0);
      recruiterFlow2.setRecruiterInfo(recruiterInfo);
      recruiterFlow2.setType(RecruiterFlow.Type.WRITTEN_EAXM.value);

      //创建面试流程
      RecruiterFlow recruiterFlow3 = new RecruiterFlow();
      recruiterFlow3.setName("面试");
      recruiterFlow3.setFlowOrder(20000);
      recruiterFlow3.setCandidateCount(0);
      recruiterFlow3.setRecruiterInfo(recruiterInfo);
      recruiterFlow3.setType(RecruiterFlow.Type.FACETOFACE_EXAM.value);

      //创建录用流程
      RecruiterFlow recruiterFlow4 = new RecruiterFlow();
      recruiterFlow4.setName("录用");
      recruiterFlow4.setFlowOrder(30000);
      recruiterFlow4.setCandidateCount(0);
      recruiterFlow4.setType("录用类型");
      recruiterFlow4.setRecruiterInfo(recruiterInfo);

      //保存到数据库
      recruiterFlowService.saveRecruiterFlow(recruiterFlow1);
      recruiterFlowService.saveRecruiterFlow(recruiterFlow2);
      recruiterFlowService.saveRecruiterFlow(recruiterFlow3);
      recruiterFlowService.saveRecruiterFlow(recruiterFlow4);
      return ResponseEntity.ok(HttpStatus.CREATED);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 创建用户自定义公司招聘流程.
   * @Author xiachao
   * @CreateTime 2017/2/17 14:31
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PostMapping("recruiters/{recId}/flow")
  public ResponseEntity<String> createRecruiterFlow(@RequestBody RecruiterFlow recruiterFlow,
                                                    @PathVariable UUID recId) {
    if (recId != null && recruiterFlow != null) {
      RecruiterInfo recruiterInfo = new RecruiterInfo();
      recruiterInfo.setId(recId);
      List<RecruiterFlow> flowList = recruiterFlowService.findByRecruiterInfoAndName(
          recruiterInfo, recruiterFlow.getName());
      if (flowList.size() > 0) {
        return ResponseEntity.ok("各流程名称不能相同");
      } else {
        recruiterFlow.setRecruiterInfo(recruiterInfo);
        recruiterFlow.setCandidateCount(0);
        recruiterFlowService.saveRecruiterFlow(recruiterFlow);
        return ResponseEntity.ok("添加成功");
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }


  /**
   * @Description 调整某一个招聘流程的名称或类型 .
   * @Author xiachao
   * @CreateTime 2017/2/17 14:37
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PutMapping("recruiters/{recId}/flows/{id}")
  public ResponseEntity<String> updateRecruiterFlowName(String name, String type,
                                                        @PathVariable UUID id,
                                                        @PathVariable UUID recId) {
    if (name != null && recId != null) {
      RecruiterFlow recruiterFlowOld = recruiterFlowService.findById(id);
      if (recruiterFlowOld.getCandidateCount() > 0) {
        return ResponseEntity.ok("已有简历进入流程，不能进行编辑");
      } else {
        recruiterFlowOld.setName(name);
        recruiterFlowOld.setType(type);
        recruiterFlowService.saveRecruiterFlow(recruiterFlowOld);
        return ResponseEntity.ok("操作成功");
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 根据id删除该招聘流程 .
   * @Author xiachao
   * @CreateTime 2017/2/17 14:45
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @DeleteMapping("recruiters/{recId}/flows/{id}")
  public ResponseEntity<String> deleteRecruiterFlow(@PathVariable UUID recId, @PathVariable UUID id) {
    if (recId != null && id != null) {
      RecruiterFlow recruiterFlow = recruiterFlowService.findById(id);
      if (recruiterFlow.getCandidateCount() > 0) {
        return ResponseEntity.ok("已有简历进入流程，不能进行删除");
      } else {
        recruiterFlowService.deleteRecruiterFlow(id);
        return ResponseEntity.ok("删除成功");
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 调整某一个招聘流程的次序 .
   * @Author xiachao
   * @CreateTime 2017/2/17 14:37
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PutMapping("recruiters/{recId}/flows/{id}/order")
  public ResponseEntity<String> updateRecruiterFlowOrder(Integer flowOrder,
                                                         @PathVariable UUID id,
                                                         @PathVariable UUID recId) {
    if (id != null && recId != null && flowOrder != null) {
      RecruiterFlow recruiterFlowOld = recruiterFlowService.findById(id);
      if (!checkCandidateCount(recId)) {
        return ResponseEntity.ok("已有简历进入流程，不能进行编辑");
      } else {
        recruiterFlowOld.setFlowOrder(flowOrder);
        recruiterFlowService.saveRecruiterFlow(recruiterFlowOld);
        return ResponseEntity.ok("操作成功");
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 判断当前招聘流程中各阶段是否都有申请表 如任何一个有 返回false .
   * @Author xiachao
   * @CreateTime 2017/3/10 19:57
   * @Param
   * @Return
   */
  protected boolean checkCandidateCount(UUID recId) {
    return recruiterFlowService.checkCandidateCount(recId);
  }


}
