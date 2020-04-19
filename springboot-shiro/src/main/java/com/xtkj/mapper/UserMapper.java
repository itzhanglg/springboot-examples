package com.xtkj.mapper;

import com.xtkj.domain.User;

public interface UserMapper {
	
	public User findByAccount(String account);

	public User findById(int id);
	
}
