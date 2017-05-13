package com.minixiao.api.recruiter.controller.recruiters;

import com.minixiao.api.recruiter.entity.recruiters.CandidateLabel;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.service.recruiters.CandidateLabelService;
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

import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/20 18:43
 */
@RestController
public class CandidateLabelQuery {
  @Autowired
  private CandidateLabelService candidateLabelService;

  /**
   * @Description 查询该公司下的所有标签 .
   * @Author xiachao
   * @CreateTime 2017/2/20 19:05
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @GetMapping("recruiters/{recId}/candidateLabels")
  public ResponseEntity<List<CandidateLabel>> queryCandidateLabel(@PathVariable UUID recId) {
    if (recId != null) {
      RecruiterInfo recruiterInfo = new RecruiterInfo();
      recruiterInfo.setId(recId);
      List<CandidateLabel> candidateLabels = candidateLabelService.findByRecruiterInfoOrderByCreatedOnDesc(recruiterInfo);
      return ResponseEntity.ok(candidateLabels);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 获取推荐标签.
   * @Author xiachao
   * @CreateTime 2017/2/27 10:50
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @GetMapping("recruiters/recommendation/labels")
  public ResponseEntity<String> getRecommendLabels() {
    String recommendLabels = "五险一金,管理规范,年终分红,交通补助,通讯补贴,定期体检,技能培训,节日礼物,"
        +
        "绩效奖金,年度旅游,岗位晋升,专项奖金,带薪年假";
    return ResponseEntity.ok(recommendLabels);
  }

}
