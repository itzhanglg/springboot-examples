package com.xtkj.domain;

/**
 * @author admin
 *
 */
public class User {
	private int id;
	private String account;
	private String password;
	private String perms;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(int id, String account, String password, String perms) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.perms = perms;
	}
	public String getPerms() {
		return perms;
	}
	public void setPerms(String perms) {
		this.perms = perms;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", password=" + password + "]";
	}
	
}
