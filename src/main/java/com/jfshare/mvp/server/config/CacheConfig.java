package com.jfshare.mvp.server.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 使用redis自定义CacheManager，设置过期时间
 * @author fengxiang
 * @date 2018-07-20
 */
@Configuration
@EnableCaching
public class CacheConfig {
	
	@SuppressWarnings("rawtypes")
	@Autowired  
    private RedisTemplate redisTemplate;
	
	//设置缓存key前缀
	private String prefix = "cache-";
	
	/**
	 * 默认cacheManager 失效时间30分钟
	 * @return
	 */
	@Primary
	@Bean
	public CacheManager cacheManager() {
	    RedisCacheManager cacheManager= new RedisCacheManager(
	    		RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory()), 
	    		RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMillis(30)).prefixKeysWith(prefix));
	    
	    return cacheManager;
	}
	/**
	 * 失效时间60分钟
	 * @return
	 */
	@Bean(name="cacheManager60")
	public CacheManager cacheManager60() {
	    RedisCacheManager cacheManager= new RedisCacheManager(
	    		RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory()), 
	    		RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMillis(60)).prefixKeysWith(prefix));
	    
	    return cacheManager;
	}
}
