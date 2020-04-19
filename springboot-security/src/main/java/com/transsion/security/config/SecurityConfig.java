package com.transsion.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author zlg
 * @create 2020-03-11 22:20
 */

/**
 * 1.pom文件引入springsecurity的jar包
 * 2.编写securityConfig类,开启 @EnableWebSecurity 安全配置类注解,
 *   继承 WebSecurityConfigurerAdapter
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //授权:定制请求的授权规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("userA")
                .antMatchers("/level2/**").hasRole("userB")
                .antMatchers("/level3/**").hasRole("userC")
            .and()
            /**
             * http.formLogin() 开启自动配置的登录功能
             * 若没有登录或权限会自动到登录页面
             * 1./login 自动生成登录页 2.登录失败重定向到 /login?error
             * 3.自定义登录页: 默认post形式的 /login 代表处理登录
             * 4.只要定制loginPage,loginPage("/userlogin")里的请求与表单登录请求一致
             */
            .formLogin()
                .loginPage("/userlogin")
//                .loginProcessingUrl("/login")   //设置登录表单发送请求的url
                .usernameParameter("name")
                .passwordParameter("pwd")
            .and()
            /**
             * http.logout() 开启自动配置的注解功能
             * 1./logout 表示用户注销,情况session
             * 2.注销成功会返回到 /login?logout 页面
             */
            .logout()
                .logoutSuccessUrl("/"); //注销成功回到首页

        /**
         * 开启记住我功能
         * 登录成功后将cookie发给浏览器保存,以后访问页面带上该cookie,只要通过检查就可以免登录
         * 点击注销会删除cookie
         */
        http.rememberMe().
                rememberMeParameter("remember");//自定义登录页设置checkbox的name参数

    }

    //认证: 定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        /**
         * inMemoryAuthentication() : 在内存中拿用户名或密码
         */
        auth.
            inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())   //用户注册时加密
                    .withUser("jack").  //下面对存储到内存数据加密
                    password(new BCryptPasswordEncoder().encode("123"))
                    .roles("userA","userB")
                .and()
                .passwordEncoder(new BCryptPasswordEncoder())
                    .withUser("tom")
                    .password(new BCryptPasswordEncoder().encode("123"))
                    .roles("userA","userC")
                .and()
                .passwordEncoder(new BCryptPasswordEncoder())
                    .withUser("mary")
                    .password(new BCryptPasswordEncoder().encode("123"))
                    .roles("userB","userC");

    }
}
