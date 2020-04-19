package com.xtkj.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.xtkj.domain.User;
import com.xtkj.service.UserService;

/*
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm{
	@Autowired
	private UserService userService;

	//执行授权逻辑
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("执行授权逻辑");
		//给资源进行授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//添加资源的授权字符串
		//info.addStringPermission("user:add");
		
		//到数据库查询当前登陆用户的授权字符串
		//获取当前登陆用户
		Subject subject = SecurityUtils.getSubject();
		User user = (User)subject.getPrincipal();
		User dbuser = userService.findById(user.getId());
		//根据角色来进行授权
		info.addStringPermission(dbuser.getPerms());
		return info;
	}

	//执行认证逻辑
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		System.out.println("执行认证逻辑");
		//编写shiro判断逻辑,判断用户名或密码
		//1.判断用户名
		//获取令牌
		UsernamePasswordToken token = (UsernamePasswordToken)arg0;
		//根据用户名去查数据库
		User user = userService.findByAccount(token.getUsername());
		if(null==user) {
			//用户名不存在
			return null;	//shiro底层会抛出UnknownAccountException
		}
		//2.判断密码
		return new SimpleAuthenticationInfo(user, user.getPassword(), "");
	}

}
