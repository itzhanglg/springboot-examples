package com.xtkj.test;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xtkj.Application;
import com.xtkj.dao.UserRepository;
import com.xtkj.domain.Role;
import com.xtkj.domain.User;

//多对一关系测试
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class OneToManyTest {
	
	@Autowired
	private UserRepository userRepository;

	//多对一关系的添加
	@Test
	public void testSave() {
		//创建一个用户
		User user = new User();
		user.setAddress("中国杭州");
		user.setAge(32);
		user.setName("姜文");
		//创建一个角色
		Role role = new Role();
		role.setRolename("管理员");
		//关联
		role.getUsers().add(user);	//主表(先添加主表数据)
		user.setRole(role);			//从表(再添加从表数据)
		//保存
		this.userRepository.save(user);
	}
	
	//多对一关系的查询
	@Test
	public void testFind() {
		Optional<User> user = this.userRepository.findById(5);	//根据id查询User
		System.out.println(user.get());		//打印输出User对象
		String rolename = user.get().getRole().getRolename();	//获取rolename
		System.out.println(rolename);
	}
	
}
