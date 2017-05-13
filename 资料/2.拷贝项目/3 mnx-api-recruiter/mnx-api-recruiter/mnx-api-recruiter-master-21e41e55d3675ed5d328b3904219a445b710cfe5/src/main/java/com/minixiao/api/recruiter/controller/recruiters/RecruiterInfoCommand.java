package com.minixiao.api.recruiter.controller.recruiters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.service.recruiters.RecruiterDeptService;
import com.minixiao.api.recruiter.service.recruiters.RecruiterFlowService;
import com.minixiao.api.recruiter.service.recruiters.RecruiterInfoService;
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
 * @Description 公司创建，删除，修改.
 * @Author xiachao
 * @CreateTime 2017/2/14 17:55
 */
@RestController
public class RecruiterInfoCommand {

  private Logger logger = LoggerFactory.getLogger(RecruiterInfoCommand.class);
  @Autowired
  private RecruiterInfoService recruiterInfoService;

  @Autowired
  private RecruiterDeptService recruiterDeptService;

  @Autowired
  private RecruiterFlowService recruiterFlowService;

  private ObjectMapper objectMapper = new ObjectMapper();

  /**
   * @Description 创建公司.
   * @Author xiachao
   * @CreateTime 2017/2/14 18:09
   * @Param recruiterInfo 公司信息
   * @Return ResponseEntity
   */
  @RequiresRoles("COMPANY")
  @PostMapping("/recruiters")
  public ResponseEntity<String> recruiters(@RequestBody RecruiterInfo recruiterInfo) {
    if (recruiterInfo != null) {
      //保存公司信息
      recruiterInfoService.saveRecruiterInfo(recruiterInfo);
      return ResponseEntity.status(HttpStatus.CREATED)
          .cacheControl(CacheControl.noCache())
          .body(null);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 公司信息设置 引导页.
   * @Author xiachao
   * @CreateTime 2017/3/8 20:46
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PutMapping("/recruiters/{recId}/step1")
  public ResponseEntity<RecruiterInfo> updateRecruiterInfo(@PathVariable UUID recId,
                                                           @RequestBody RecruiterInfo
                                                               recruiterInfoDto) {
    if (recId != null && recruiterInfoDto != null) {
      RecruiterInfo recruiterInfo = recruiterInfoService.findById(recId);
      recruiterInfo.setNature(recruiterInfoDto.getNature());
      recruiterInfo.setSize(recruiterInfoDto.getSize());
      recruiterInfo.setPrimaryIndustry(recruiterInfoDto.getPrimaryIndustry());
      recruiterInfo.setCity(recruiterInfoDto.getCity());
      recruiterInfo.setDetailAddress(recruiterInfoDto.getDetailAddress());
      recruiterInfo.setWebsite(recruiterInfoDto.getWebsite());
      recruiterInfo.setTags(recruiterInfoDto.getTags());
      recruiterInfo.setSlogan(recruiterInfoDto.getSlogan());
      recruiterInfo.setDescription(recruiterInfoDto.getDescription());
      recruiterInfoService.saveRecruiterInfo(recruiterInfo);
      return ResponseEntity.ok(null);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 根据公司id更新该公司信息 .
   * @Author xiachao
   * @CreateTime 2017/2/15 14:54
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PutMapping("/recruiters/{id}")
  public ResponseEntity<String> updateRecruiter(@RequestBody RecruiterInfo recruiterInfoDto,
                                                @PathVariable UUID id) {
    if (id != null) {
      RecruiterInfo recruiterInfo = recruiterInfoService.findById(id);
      //修改公司性质
      if (recruiterInfoDto.getNature() != null && !("".equals(recruiterInfoDto.getNature()))) {
        recruiterInfo.setNature(recruiterInfoDto.getNature());
        recruiterInfoService.saveRecruiterInfo(recruiterInfo);
        return ResponseEntity.ok(recruiterInfoDto.getNature());
      }
      //修改公司规模
      if (recruiterInfoDto.getSize() != null && !("".equals(recruiterInfoDto.getSize()))) {
        recruiterInfo.setSize(recruiterInfoDto.getSize());
        recruiterInfoService.saveRecruiterInfo(recruiterInfo);
        return ResponseEntity.ok(recruiterInfoDto.getSize());
      }
      //修改一级行业类别
      if (recruiterInfoDto.getPrimaryIndustry() != null
          &&
          !("".equals(recruiterInfoDto.getPrimaryIndustry()))) {
        recruiterInfo.setPrimaryIndustry(recruiterInfoDto.getPrimaryIndustry());
        recruiterInfoService.saveRecruiterInfo(recruiterInfo);
        return ResponseEntity.ok(recruiterInfoDto.getPrimaryIndustry());
      }
      //修改二级行业类别
      if (recruiterInfoDto.getSlaveIndustry() != null
          &&
          !("".equals(recruiterInfoDto.getSlaveIndustry()))) {
        recruiterInfo.setSlaveIndustry(recruiterInfoDto.getSlaveIndustry());
        recruiterInfoService.saveRecruiterInfo(recruiterInfo);
        return ResponseEntity.ok(recruiterInfoDto.getSlaveIndustry());
      }
      //修改所在省市
      if (recruiterInfoDto.getCity() != null
          &&
          !("".equals(recruiterInfoDto.getCity()))) {
        recruiterInfo.setCity(recruiterInfoDto.getCity());
        recruiterInfoService.saveRecruiterInfo(recruiterInfo);
        return ResponseEntity.ok(recruiterInfoDto.getCity());
      }
      //修改详细地址
      if (recruiterInfoDto.getDetailAddress() != null
          &&
          !("".equals(recruiterInfoDto.getDetailAddress()))) {
        recruiterInfo.setDetailAddress(recruiterInfoDto.getDetailAddress());
        recruiterInfoService.saveRecruiterInfo(recruiterInfo);
        return ResponseEntity.ok(recruiterInfoDto.getDetailAddress());
      }
      //修改公司官网
      if (recruiterInfoDto.getWebsite() != null && !("".equals(recruiterInfoDto.getWebsite()))) {
        recruiterInfo.setWebsite(recruiterInfoDto.getWebsite());
        recruiterInfoService.saveRecruiterInfo(recruiterInfo);
        return ResponseEntity.ok(recruiterInfoDto.getWebsite());
      }
      //修改公司标签
      if (recruiterInfoDto.getTags() != null && !("".equals(recruiterInfoDto.getTags()))) {
        recruiterInfo.setTags(recruiterInfoDto.getTags());
        recruiterInfoService.saveRecruiterInfo(recruiterInfo);
        return ResponseEntity.ok(recruiterInfoDto.getTags());
      }
      //修改公司logo
      if (recruiterInfoDto.getLogo() != null && !("".equals(recruiterInfoDto.getLogo()))) {
        recruiterInfo.setLogo(recruiterInfoDto.getLogo());
        recruiterInfoService.saveRecruiterInfo(recruiterInfo);
        return ResponseEntity.ok(recruiterInfoDto.getLogo());
      }
      //修改一句话介绍
      if (recruiterInfoDto.getSlogan() != null && !("".equals(recruiterInfoDto.getSlogan()))) {
        recruiterInfo.setSlogan(recruiterInfoDto.getSlogan());
        recruiterInfoService.saveRecruiterInfo(recruiterInfo);
        return ResponseEntity.ok(recruiterInfoDto.getSlogan());
      }
      //修改公司简介
      if (recruiterInfoDto.getDescription() != null
          && !("".equals(recruiterInfoDto.getDescription()))) {
        recruiterInfo.setDescription(recruiterInfoDto.getDescription());
        recruiterInfoService.saveRecruiterInfo(recruiterInfo);
        return ResponseEntity.ok(recruiterInfoDto.getDescription());
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 根据公司ID删除该公司 .
   * @Author xiachao
   * @CreateTime 2017/2/15 15:08
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @DeleteMapping("/recruiters/{id}")
  public ResponseEntity<String> deleteRecruiter(@PathVariable UUID id) {
    if (id != null) {
      recruiterInfoService.deleteRecruiter(id);
      return ResponseEntity
          .status(HttpStatus.OK)
          .cacheControl(CacheControl.noCache())
          .body(null);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }
}
