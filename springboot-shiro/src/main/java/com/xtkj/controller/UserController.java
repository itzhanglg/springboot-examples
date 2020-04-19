package com.xtkj.controller;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

	/*
	 * 测试springboot方法,返回json字符串
	 */
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		System.out.println("UserController.hello()");
		return "ok";
	}
	
	/*
	 * 测试thymeleaf
	 */
	@RequestMapping("/testThymeleaf")
	public String testThymeleaf(Model model) {
		//把数据存入model
		model.addAttribute("name", "用户认证");
		//返回test.html
		return "test";
	}
	
	//跳转到添加页面
	/*@RequestMapping("/add")
	public String add() {
		return "user/add";
	}*/
	//跳转到修改页面
	/*@RequestMapping("/update")
	public String update() {
		return "user/update";
	}*/
	
	//登陆页面
	@RequestMapping("/login")
	public String login(String account,String password,Model model) {
		System.out.println(account+":"+password);
		//使用shiro进行认证
		//1.获取subject
		Subject subject = SecurityUtils.getSubject();
		//2.封装用户数据
		UsernamePasswordToken token = new UsernamePasswordToken(account, password);
		//3.执行登陆方法
		try {
			//执行下面方法底层会自动调用realm里的执行认证逻辑方法
			subject.login(token);
			//登陆成功
			return "redirect:/testThymeleaf";
		} catch (UnknownAccountException e) {
			//登陆失败
			model.addAttribute("msg","用户名不存在");
			return "login";
		} catch (IncorrectCredentialsException e) {
			//登陆失败
			model.addAttribute("msg","密码错误");
			return "login";
		}
		
	}
	
}
