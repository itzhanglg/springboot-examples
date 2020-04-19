package com.xtkj.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.xtkj.domain.User;

//Repository接口的方法名称命名查询
public interface UserRepositoryByName extends Repository<User, Integer> {
	
	//方法名称必须遵循驼峰式命名规则。
	//findBy(关键字)+实体属性名(首字母大写)+查询条件(首字母大写)
	public List<User> findByName(String name); 
	
	public List<User> findByNameAndAge(String name,Integer age);
	
	public List<User> findByNameLike(String name);
	
}
