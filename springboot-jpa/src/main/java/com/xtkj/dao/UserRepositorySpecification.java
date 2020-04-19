package com.xtkj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xtkj.domain.User;

//JpaSpecificationExecutor接口
public interface UserRepositorySpecification extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
	
}
