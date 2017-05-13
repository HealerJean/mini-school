package com.minixiao.api.recruiter.controller.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterPicture;
import com.minixiao.api.recruiter.service.recruiters.RecruiterPictureService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.UUID;

/**
 * @Description 公司图片 增删改 .
 * @Author xiachao
 * @CreateTime 2017/2/23 11:35
 */
@RestController
public class RecruiterPictureCommand {

  @Value("${picture_path}")
  private String picturePath;

  @Autowired
  private RecruiterPictureService recruiterPictureService;

  /**
   * @Description 删除某个公司图片 .
   * @Author xiachao
   * @CreateTime 2017/2/22 18:16
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @DeleteMapping("/recruiters/{recId}/pictures/{id}")
  public ResponseEntity deleteRecruiterPicture(@PathVariable UUID recId, @PathVariable UUID id,
                                               String picUrl) {
    if (recId != null && id != null && picUrl != null) {
      //先删除图片这条记录
      recruiterPictureService.deleteRecruiterPicture(id);
      //从文件系统中删除该文件
      File file = new File(picturePath + picUrl.substring(picUrl.lastIndexOf("/")));
      if (file.exists()) {
        file.delete();
      }
      return ResponseEntity.ok(null);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);

  }

  /**
   * @Description 调整某个公司图片次序.
   * @Author xiachao
   * @CreateTime 2017/2/22 18:53
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PutMapping("/recruiters/{recId}/pictures/{id}/order")
  public ResponseEntity updatePictureOrder(@RequestBody RecruiterPicture recruiterPictureDto,
                                           @PathVariable UUID recId, @PathVariable UUID id) {
    if (recId != null && id != null && recruiterPictureDto != null) {
      RecruiterPicture recruiterPicture = recruiterPictureService.findById(id);
      recruiterPicture.setPictureOrder(recruiterPictureDto.getPictureOrder());
      recruiterPictureService.saveRecruiterPicture(recruiterPicture);
      return ResponseEntity.ok(null);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }
}