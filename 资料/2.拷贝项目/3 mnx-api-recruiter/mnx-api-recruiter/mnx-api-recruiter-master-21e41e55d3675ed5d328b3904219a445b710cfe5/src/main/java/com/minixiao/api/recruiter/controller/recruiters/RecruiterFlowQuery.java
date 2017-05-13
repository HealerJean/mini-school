package com.minixiao.api.recruiter.controller.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.RecruiterFlow;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.service.recruiters.RecruiterFlowService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/17 14:31
 */
@RestController
public class RecruiterFlowQuery {
  @Autowired
  private RecruiterFlowService recruiterFlowService;

  /**
   * @Description 根据公司id查询该公司的所有招聘流程 list .
   * @Author xiachao
   * @CreateTime 2017/2/17 14:52
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @GetMapping("recruiters/{recId}/flows")
  public ResponseEntity<List<RecruiterFlow>> findAllRecruiterFlow(@PathVariable UUID recId) {
    if (recId != null) {
      RecruiterInfo recruiterInfo = new RecruiterInfo();
      recruiterInfo.setId(recId);
      List<RecruiterFlow> recruiterFlows = recruiterFlowService.
          findByRecruiterInfoOrderByFlowOrderAsc(recruiterInfo);
      return ResponseEntity.ok(recruiterFlows);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 根据流程id查看该招聘流程.
   * @Author xiachao
   * @CreateTime 2017/3/2 9:24
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @GetMapping("recruiters/{recId}/flows/{id}")
  public ResponseEntity<RecruiterFlow> findRecruiterFlow(@PathVariable UUID recId,
                                                         @PathVariable UUID id) {
    if (recId != null && id != null) {
      RecruiterFlow recruiterFlow = recruiterFlowService.findById(id);
      return ResponseEntity.ok(recruiterFlow);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }
}
