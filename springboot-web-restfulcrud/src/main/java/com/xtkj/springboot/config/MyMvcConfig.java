package com.xtkj.springboot.config;

import com.xtkj.springboot.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zlg
 * @create 2019-01-22 21:22
 */
//使用WebMvcConfigurer可以来扩展SpringMVC的功能
//@EnableWebMvc 所有的SpringMVC的自动配置都失效了
//@EnableWebMvc 不用接管springmvc
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送 /athello 请求来到 success
        registry.addViewController("/athello").setViewName("success");
    }

    //所有的WebMvcConfigurer组件都会一起起作用
    @Bean   //将组件注册在容器
    public WebMvcConfigurer webMvcConfigurer(){
        //接口对象：匿名内部类
        WebMvcConfigurer configurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                //它会找templates文件夹下的文件是因为模版映射的关系,templates为Spring Boot默认jar包使用嵌入式的Tomcat
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
           /* @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //静态资源；  *.css , *.js
                //SpringBoot已经做好了静态资源映射
//              .excludePathPatterns代表这些请求不过滤
//              asserts为resources下static下的文件夹，webjars则是maven导入的一些前端框架
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html","/","/user/login","/asserts/**","/webjars/**");
            }*/

            //配置访问静态资源
            /*@Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
            }*/
        };
        return configurer;
    }

    //区域信息对象
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
