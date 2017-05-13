package com.minixiao.api.recruiter.controller.recruiters;

import com.minixiao.api.recruiter.dto.recruiters.RecruiterPictureDto;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterPicture;
import com.minixiao.api.recruiter.service.recruiters.RecruiterInfoService;
import com.minixiao.api.recruiter.service.recruiters.RecruiterPictureService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 图片上传command .
 * Created by xiachao on 2017/3/1.
 */
@RestController
public class PictureUploadCommand {

  @Autowired
  private RecruiterInfoService recruiterInfoService;

  @Autowired
  private RecruiterPictureService recruiterPictureService;

  private Logger logger = LoggerFactory.getLogger(PictureUploadCommand.class);


  @Value("${picture_path}")
  private String picturePath;

  /**
   * @Description 公司logo上传.
   * @Author xiachao
   * @CreateTime 2017/3/1 11:10
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PostMapping("recruiters/upload/logo")
  public ResponseEntity<String> uploadRecruiterLogo(@RequestBody RecruiterPictureDto pictureDto,
                                                    @PathVariable UUID recId) {
    if (pictureDto != null && recId != null) {
      String logoUrl = recruiterInfoService.uploadLogo(recId, pictureDto);
      if (logoUrl != null && !("".equals(logoUrl))) {
        return ResponseEntity.ok(logoUrl);
      } else {
        logger.error("获取logo链接出错，logo上传失败");
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
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
   * @Description 上传公司图片.
   * @Author xiachao
   * @CreateTime 2017/3/6 17:42
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PostMapping("recruiters/upload/picture")
  public ResponseEntity<String> uploadRecruiterPictures(@RequestBody RecruiterPictureDto pictureDto,
                                                        @PathVariable UUID recId) {
    if (pictureDto != null && recId != null) {
      //公司图片上传
      byte[] fileByte = pictureDto.getFileByte();
      String filename = UUID.randomUUID().toString() + pictureDto.getFileSuffix();
      File fileDir = new File(picturePath);
      if (!fileDir.exists()) {
        fileDir.mkdirs();
      }
      try {
        File file = new File(fileDir + File.separator + filename);
        FileImageOutputStream imageOutputStream = new FileImageOutputStream(file);
        //写入本地磁盘
        imageOutputStream.write(fileByte, 0, fileByte.length);
        imageOutputStream.close();
        //这里暂时写死
        //String picUrl = "http://www.minixiao.com/st/images/"+fileDir + File.separator + filename;
        String picUrl = "http://www.minixiao.com/st/images/companyIntroduction/201612/2016120610"
            +
            "2858d563d12588544c04948c951b04789540.png";
        RecruiterInfo recruiterInfo2 = new RecruiterInfo();
        recruiterInfo2.setId(recId);
        RecruiterPicture recruiterPicture = new RecruiterPicture();
        recruiterPicture.setUrl(picUrl);
        recruiterPicture.setRecruiterInfo(recruiterInfo2);
        //图片url保存到数据库
        recruiterPictureService.saveRecruiterPicture(recruiterPicture);
        return ResponseEntity.ok(picUrl);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }
}
