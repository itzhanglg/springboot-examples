package com.xtkj.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

//完成对redis整合的一些配置
@Configuration
public class RedisConfig {

	/*
	 * 1.创建JedisPoolConfig对象，在该对象中完成一些连接池配置
	 */
//	@Bean
//	public JedisPoolConfig getJedisPoolConfig() {
//		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//		//连接池最大空闲连接
//		jedisPoolConfig.setMaxIdle(8);
//		//连接池最小空闲连接
//		jedisPoolConfig.setMinIdle(0);
//		//连接池最大连接数
//		jedisPoolConfig.setMaxTotal(8);
//		return jedisPoolConfig;
//	}
	
	/*
	 * 2.创建JedisConnectionFactory对象,配置redis连接信息
	 *  @ConfigurationProperties(prefix="spring.redis"):会将前缀相同的内容创建一个实体
	 */
//	@Bean
//	public JedisConnectionFactory getJedisConnectionFactory(JedisPoolConfig config) {
//		JedisConnectionFactory factory = new JedisConnectionFactory();
//		//关联连接池配置
//		factory.setPoolConfig(config);
//		//配置连接redis信息
//		factory.setHostName("192.168.91.100");	//主机地址
//		factory.setPort(6379);	//端口
//		return factory;
//	}
	
	/*
	 * 3.创建RedisTemplate:用于执行Redis操作方法
	 */
	@Bean
	public RedisTemplate<String, Object> getRedisTemplate(JedisConnectionFactory factory){
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		//关联连接工厂
		template.setConnectionFactory(factory);
		//为key设置序列化
		template.setKeySerializer(new StringRedisSerializer());
		//为value设置序列化
		template.setValueSerializer(new StringRedisSerializer());
		return template;
	}
	
}
