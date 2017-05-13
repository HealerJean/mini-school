package com.minixiao.api.recruiter.service.recruiters;

import com.minixiao.api.recruiter.dto.recruiters.RecruiterUserDTO;
import com.minixiao.api.recruiter.dto.recruiters.RecruiterUserExceptPwd;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterUser;

import java.util.List;
import java.util.UUID;


/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/13 11:08
 */

public interface RecruiterUserService {
  /**
   * .
   *
   * @param recruiterUser r
   */
  void saveRecruiterUser(RecruiterUser recruiterUser);

  /**
   * .
   *
   * @param id r
   */
  void deleteRecruiterUser(UUID id);

  /**
   * .
   *
   * @param id r
   */
  RecruiterUser findById(UUID id);

  /**
   * .
   */
  List<RecruiterUser> findByRecruiterInfo(RecruiterInfo recruiterInfo);

  /**
   * 根据用户id查找该用户是否是管理员.
   *
   * @param id 用户id
   * @return boolean
   */
  boolean findRoleById(UUID id);

  /**
   * 更新用户状态.
   */
  void updateRecruiterUserStatus(String status, UUID id);

  /**
   * .
   *
   * @param email r
   */
  RecruiterUserDTO findByEmailAndStatus(String email, String status);

  /**
   * 根据公司Id查询该公司下的所有用户 并按照创建时间输出.
   *
   * @return list
   */
  List<RecruiterUser> findByRecId(UUID recId, UUID userId);

  /**
   * 根据用户id 查询该用户 不查询该用户密码.
   *
   * @param id id
   * @return RecruiterUser
   */
  RecruiterUserExceptPwd findUserById(UUID id);
}
