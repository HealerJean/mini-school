package com.minixiao.api.recruiter.controller.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterPicture;
import com.minixiao.api.recruiter.service.recruiters.RecruiterPictureService;
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
 * @Description 公司图片查询.
 * @Author xiachao
 * @CreateTime 2017/2/23 11:35
 */

@RestController
public class RecruiterPictureQuery {
  @Autowired
  private RecruiterPictureService recruiterPictureService;

  /**
   * @Description 按次序查询公司下所有图片 .
   * @Author xiachao
   * @CreateTime 2017/2/23 11:57
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @GetMapping("/recruiters/{recId}/pictures")
  public ResponseEntity getAllRecruiterPicture(@PathVariable UUID recId) {
    if (recId != null) {
      RecruiterInfo recruiterInfo = new RecruiterInfo();
      recruiterInfo.setId(recId);
      List<RecruiterPicture> recruiterPictureList = recruiterPictureService.findByRecruiterInfo(
          recruiterInfo);
      return ResponseEntity.ok(recruiterPictureList);
    }
    return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }
}
