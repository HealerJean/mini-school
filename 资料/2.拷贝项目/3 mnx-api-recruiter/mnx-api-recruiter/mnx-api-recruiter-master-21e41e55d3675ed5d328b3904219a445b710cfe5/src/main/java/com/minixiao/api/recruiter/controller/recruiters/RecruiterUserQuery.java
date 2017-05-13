package com.minixiao.api.recruiter.controller.recruiters;

import com.minixiao.api.recruiter.dto.recruiters.RecruiterUserDTO;
import com.minixiao.api.recruiter.dto.recruiters.RecruiterUserExceptPwd;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterUser;
import com.minixiao.api.recruiter.service.recruiters.RecruiterUserService;
import mnx.infra.apiauth.server.domain.AuthUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/14 17:04
 */
@RestController
public class RecruiterUserQuery {
  private Logger logger = LoggerFactory.getLogger(RecruiterUserQuery.class);

  @Autowired
  private RecruiterUserService recruiterUserService;

  /**
   * @Description 查询该公司下所有用户.
   * @Author xiachao
   * @CreateTime 2017/2/14 17:41
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @GetMapping("recruiters/{recId}/users")
  public ResponseEntity<List<RecruiterUser>> users(@PathVariable UUID recId) {
    if (recId != null) {
      Subject currentUser = SecurityUtils.getSubject();
      if (currentUser != null) {
        if (currentUser.isAuthenticated()) {
          AuthUser authUser = (AuthUser) currentUser.getPrincipal();
          //获取管理员id
          UUID userId2 = authUser.getUid();
          RecruiterInfo recruiterInfo = new RecruiterInfo();
          recruiterInfo.setId(recId);
          List<RecruiterUser> recruiterUsers = recruiterUserService.findByRecId(recId, userId2);
          return ResponseEntity.ok(recruiterUsers);
        }
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);

  }

  /**
   * @Description 查询某一个用户 不含密码 .
   * @Author xiachao
   * @CreateTime 2017/2/16 10:38
   * @Param
   * @Return
   */
  @GetMapping("recruiters/{recId}/users/{id}")
  public ResponseEntity<RecruiterUser> findUser(@PathVariable UUID recId, @PathVariable UUID id) {
    if (recId != null && id != null) {
      RecruiterUser recruiterUser = recruiterUserService.findById(id);
      return ResponseEntity.ok(recruiterUser);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 查询某一个用户（登录） .
   * @Author Jiangyh
   * @CreateTime 2017/3/6 10:38
   * @Param
   * @Return
   */
  @GetMapping("recruiters/users/email")
  public ResponseEntity<RecruiterUserDTO> findUserByName(String email) {
    if (email != null) {
      RecruiterUserDTO user = recruiterUserService.findByEmailAndStatus(email, "using");
      return ResponseEntity.ok(user);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }
}
