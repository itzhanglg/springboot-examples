package com.xtkj.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_menus")
public class Menu {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="menuid")
	private Integer menuid;
	
	@Column(name="menuname")
	private String menuname;
	
	@Column(name="menuurl")
	private String menuurl;
	
	@Column(name="fatherid")
	private Integer fatherid;
	
	@ManyToMany(mappedBy="menus")	//mappedBy：建立类之间的双向关联，保证数据一致性
	private Set<Role> roles = new HashSet<>();

	public Integer getMenuid() {
		return menuid;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getMenuurl() {
		return menuurl;
	}

	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl;
	}

	public Integer getFatherid() {
		return fatherid;
	}

	public void setFatherid(Integer fatherid) {
		this.fatherid = fatherid;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Menu [menuid=" + menuid + ", menuname=" + menuname + ", menuurl=" + menuurl + ", fatherid=" + fatherid
				+ "]";
	}
}
