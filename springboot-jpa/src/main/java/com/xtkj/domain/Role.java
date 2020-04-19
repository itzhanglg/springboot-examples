package com.xtkj.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_roles")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="roleid")
	private Integer roleid;
	
	@Column(name="rolename")
	private String rolename;

	//一对多关系
	@OneToMany(mappedBy="role")		//添加user中外键对象属性
	private Set<User> users = new HashSet<>();
	
	//@JoinTable:映射中间表
	//中间表中属性joinColumns:当前表主键所关联的中间表中的外键字段
	//添加数据时级联新建,fetchType.EAGER表示关系类在主体类加载的时候同时加载，立即加载
	@ManyToMany(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinTable(name="t_role_menu",joinColumns=@JoinColumn(name="role_id"),inverseJoinColumns=@JoinColumn(name="menu_id"))
	private Set<Menu> menus = new HashSet<>();
	
	public Role() {
		// TODO Auto-generated constructor stub
	}
	public Role(Integer roleid, String rolename, Set<User> users) {
		super();
		this.roleid = roleid;
		this.rolename = rolename;
		this.users = users;
	}

	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Set<Menu> getMenus() {
		return menus;
	}
	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	@Override
	public String toString() {
		return "Role [roleid=" + roleid + ", rolename=" + rolename + ", menus=" + menus + "]";
	}
	
}
