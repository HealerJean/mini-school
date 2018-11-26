package com.kfit.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kfit.core.bean.UserInfo;
import com.kfit.core.repository.UserInfoRepository;
import com.kfit.core.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	
	@Resource
	private UserInfoRepository userInfoRepository;
	
	@Override
	public UserInfo findByUsername(String username) {
		System.out.println("UserInfoServiceImpl.findByUsername()");
		return userInfoRepository.findByUsername(username);
	}
	
}
