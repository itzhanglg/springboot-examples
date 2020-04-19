package com.xtkj.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/*
 * 编写shiro配置类
 */
@Configuration
public class ShiroConfig {
	/*
	 * 3.创建ShiroFilterFactoryBean
	 */
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		//关联securityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//添加shiro内置过滤器
		/*shiro内置过滤器，可以实现权限相关的拦截器
		   常用的过滤器：
			anon：无需认证（登陆）可以访问
			authc：必须认证才可以访问
			user：如果使用rememberMe的功能可以直接访问
			perms：该资源必须得到资源权限才可以访问
			role：该资源必须得到角色权限才可以访问*/
		Map<String,String> filterMap = new LinkedHashMap<String,String>();
		/*filterMap.put("/add", "authc");
		filterMap.put("/update", "authc");*/
		filterMap.put("/testThymeleaf", "anon");	//放行
		filterMap.put("/login", "anon");
		
		//授权过滤器(需要放在下面认证语句之前)
		//当授权拦截后，shiro会自动跳转到未授权页面
		filterMap.put("/add", "perms[user:add]");
		filterMap.put("/update", "perms[user:update]");
		
		filterMap.put("/*", "authc");	//拦截
		//修改跳转到的登陆页面,默认是跳到login.jsp
		shiroFilterFactoryBean.setLoginUrl("/notlogin");
		//设置未授权页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
		return shiroFilterFactoryBean;
	}
	
	/*
	 * 2.创建DefaultWebSecurityManager
	 */
	@Bean(name="securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//关联realm
		securityManager.setRealm(userRealm);
		return securityManager;
	}
	
	/*
	 * 1.创建Realm
	 */
	@Bean(name="userRealm")
	public UserRealm getRealm() {
		return new UserRealm();
	}
	
	/*
	 * 配置ShiroDialect,用于thymeleaf和shiro标签配合使用
	 */
	@Bean
	public ShiroDialect getShiroDialect() {
		return new ShiroDialect();
	}
	
}
