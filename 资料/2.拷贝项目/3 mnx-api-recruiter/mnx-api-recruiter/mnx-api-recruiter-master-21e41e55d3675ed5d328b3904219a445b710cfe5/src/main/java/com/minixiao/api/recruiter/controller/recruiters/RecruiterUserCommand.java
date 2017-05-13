package com.minixiao.api.recruiter.controller.recruiters;

import com.minixiao.api.recruiter.dto.recruiters.RecruiterUserPwdDTO;
import com.minixiao.api.recruiter.entity.recruiters.CandidateFilter;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterUser;
import com.minixiao.api.recruiter.service.recruiters.CandidateFilterService;
import com.minixiao.api.recruiter.service.recruiters.RecruiterUserService;
import com.minixiao.api.recruiter.utils.MD5Util;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @Description 用户创建，修改，删除.
 * @Author xiachao
 * @CreateTime 2017/2/15 13:07
 */
@RestController
public class RecruiterUserCommand {
  private Logger logger = LoggerFactory.getLogger(RecruiterInfoCommand.class);

  @Autowired
  private RecruiterUserService recruiterUserService;

  @Autowired
  private CandidateFilterService candidateFilterService;

  /**
   * @Description 创建管理员账号( ？第一次创建管理员账号的时候是否给其创建一条公司信息记录) .
   * @Author xiachao
   * @CreateTime 2017/2/23 14:46
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PostMapping("recruiters/user/admin")
  public ResponseEntity createRecruiterAdminUser(@RequestBody RecruiterUser recruiterUser) {
    if (recruiterUser != null) {
      if (!"".equals(recruiterUser.getPwd())) {
        String encryptionPwd = MD5Util.getEncryptString(recruiterUser.getPwd());
        recruiterUser.setIsAdmin(true);
        recruiterUser.setPwd(encryptionPwd);
        recruiterUserService.saveRecruiterUser(recruiterUser);
        return ResponseEntity.ok(HttpStatus.CREATED);
      }
    }
    return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 管理员创建普通用户 .
   * @Author xiachao
   * @CreateTime 2017/2/15 17:37
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PostMapping("recruiters/{recId}/user")
  public ResponseEntity createRecruiterUser(@RequestBody RecruiterUser recruiterUser,
                                            @PathVariable UUID recId) {
    if (recruiterUser != null) {
      Subject currentUser = SecurityUtils.getSubject();
      if (currentUser != null) {
        if (currentUser.isAuthenticated()) {
          AuthUser authUser = (AuthUser) currentUser.getPrincipal();
          UUID userId = authUser.getUid();
          boolean flag = recruiterUserService.findRoleById(userId);
          if (flag == true) {
            if (recruiterUser.getPwd() != null && !("".equals(recruiterUser.getPwd()))) {
              String encryptionPwd = MD5Util.getEncryptString(recruiterUser.getPwd());
              if (!("".equals(encryptionPwd))) {
                RecruiterInfo recruiterInfo = new RecruiterInfo();
                recruiterInfo.setId(recId);
                recruiterUser.setRecruiterInfo(recruiterInfo);
                recruiterUser.setPwd(encryptionPwd);
                recruiterUser.setStatus("using");
                recruiterUserService.saveRecruiterUser(recruiterUser);
                return ResponseEntity.ok(HttpStatus.CREATED);
              }
            }
          } else {
            logger.error("该用户非管理员，权限不够，不能创建用户");
            return ResponseEntity.ok("该用户非管理员，权限不够，不能创建用户");
          }
        }
      }
    }
    return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 管理员修改公司用户信息（管理员可修改公司下用户的所有信息）.
   * @Author xiachao
   * @CreateTime 2017/2/15 17:45
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PutMapping("recruiters/{recId}/users/{userId}")
  public ResponseEntity<RecruiterUser> updateRecruiterUser(@PathVariable UUID recId,
                                                           @RequestBody RecruiterUser recruiterUserDto,
                                                           @PathVariable UUID userId) {
    if (recId != null && userId != null) {
      Subject currentUser = SecurityUtils.getSubject();
      if (currentUser != null) {
        if (currentUser.isAuthenticated()) {
          AuthUser authUser = (AuthUser) currentUser.getPrincipal();
          //获取管理员id
          UUID userId2 = authUser.getUid();
          boolean flag = recruiterUserService.findRoleById(userId2);
          //如果该用户是管理员
          if (flag == true) {
            //如果是管理员 修改普通用户信息
            RecruiterUser ordinaryRecruiterUser = recruiterUserService.findById(userId);
            ordinaryRecruiterUser.setEmail(recruiterUserDto.getEmail());
            ordinaryRecruiterUser.setRealName(recruiterUserDto.getRealName());
            ordinaryRecruiterUser.setTel(recruiterUserDto.getTel());
            ordinaryRecruiterUser.setDeptId(recruiterUserDto.getDeptId());
            ordinaryRecruiterUser.setDeptName(recruiterUserDto.getDeptName());
            ordinaryRecruiterUser.setMobile(recruiterUserDto.getMobile());
            if (recruiterUserDto.getPwd() != null) {
              ordinaryRecruiterUser.setPwd(MD5Util.getEncryptString(recruiterUserDto.getPwd()));
            } else {
              ordinaryRecruiterUser.setPwd(MD5Util.getEncryptString(""));
            }
            recruiterUserService.saveRecruiterUser(ordinaryRecruiterUser);
            return ResponseEntity.ok(ordinaryRecruiterUser);
          } else {
            logger.error("该用户非管理员，权限不够，不能修改其它用户信息");
            return ResponseEntity.ok(null);
          }
        }
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 管理员删除公司普通用户.
   * @Author xiachao
   * @CreateTime 2017/2/15 17:56
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @DeleteMapping("recruiters/{recId}/users/{userId}")
  public ResponseEntity deleteRecruiterUser(@PathVariable UUID recId,
                                            @PathVariable UUID userId) {
    if (recId != null && userId != null) {
      Subject currentUser = SecurityUtils.getSubject();
      if (currentUser != null) {
        if (currentUser.isAuthenticated()) {
          AuthUser authUser = (AuthUser) currentUser.getPrincipal();
          //获取管理员id
          UUID userId2 = authUser.getUid();
          boolean flag = recruiterUserService.findRoleById(userId2);
          if (flag == true) {
            //删除用户时  先删除他所拥有的筛选器
            candidateFilterService.deleteCandidateFilterByRecruiterUser(userId);
            //删除该用户
            recruiterUserService.deleteRecruiterUser(userId);
            return ResponseEntity.ok(null);
          } else {
            logger.error("该用户非管理员，权限不够，不能删除用户");
            return ResponseEntity.ok("该用户非管理员，权限不够，不能删除用户");
          }
        }
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }

  /**
   * @Description 用户修改自己密码 .
   * @Author xiachao
   * @CreateTime 2017/2/15 17:54
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PutMapping("recruiters/{recId}/users/{id}/pwd")
  public ResponseEntity<String> updateRecruiterUserPwd(@RequestBody RecruiterUserPwdDTO userPwdDTO,
                                                       @PathVariable UUID recId,
                                                       @PathVariable UUID id) {
    if (recId != null && id != null) {
      RecruiterUser recruiterUser = recruiterUserService.findById(id);
      if (userPwdDTO.getNewPwd().equals(userPwdDTO.getNewPwdConfirm())) {
        if (MD5Util.compare(userPwdDTO.getOldPwd(), recruiterUser.getPwd())) {
          recruiterUser.setPwd(MD5Util.getEncryptString(userPwdDTO.getNewPwd()));
          recruiterUserService.saveRecruiterUser(recruiterUser);
          return ResponseEntity.ok("修改成功");
        }
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }


  /**
   * @Description 用户修改个人信息 .
   * @Author xiachao
   * @CreateTime 2017/2/23 16:21
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PutMapping("recruiters/{recId}/users/self/{id}")
  public ResponseEntity<RecruiterUser> updateSelfInfo(@RequestBody RecruiterUser recruiterUserDto,
                                                      @PathVariable UUID recId,
                                                      @PathVariable UUID id) {
    if (recId != null && id != null) {
      RecruiterUser recruiterUser = recruiterUserService.findById(id);
      recruiterUser.setRealName(recruiterUserDto.getRealName());
      recruiterUser.setMobile(recruiterUserDto.getMobile());
      recruiterUser.setTel(recruiterUserDto.getTel());
      recruiterUserService.saveRecruiterUser(recruiterUser);
      return ResponseEntity.ok(recruiterUser);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }


  /**
   * @Description 管理员修改普通用户状态.
   * @Author xiachao
   * @CreateTime 2017/3/3 8:52
   * @Param
   * @Return
   */
  @RequiresRoles("COMPANY")
  @PutMapping("/recruiters/{recId}/users/{userId}/status")
  public ResponseEntity<String> updateRecruiterUserStatus(@PathVariable UUID userId,
                                                          String status) {
    if (status != null && !"".equals(status)) {
      Subject currentUser = SecurityUtils.getSubject();
      if (currentUser != null) {
        if (currentUser.isAuthenticated()) {
          AuthUser authUser = (AuthUser) currentUser.getPrincipal();
          //获取管理员id
          UUID userId2 = authUser.getUid();
          boolean flag = recruiterUserService.findRoleById(userId2);
          if (flag == true) {
            recruiterUserService.updateRecruiterUserStatus(status, userId);
            return ResponseEntity.ok(status);
          } else {
            logger.error("该用户权限不够，不能修改其它用户状态");
            return ResponseEntity.ok("该用户权限不够，不能修改其它用户状态");
          }
        }
      }
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .cacheControl(CacheControl.noCache())
        .body(null);
  }
}
