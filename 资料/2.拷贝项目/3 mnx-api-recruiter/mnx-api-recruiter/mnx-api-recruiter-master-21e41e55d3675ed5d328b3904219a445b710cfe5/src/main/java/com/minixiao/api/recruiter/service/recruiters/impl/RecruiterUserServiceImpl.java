package com.minixiao.api.recruiter.service.recruiters.impl;

import com.minixiao.api.recruiter.dto.recruiters.RecruiterUserDTO;
import com.minixiao.api.recruiter.dto.recruiters.RecruiterUserExceptPwd;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterInfo;
import com.minixiao.api.recruiter.entity.recruiters.RecruiterUser;
import com.minixiao.api.recruiter.repository.recruiters.RecruiterUserRepository;
import com.minixiao.api.recruiter.service.recruiters.RecruiterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @Description .
 * @Author xiachao
 * @CreateTime 2017/2/13 18:02
 */
@Service
@Transactional(readOnly = true)
public class RecruiterUserServiceImpl implements RecruiterUserService {
  @Autowired
  private RecruiterUserRepository recruiterUserRepository;

  @Override
  @Transactional(readOnly = false)
  public void saveRecruiterUser(RecruiterUser recruiterUser) {
    recruiterUserRepository.save(recruiterUser);
  }

  @Override
  @Transactional(readOnly = false)
  public void deleteRecruiterUser(UUID id) {
    recruiterUserRepository.delete(id);
  }

  @Override
  public RecruiterUser findById(UUID id) {
    return recruiterUserRepository.findById(id);
  }


  @Override
  public List<RecruiterUser> findByRecruiterInfo(RecruiterInfo recruiterInfo) {
    return recruiterUserRepository.findByRecruiterInfoOrderByCreatedOnDesc(recruiterInfo);
  }

  @Override
  public boolean findRoleById(UUID id) {
    return recruiterUserRepository.findRoleById(id);
  }

  /**
   * 更新用户状态.
   */
  @Override
  @Transactional(readOnly = false)
  public void updateRecruiterUserStatus(String status,UUID id) {
    recruiterUserRepository.updateRecruiterUserStatus(status,id);
  }

  @Override
  public RecruiterUserDTO findByEmailAndStatus(String email, String status) {
    RecruiterUser user = recruiterUserRepository.findByEmailAndStatus(email, status);
    if (user != null) {
      RecruiterUserDTO reUser = new RecruiterUserDTO();
      reUser.setId(user.getId());
      reUser.setStatus(user.getStatus());
      reUser.setDeptId(user.getDeptId());
      reUser.setDeptName(user.getDeptName());
      reUser.setEmail(user.getEmail());
      reUser.setIsAdmin(user.getIsAdmin());
      reUser.setPwd(user.getPwd());
      reUser.setRealName(user.getRealName());
      reUser.setTel(user.getTel());
      reUser.setRecId(user.getRecruiterInfo().getId());
      reUser.setRecName(user.getRecruiterInfo().getShortName());
      return reUser;
    } else {
      return null;
    }

  }

  /**
   * 根据公司Id查询该公司下的所有用户 并按照创建时间输出.
   *
   * @return list
   */
  @Override
  public List<RecruiterUser> findByRecId(UUID recId,UUID userId) {
    return recruiterUserRepository.findByRecId(recId,userId);
  }

  /**
   * 根据用户id 查询该用户 不查询该用户密码.
   *
   * @param id id
   * @return RecruiterUser
   */
  @Override
  public RecruiterUserExceptPwd findUserById(UUID id) {
    return recruiterUserRepository.findUserById(id);
  }
}
