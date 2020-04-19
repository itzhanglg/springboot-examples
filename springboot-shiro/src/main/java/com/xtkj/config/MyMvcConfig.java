package com.xtkj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//使用WebMvcConfigurer可以来扩展SpringMVC的功能
//@EnableWebMvc 所有的SpringMVC的自动配置都失效了
//@EnableWebMvc 不用接管springmvc
@Configuration
public class MyMvcConfig implements WebMvcConfigurer{
	//所有的WebMvcConfigurer组件都会一起起作用
	@Bean
	public WebMvcConfigurer getWebMvcConfigurer() {
		WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				//它会找templates文件夹下的文件是因为模版映射的关系,templates为Spring Boot默认jar包使用嵌入式的Tomcat
				registry.addViewController("/add").setViewName("user/add");
				registry.addViewController("/update").setViewName("user/update");
				registry.addViewController("/notlogin").setViewName("login");
				registry.addViewController("/unauth").setViewName("unauth");
				
			}
			
			
			
		};
		return webMvcConfigurer;
	}
	
}
