package com.minixiao.api.recruiter.controller.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.CandidateFilter;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterUser;
import com.minixiao.api.recruiter.service.recruiters.CandidateFilterService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.UUID;

/**
 * @Description 筛选器 增删改 (属于个人用户).
 * @Author xiachao
 * @CreateTime 2017/2/16 16:05
 */
@RestController
public class CandidateFilterCommand {
  private Logger logger = LoggerFactory.getLogger(CandidateFilterCommand.class);

  @Autowired
  private CandidateFilterService candidateFilterService;

  /**
   * @Description 创建筛选器 .
   * @Author xiachao
   * @CreateTime 2017/2/16 16:07
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PostMapping("/recruiters/account/{userId}/filter/candidate")
  public ResponseEntity createFilter(@RequestBody CandidateFilter candidateFilter,
                                     @PathVariable UUID userId) {
    if (candidateFilter != null) {
      if (userId != null) {
        RecruiterUser recruiterUser = new RecruiterUser();
        recruiterUser.setId(userId);
        candidateFilter.setRecruiterUser(recruiterUser);
        candidateFilterService.saveCandidateFilter(candidateFilter);
        return ResponseEntity.ok(HttpStatus.CREATED);
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);

  }

  /**
   * @Description 修改筛选器 .
   * @Author xiachao
   * @CreateTime 2017/2/16 16:12
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PutMapping("/recruiters/account/{userId}/filter/candidates/{id}")
  public ResponseEntity<CandidateFilter> updateFilter(@RequestBody CandidateFilter candidateFilter,
                                                      @PathVariable UUID userId,
                                                      @PathVariable UUID id) {
    if (candidateFilter != null) {
      RecruiterUser recruiterUser = new RecruiterUser();
      recruiterUser.setId(userId);
      candidateFilterService.deleteCandidateFilter(id);
      candidateFilter.setRecruiterUser(recruiterUser);
      candidateFilterService.saveCandidateFilter(candidateFilter);
      return ResponseEntity.ok(candidateFilter);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }


  /**
   * @Description 删除筛选器 .
   * @Author xiachao
   * @CreateTime 2017/2/16 16:44
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @DeleteMapping("/recruiters/account/{userId}/filter/candidates/{id}")
  public ResponseEntity deleteFilter(@PathVariable UUID userId, @PathVariable UUID id) {
    if (userId != null && id != null) {
      candidateFilterService.deleteCandidateFilter(id);
      return ResponseEntity.ok(null);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }
}
