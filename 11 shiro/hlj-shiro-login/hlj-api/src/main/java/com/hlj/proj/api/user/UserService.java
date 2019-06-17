package com.hlj.proj.api.user;


import com.hlj.proj.dto.user.UserDTO;

/**
 * @ClassName
 * @Author TD
 * @Date 2019/4/11 14:47
 * @Description 用户业务类
 */
public interface UserService {

    /**
     * 查询用户信息
     * 查询顺序：
     * 1.如果有用户ID，则根据用户ID查询为唯一标识
     * 2.如果有用户的邮箱，则先查询邮箱为唯一标识
     * 3.如果有用户的手机号，则先查询手机号为唯一标识
     * 4.剩下的条件查询
     * 公共方法，可以重写
     */
    UserDTO queryUserInfo(UserDTO userDTO);

}
