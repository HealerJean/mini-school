package com.minixiao.api.recruiter.controller.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 公司属性的数据项查询.
 * @Author xiachao
 * @CreateTime 2017/2/21 10:50
 */
@RestController
public class RecruiterDataItemQuery {

  /**
   * @Description 获取全部公司的行业数据项.
   * @Author xiachao
   * @CreateTime 2017/2/21 10:51
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @GetMapping("/recruiters/industry")
  public ResponseEntity<Map<String, String>> getIndustry() {
    Map<String, String> map = new HashMap<String, String>();
    for (RecruiterInfo.RecIndustry industry : RecruiterInfo.RecIndustry.values()) {
      map.put(industry.getPrimary(), industry.getSlave());
    }
    return ResponseEntity.ok(map);
  }

  /**
   * @Description 获取公司一级行业数据项 .
   * @Author xiachao
   * @CreateTime 2017/2/21 11:09
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @GetMapping("/recruiters/industry/primary")
  public ResponseEntity<List<String>> getIndustryPrimary() {
    List<String> list = new ArrayList<String>();
    for (RecruiterInfo.RecIndustry industry : RecruiterInfo.RecIndustry.values()) {
      list.add(industry.getPrimary());
    }
    return ResponseEntity.ok(list);
  }

  /**
   * @Description 获取某个一级公司行业下的二级行业 .
   * @Author xiachao
   * @CreateTime 2017/2/21 11:09
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @GetMapping("/recruiters/industry/{primary}/slave")
  public ResponseEntity<String> getIndustrySlave(@PathVariable String primary) {
    if (primary != null && !("".equals(primary))) {
      for (RecruiterInfo.RecIndustry industry : RecruiterInfo.RecIndustry.values()) {
        if (primary.equals(industry.getPrimary())) {
          return ResponseEntity.ok(industry.getSlave());
        }
      }
    }
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 获取全部公司性质数据项  .
   * @Author xiachao
   * @CreateTime 2017/2/21 11:23
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @GetMapping("/recruiters/nature")
  public ResponseEntity<List<String>> getRecruiterNature() {
    List<String> list = new ArrayList<String>();
    for (RecruiterInfo.Nature nature : RecruiterInfo.Nature.values()) {
      list.add(nature.toString());
    }
    return ResponseEntity.ok(list);
  }

  /**
   * @Description 获取全部公司规模数据项  .
   * @Author xiachao
   * @CreateTime 2017/2/21 11:23
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @GetMapping("/recruiters/size")
  public ResponseEntity<List<String>> getRecruiterSize() {
    List<String> list = new ArrayList<String>();
    for (RecruiterInfo.Size size : RecruiterInfo.Size.values()) {
      list.add(size.toString());
    }
    return ResponseEntity.ok(list);
  }
}
