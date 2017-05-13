package com.minixiao.api.recruiter.controller.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.service.recruiters.RecruiterInfoService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Description 查询公司相关信息.
 * @Author xiachao
 * @CreateTime 2017/2/15 13:07
 */
@RestController
public class RecruiterInfoQuery {
  private Logger logger = LoggerFactory.getLogger(RecruiterInfoQuery.class);

  @Autowired
  private RecruiterInfoService recruiterInfoService;

  /**
   * @Description 获取某一个公司的详细信息.
   * @Author xiachao
   * @CreateTime 2017/2/15 13:08
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @GetMapping("/recruiters/{id}")
  public ResponseEntity<RecruiterInfo> getRecruiter(@PathVariable UUID id) {
    if (id != null) {
      RecruiterInfo recruiterInfo = recruiterInfoService.findById(id);
      if (recruiterInfo != null) {
        return ResponseEntity.ok(recruiterInfo);
      } else {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .cacheControl(CacheControl.noCache())
            .body(null);
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 获取所有公司的详细信息.
   * @Author xiachao
   * @CreateTime 2017/2/15 15:21
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @GetMapping("/recruiters")
  public ResponseEntity<Page<RecruiterInfo>> getAllRecruiters(Pageable pageable) {
    Page<RecruiterInfo> recruiterInfos = recruiterInfoService.findAll(pageable);
    return ResponseEntity.ok(recruiterInfos);
  }
}
