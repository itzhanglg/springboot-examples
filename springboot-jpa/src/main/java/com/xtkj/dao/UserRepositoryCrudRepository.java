package com.xtkj.dao;

import org.springframework.data.repository.CrudRepository;

import com.xtkj.domain.User;

//CrudRepository接口
public interface UserRepositoryCrudRepository extends CrudRepository<User, Integer> {

}
