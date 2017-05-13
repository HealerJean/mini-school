package com.minixiao.api.recruiter.controller.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.CandidateLabel;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.service.recruiters.CandidateLabelService;
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

import java.util.UUID;

/**
 * @Description 标签增删改 .
 * @Author xiachao
 * @CreateTime 2017/2/20 18:42
 */
@RestController
public class CandidateLabelCommand {

  @Autowired
  private CandidateLabelService candidateLabelService;

  /**
   * @Description 创建标签.
   * @Author xiachao
   * @CreateTime 2017/2/20 18:43
   * @Param
   * @Return
   */
  @PostMapping("recruiters/{recId}/candidatelabel")
  @RequiresRoles("COMPANY")
  public ResponseEntity createCandidateLabel(@RequestBody CandidateLabel candidateLabel,
                                             @PathVariable UUID recId) {
    if (candidateLabel != null && recId != null) {
      RecruiterInfo recruiterInfo = new RecruiterInfo();
      recruiterInfo.setId(recId);
      candidateLabel.setRecruiterInfo(recruiterInfo);
      candidateLabel.setStatus(true);
      candidateLabelService.saveCandidateLabel(candidateLabel);
      return ResponseEntity.ok(HttpStatus.CREATED);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 修改标签名称 .
   * @Author xiachao
   * @CreateTime 2017/2/20 18:53
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PutMapping("recruiters/{recId}/candidatelabels/{id}")
  public ResponseEntity updateCandidateLabel(@RequestBody CandidateLabel candidateLabel,
                                             @PathVariable UUID recId, @PathVariable UUID id) {
    if (recId != null && id != null) {
      CandidateLabel candidateLabel1 = candidateLabelService.findById(id);
      candidateLabel1.setName(candidateLabel.getName());
      candidateLabelService.saveCandidateLabel(candidateLabel1);
      return ResponseEntity.ok(null);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 修改标签状态.
   * @Author xiachao
   * @CreateTime 2017/3/3 10:03
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PutMapping("recruiters/{recId}/candidatelabels/{id}/status")
  public ResponseEntity updateCandidateLabelStatus(@PathVariable UUID recId, @PathVariable UUID id,
                                                   boolean status) {
    if (recId != null && id != null) {
      candidateLabelService.updateCandidateLabelStatus(status, id);
      return ResponseEntity.ok(null);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);

  }


  /**
   * @Description 删除标签.
   * @Author xiachao
   * @CreateTime 2017/2/20 19:01
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @DeleteMapping("recruiters/{recId}/candidatelabels/{id}")
  public ResponseEntity deleteCandidateLabel(@PathVariable UUID recId, @PathVariable UUID id) {
    if (recId != null && id != null) {
      CandidateLabel candidateLabel = candidateLabelService.findById(id);
      if (candidateLabel.getUseCount() != null) {
        if (candidateLabel.getUseCount() > 0) {
          return ResponseEntity
              .status(HttpStatus.BAD_REQUEST)
              .cacheControl(CacheControl.noCache())
              .body(null);
        } else {
          candidateLabelService.deleteCandidateLabel(id);
          return ResponseEntity.ok(null);
        }
      } else {
        candidateLabelService.deleteCandidateLabel(id);
        return ResponseEntity.ok(null);
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }
}
