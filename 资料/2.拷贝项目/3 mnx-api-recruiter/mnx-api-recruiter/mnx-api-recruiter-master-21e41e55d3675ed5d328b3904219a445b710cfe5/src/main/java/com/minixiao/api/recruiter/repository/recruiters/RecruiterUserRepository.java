package com.minixiao.api.recruiter.repository.recruiters;

import com.minixiao.api.recruiter.dto.recruiters.RecruiterUserExceptPwd;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/13 17:53
 */

public interface RecruiterUserRepository extends PagingAndSortingRepository<RecruiterUser, UUID> {
  /**
   * 根据ID查找用户 .
   *
   * @param id id
   * @return RecruiterUser
   */
  RecruiterUser findById(UUID id);

  /**
   * 根据公司id查询该公司的所有用户.
   *
   * @return page
   */
  List<RecruiterUser> findByRecruiterInfo(RecruiterInfo recruiterInfo);

  /**
   * 根据用户id查找该用户是否是管理员.
   *
   * @param id 用户id
   * @return boolean
   */
  @Query(value = "select is_admin from tb_rec_user where id = ?1", nativeQuery = true)
  boolean findRoleById(UUID id);

  /**
   * 管理员修改普通用户状态.
   *
   * @param status 状态
   * @param id     用户id
   */
  @Modifying
  @Query(value = "update tb_rec_user set status = ?1 where id = ?2", nativeQuery = true)
  void updateRecruiterUserStatus(String status, UUID id);

  /**
   * 根据邮箱查找用户 .
   *
   * @param email email
   * @return RecruiterUser
   */
  RecruiterUser findByEmailAndStatus(String email, String status);

  /**
   * @param recId  recId
   * @param userId userId
   * @return list
   */
  @Query(value = "select * from tb_rec_user where rec_id = ?1 and id != ?2 order by created_on desc;", nativeQuery = true)
  List<RecruiterUser> findByRecId(UUID recId, UUID userId);

  /**
   * 根据用户id 查询该用户 不查询该用户密码.
   *
   * @param id id
   * @return RecruiterUser
   */
  @Query(value = "select id,real_name,mobile,email,is_admin,dept_id,dept_name,tel,created_on,updated_on from tb_rec_user where id = ?1", nativeQuery = true)
  RecruiterUserExceptPwd findUserById(UUID id);

  /**
   * 根据公司id查询该公司下的所有用户  按照创建时间降序排列.
   *
   * @param recruiterInfo recruiterInfo
   * @return list
   */
  List<RecruiterUser> findByRecruiterInfoOrderByCreatedOnDesc(RecruiterInfo recruiterInfo);
}
