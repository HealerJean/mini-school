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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


/**
 * @Description 筛选器查询.
 * @Author xiachao
 * @CreateTime 2017/2/16 16:06
 */
@RestController
public class CandidateFilterQuery {
  private Logger logger = LoggerFactory.getLogger(CandidateFilterQuery.class);

  @Autowired
  private CandidateFilterService candidateFilterService;

  /**
   * @Description 查询该用户下所有筛选器.
   * @Author xiachao
   * @CreateTime 2017/2/16 16:59
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @GetMapping("/recruiters/account/{userId}/filter/candidates")
  public ResponseEntity<List<CandidateFilter>> filters(@PathVariable UUID userId) {
    if (userId != null) {
      RecruiterUser recruiterUser = new RecruiterUser();
      recruiterUser.setId(userId);
      List<CandidateFilter> candidateFilters = candidateFilterService.findByRecruiterUser(
          recruiterUser);
      return ResponseEntity.ok(candidateFilters);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 查询某一个筛选器.
   * @Author xiachao
   * @CreateTime 2017/2/16 16:53
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @GetMapping("/recruiters/account/{userId}/filter/candidates/{id}")
  public ResponseEntity<CandidateFilter> queryFilter(@PathVariable UUID userId,
                                                     @PathVariable UUID id) {
    if (userId != null && id != null) {
      CandidateFilter candidateFilter = candidateFilterService.findById(id);
      if (candidateFilter != null) {
        return ResponseEntity.ok(candidateFilter);
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }


}
