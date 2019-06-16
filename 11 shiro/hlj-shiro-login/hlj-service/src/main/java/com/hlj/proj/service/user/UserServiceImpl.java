package com.hlj.proj.service.user;

import com.hlj.proj.api.user.UserService;
import com.hlj.proj.data.dao.mybatis.manager.system.SysUserRoleManager;
import com.hlj.proj.data.dao.mybatis.manager.user.UserInfoManager;
import com.hlj.proj.data.pojo.user.UserInfo;
import com.hlj.proj.data.pojo.user.UserInfoQuery;
import com.hlj.proj.dto.user.UserDTO;
import com.hlj.proj.enums.ResponseEnum;
import com.hlj.proj.exception.BusinessException;
import com.hlj.proj.utils.BeanUtils;
import com.hlj.proj.utils.PasswordHash;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Author DYB
 * @Date 2019/4/12 9:53
 * @Description
 * @Version V1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoManager userInfoManager;

    /**
     * 查询用户信息
     * 查询顺序：
     * 1.如果有用户ID，则根据用户ID查询为唯一标识
     * 2.如果有用户的邮箱，则先查询邮箱为唯一标识
     * 3.如果有用户的手机号，则先查询手机号为唯一标识
     * 4.剩下的条件查询
     * 公共方法，可以重写
     */
    @Override
    public UserDTO queryUserInfo(UserDTO userDTO) {
//        辨识度，依次是 用户ID ->  用户名
        UserInfo scfUserInfo = null;
        if (userDTO == null) {
            throw new BusinessException(ResponseEnum.用户不存在);
        }
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StringUtils.isNotBlank(username)) {
            UserInfoQuery query = new UserInfoQuery();
            query.setUsername(userDTO.getUsername());
            query.setUserType(userDTO.getUserType());
            scfUserInfo = userInfoManager.findByQueryContion(query);
        }
        if (scfUserInfo == null) {
            throw new BusinessException(ResponseEnum.用户不存在);
        }
        boolean flag = false;
        if (StringUtils.isNotBlank(password)) {
            try {
                flag = PasswordHash.validatePassword(password, scfUserInfo.getPassword());
            } catch (Exception e) {
                log.error("密码对比时出现异常", e);
                throw new BusinessException("密码对比时出现异常");
            }
            if (!flag) {
                throw new BusinessException("密码错误");
            }
        }
        return BeanUtils.toUserDTO(scfUserInfo);
    }

}