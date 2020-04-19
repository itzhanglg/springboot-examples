package com.xtkj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtkj.domain.User;
import com.xtkj.mapper.UserMapper;
import com.xtkj.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findByAccount(String account) {
		return userMapper.findByAccount(account);
	}

	@Override
	public User findById(int id) {
		return userMapper.findById(id);
	}

	
	
}
