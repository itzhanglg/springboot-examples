package com.xtkj.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.xtkj.Application;
import com.xtkj.domain.User;

//spring data redis测试
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)		//Application是springboot启动类的类名
public class RedisTest {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	//1.添加一个字符串
	@Test
	public void testSet() {
		this.redisTemplate.opsForValue().set("key", "测试redis整合");
	}
	
	//获取一个字符串
	@Test
	public void testGet() {
		String value = (String)this.redisTemplate.opsForValue().get("key");
		System.out.println(value);
	}
	
	//2.添加user实体类
	@Test
	public void testSetUser() {
		User user = new User();
		user.setId(1);
		user.setName("zlg");
		user.setAge("18");
		//重新设置序列化器
		this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		this.redisTemplate.opsForValue().set("user", user);
	}
	
	//根据key获取实体类对象
	@Test
	public void testGetUser() {
		//反序列化,重新设置序列化器
		this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		User user = (User)this.redisTemplate.opsForValue().get("user");
		System.out.println(user);
	}
	
	//3.基于json格式存user对象
	@Test
	public void testSetUserUseJson() {
		User user = new User();
		user.setId(1);
		user.setName("zlg");
		user.setAge("18");
		//重新设置序列化器
		this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
		this.redisTemplate.opsForValue().set("user_json", user);
	}
	
	//基于json格式取对象
	@Test
	public void testGetUserUseJson() {
		//反序列化
		this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
		User user = (User)this.redisTemplate.opsForValue().get("user_json");
		System.out.println(user);
	}
	
}
