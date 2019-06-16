package com.hlj.proj.service.user.identity;


import com.hlj.proj.dto.user.IdentityInfoDTO;

/**
 * @ClassName IdentityService
 * @Author TD
 * @Date 2019/6/3 20:54
 * @Description 用户认证信息接口
 */
public interface IdentityService {

    /**
     * 根据用户ID获取当前登陆用户信息
     * @param userId
     * @return
     */
    IdentityInfoDTO getUserInfo(Long userId);
}
