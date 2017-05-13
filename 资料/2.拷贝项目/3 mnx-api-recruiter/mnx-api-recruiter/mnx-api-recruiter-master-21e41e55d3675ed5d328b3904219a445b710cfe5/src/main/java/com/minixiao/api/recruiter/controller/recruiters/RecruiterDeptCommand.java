package com.minixiao.api.recruiter.controller.recruiters;

import com.minixiao.api.recruiter.dto.recruiters.RecruiterDeptDTO;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterDept;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.service.jobrequistion.JobRequistionService;
import com.minixiao.api.recruiter.service.recruiters.RecruiterDeptService;
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

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.UUID;

/**
 * @Description 公司部门增删改.
 * @Author xiachao
 * @CreateTime 2017/2/17 11:57
 */

@RestController
public class RecruiterDeptCommand {
  private Logger logger = LoggerFactory.getLogger(CandidateFilterQuery.class);

  @Autowired
  private RecruiterDeptService recruiterDeptService;

  @Autowired
  private JobRequistionService jobRequistionService;

  /**
   * @Description 为公司创建一个默认的一级部门.
   * @Author xiachao
   * @CreateTime 2017/2/20 18:23
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PostMapping("recruiters/{recId}/department/init")
  public ResponseEntity initDept(@RequestBody RecruiterDept recruiterDept,
                                 @PathVariable UUID recId) {
    if (recId != null && recruiterDept.getName() != null && recruiterDept.getName() != "") {
      RecruiterInfo recruiterInfo = new RecruiterInfo();
      recruiterInfo.setId(recId);
      List<RecruiterDept> deptList = recruiterDeptService.findAllByRecruiterInfo(recruiterInfo);
      if (deptList.size() <= 0) {
        //为公司创建一个一级部门
        RecruiterDept recruiterDeptNew = new RecruiterDept();
        recruiterDeptNew.setRecruiterInfo(recruiterInfo);
        recruiterDeptNew.setDeptId("10");
        recruiterDeptNew.setName(recruiterDept.getName());
        recruiterDeptService.saveRecruiterDept(recruiterDeptNew);
        return ResponseEntity.ok(HttpStatus.CREATED);
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }


  /**
   * @Description 创建部门 .
   * @Author xiachao
   * @CreateTime 2017/2/17 12:00
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PostMapping("recruiters/{recId}/department")
  public ResponseEntity createDept(@RequestBody RecruiterDeptDTO recruiterDeptDTO,
                                   @PathVariable UUID recId) {
    if (recId != null) {
      RecruiterInfo recruiterInfo = new RecruiterInfo();
      recruiterInfo.setId(recId);
      RecruiterDept recruiterDept = new RecruiterDept();
      recruiterDept.setRecruiterInfo(recruiterInfo);
      String deptName = recruiterDeptDTO.getName();
      recruiterDept.setName(deptName);
      Long parentId = Long.parseLong(recruiterDeptDTO.getParentId());
      Integer number = recruiterDeptService.checkDeptName(recId, deptName, parentId, parentId);
      if (number > 0) {
        return ResponseEntity.ok("同级部门名称不能相同");
      }
      Long count = recruiterDeptService.findDeptCount(recId, parentId, parentId);
      Long deptId = parentId * 100 + count;
      recruiterDept.setDeptId(deptId + "");
      recruiterDeptService.saveRecruiterDept(recruiterDept);
      return ResponseEntity.ok(recruiterDept);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 修改部门信息 .
   * @Author xiachao
   * @CreateTime 2017/2/17 12:07
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PutMapping("recruiters/{recId}/departments/{id}")
  public ResponseEntity<String> updateDept(@PathVariable UUID recId, @PathVariable UUID id,
                                           @RequestBody RecruiterDept recruiterDept) {
    if (id != null && recId != null && recruiterDept.getName() != null &&
        recruiterDept.getName() != "") {
      RecruiterDept recruiterDeptOld = recruiterDeptService.findById(id);
      String deptName = recruiterDept.getName();
      recruiterDeptOld.setName(deptName);
      String deptId = recruiterDeptOld.getDeptId();
      Long parentId = Long.parseLong(deptId.substring(0, deptId.length() - 2));
      Integer number = recruiterDeptService.checkDeptName(recId, deptName, parentId, parentId);
      if (number > 0) {
        return ResponseEntity.ok("同级部门名称不能相同");
      }
      recruiterDeptService.saveRecruiterDept(recruiterDeptOld);
      return ResponseEntity.ok("修改成功");
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 删除部门.
   * @Author xiachao
   * @CreateTime 2017/2/17 13:02
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @DeleteMapping("recruiters/{recId}/departments/{id}")
  public ResponseEntity deleteDept(@PathVariable UUID id, @PathVariable UUID recId) {
    if (id != null) {
      RecruiterDept recruiterDept = recruiterDeptService.findById(id);
      if ("10".equals(recruiterDept.getDeptId())) {
        return ResponseEntity.ok("一级部门不可删除");
      }
      boolean flag = jobRequistionService.findByDepartment(id);
      if (flag == true) {
        return ResponseEntity.ok("该部门已发布过职位，不可删除");
      }
      Long deptId = Long.parseLong(recruiterDept.getDeptId());
      Long count = recruiterDeptService.findDeptCount(recId, deptId, deptId);
      if (count > 0) {
        return ResponseEntity.ok("请先删除子部门");
      } else {
        recruiterDeptService.deleteRecruiterDept(id);
        return ResponseEntity.ok("删除成功");
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }
}
