package com.xtkj.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity	//表示该类是实体类
@Table(name="tb_users")	//表示正向工程生成的表名为tb_users
public class User {

	@Id	//表示该字段为主键
	@GeneratedValue(strategy=GenerationType.IDENTITY)	//表示该键生成策略为自增
	@Column(name="id")	//生成的表中对应的字段为id
	private Integer id;
	
	@Column(name="username")
	private String name;
	
	@Column(name="age")
	private Integer age;
	
	@Column(name="address")
	private String address;
	
	//多对一关系
	@ManyToOne(cascade=CascadeType.PERSIST)		//级联添加(添加用户的同时添加相应的角色)
	@JoinColumn(name="role_id")	//维护外键
	private Role role;	//tostring()方法中不能打印输出role对象，编译会报错
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(Integer id, String name, Integer age, String address) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", address=" + address + "]";
	}
	
}
