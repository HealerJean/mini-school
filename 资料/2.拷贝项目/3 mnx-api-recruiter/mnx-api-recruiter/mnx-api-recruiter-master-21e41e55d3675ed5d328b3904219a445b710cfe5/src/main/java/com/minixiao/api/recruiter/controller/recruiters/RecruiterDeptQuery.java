package com.minixiao.api.recruiter.controller.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.RecruiterDept;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.service.recruiters.RecruiterDeptService;
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
 * @CreateTime 2017/2/17 11:57
 */
@RestController
public class RecruiterDeptQuery {

  @Autowired
  private RecruiterDeptService recruiterDeptService;

  /**
   * @Description 查询所有部门 list.
   * @Author xiachao
   * @CreateTime 2017/2/17 13:06
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @GetMapping("recruiters/{recId}/departments")
  public ResponseEntity<List<RecruiterDept>> findAllDepts(@PathVariable UUID recId) {
    if (recId != null) {
      RecruiterInfo recruiterInfo = new RecruiterInfo();
      recruiterInfo.setId(recId);
      List<RecruiterDept> recruiterDepts = recruiterDeptService.findAllByRecruiterInfo(
          recruiterInfo);
      return ResponseEntity.ok(recruiterDepts);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

}
