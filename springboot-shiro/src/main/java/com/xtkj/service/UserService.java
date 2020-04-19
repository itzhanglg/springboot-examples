package com.xtkj.service;

import com.xtkj.domain.User;

public interface UserService {
	
	public User findByAccount(String account);

	public User findById(int id);
	
}
