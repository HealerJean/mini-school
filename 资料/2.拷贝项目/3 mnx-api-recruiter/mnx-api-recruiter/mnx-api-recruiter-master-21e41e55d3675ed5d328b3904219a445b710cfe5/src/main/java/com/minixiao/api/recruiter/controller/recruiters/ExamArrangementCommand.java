package com.minixiao.api.recruiter.controller.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.ExamArrangement;
import com.minixiao.api.recruiter.service.recruiters.ExamArrangementService;
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
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/17 13:16
 */
@RestController
public class ExamArrangementCommand {

  @Autowired
  private ExamArrangementService examArrangementService;

  /**
   * @Description 创建笔面试安排 .
   * @Author xiachao
   * @CreateTime 2017/2/17 13:49
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PostMapping("recruiters/{recId}/exam")
  public ResponseEntity createExamArrangement(@RequestBody ExamArrangement examArrangement,
                                              @PathVariable UUID recId) {
    if (recId != null) {
      examArrangementService.saveExamArrangement(examArrangement);
      return ResponseEntity.ok(HttpStatus.CREATED);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 修改笔面试安排 .
   * @Author xiachao
   * @CreateTime 2017/2/17 13:55
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PutMapping("recruiters/{recId}/exams/{id}")
  public ResponseEntity updateExamArrangement(@RequestBody ExamArrangement examArrangement,
                                              @PathVariable UUID recId, @PathVariable UUID id) {
    if (recId != null && id != null) {
      ExamArrangement examArrangementOld = examArrangementService.findById(id);
      examArrangementOld.setType(examArrangement.getType());
      examArrangementOld.setAccommodateNo(examArrangement.getAccommodateNo());
      examArrangementOld.setAreaName(examArrangement.getAreaName());
      examArrangementOld.setCity(examArrangement.getCity());
      examArrangementOld.setDuration(examArrangement.getDuration());
      examArrangementOld.setInterviewer(examArrangement.getInterviewer());
      examArrangementOld.setLocation(examArrangement.getLocation());
      examArrangementOld.setStage(examArrangement.getStage());
      examArrangementOld.setStartTime(examArrangement.getStartTime());
      //保存更改
      examArrangementService.saveExamArrangement(examArrangementOld);
      return ResponseEntity.ok(null);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description  删除笔面试安排 .
   * @Author  xiachao
   * @CreateTime 2017/2/17 14:05
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @DeleteMapping("recruiters/{recId}/exams/{id}")
  public ResponseEntity deleteExamArrangement(@PathVariable UUID id) {
    if (id != null) {
      examArrangementService.deleteExamArrangement(id);
      return ResponseEntity.ok(null);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

}
