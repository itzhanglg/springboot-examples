package com.xtkj.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.xtkj.domain.User;

//Repository接口的@query注解查询
public interface UserRepositoryQueryAnnotation extends Repository<User, Integer> {

	//采用hibernate的hql语句查询(User类名，name实体属性，:name别名 对应@Param中的key)
	@Query("from User where name = :name")
	public List<User> findByNameUserHQL(@Param("name")String name);

	//采用slq语句
	@Query(value="select * from tb_users where username = ?",nativeQuery=true)
	public List<User> findByNameUserSQL(String name);
	
	//更新操作
	@Query("update User set name = :name where id = :id")
	@Modifying	//需要执行一个更新操作
	public void updateUserNameById(@Param("name")String name,@Param("id")Integer id);
	
}
