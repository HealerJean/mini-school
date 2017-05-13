package com.minixiao.api.recruiter.controller.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.ExamArrangement;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.service.recruiters.ExamArrangementService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/17 13:16
 */
@RestController
public class ExamArrangementQuery {

  @Autowired
  private ExamArrangementService examArrangementService;

  /**
   * @Description 根据公司id查询该公司下的所有笔面试安排.
   * @Author xiachao
   * @CreateTime 2017/2/17 14:09
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @GetMapping(" recruiters/{recId}/exams")
  public ResponseEntity<Page<ExamArrangement>> findAllExamArrangement(@PathVariable UUID recId,
                                                                      Pageable pageable) {
    if (recId != null) {
      RecruiterInfo recruiterInfo = new RecruiterInfo();
      recruiterInfo.setId(recId);
      Page<ExamArrangement> examArrangements = examArrangementService.findByRecruiterInfo(
          recruiterInfo, pageable);
      return ResponseEntity.ok(examArrangements);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);

  }

  /**
   * @Description 查询某一个笔面试安排的信息.
   * @Author xiachao
   * @CreateTime 2017/2/17 14:16
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @GetMapping("recruiters/{recId}/exam/{id}")
  public ResponseEntity<ExamArrangement> findExamArrangement(@PathVariable UUID recId,
                                                             @PathVariable UUID id) {
    if (recId != null && id != null) {
      ExamArrangement examArrangement = examArrangementService.findById(id);
      if (examArrangement != null) {
        return ResponseEntity.ok(examArrangement);
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }
}
