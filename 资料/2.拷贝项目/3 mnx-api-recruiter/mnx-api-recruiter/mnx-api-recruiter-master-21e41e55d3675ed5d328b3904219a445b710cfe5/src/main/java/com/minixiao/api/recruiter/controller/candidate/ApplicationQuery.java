package com.minixiao.api.recruiter.controller.candidate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minixiao.api.recruiter.dto.candidate.ApplicationDTO;
import com.minixiao.api.recruiter.dto.candidate.ApplicationFilterDTO;
import com.minixiao.api.recruiter.dto.candidate.ApplicationListDTO;
import com.minixiao.api.recruiter.entity.candidate.Applications;
import com.minixiao.api.recruiter.service.candidate.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @Author 王迎接 【wangyj@minixiao.com】.
 * @Date 2017/2/15  15:00.
 */
@RestController
public class ApplicationQuery {
  @Autowired
  private ApplicationService applicationService;

  /**
   * @Description 查看申请表.
   * @Author  王迎接【wangyj@minixiao.com】
   * @CreateDate 2017/2/16 16:21
   * @Param
   * @Return
   */
  @GetMapping("/candidate/{id}")
  public ResponseEntity<ApplicationDTO> getApplication(@PathVariable UUID id){
    // TODO: 2017/2/16 判断当前用户类型 ，hr获取申请表详情后将改变申请表状态
    ApplicationDTO applicationDTO = null;
    try {
      applicationDTO = applicationService.getApplicaiton(id);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ResponseEntity.ok(applicationDTO);
  }

  /**
   * @Description 获取申请表列表.
   * @Author  王迎接【wangyj@minixiao.com】
   * @CreateDate 2017/2/28 16:43
   * @Param
   * @Return
   */
  @GetMapping("/candidate")
  public ResponseEntity<ApplicationListDTO> listApplication(
      @RequestParam String filter){
    //将字字符串转化为applicationFilterDTO
    String filterJson = filter.replaceAll(" ","");
    ObjectMapper objectMapper = new ObjectMapper();
    ApplicationFilterDTO applicationFilterDTO =null;
    try {
      applicationFilterDTO = objectMapper.readValue("{"+filterJson+"}",
          ApplicationFilterDTO.class);
    } catch (IOException e) {
      e.printStackTrace();
    }

    ApplicationListDTO applicationsListDTO = null;

    try {
      applicationsListDTO = applicationService.listApplication(applicationFilterDTO);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ResponseEntity.ok(applicationsListDTO);
  }


}
