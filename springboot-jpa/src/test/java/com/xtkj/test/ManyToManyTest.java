package com.xtkj.test;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xtkj.Application;
import com.xtkj.dao.RoleRepository;
import com.xtkj.domain.Menu;
import com.xtkj.domain.Role;

//多对多关联关系的测试
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class ManyToManyTest {

	@Autowired
	private RoleRepository roleRepository;
	
	//多对多关系的添加操作
	@Test
	public void testSave() {
		//创建角色对象
		Role role = new Role();
		role.setRolename("项目经理");
		//创建菜单对象
		Menu menu = new Menu();
		menu.setFatherid(0);
		menu.setMenuname("xxx管理系统");
		Menu menu2 = new Menu();
		menu2.setFatherid(1);
		menu2.setMenuname("项目管理");
		//关联
		role.getMenus().add(menu);
		role.getMenus().add(menu2);
		menu.getRoles().add(role);
		menu2.getRoles().add(role);
		//保存
		this.roleRepository.save(role);
	}
	
	//多对多关系的查询操作
	@Test
	public void testFind() {
		Optional<Role> role = this.roleRepository.findById(2);
		System.out.println(role.get());
	}
	
}
