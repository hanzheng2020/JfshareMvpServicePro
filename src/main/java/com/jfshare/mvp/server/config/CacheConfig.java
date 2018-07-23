package com.jfshare.mvp.server.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
	
	//缓存失效时间5分钟
	private long expireTime = 30;
	//设置缓存key前缀
	private String prefix = "cache-";
	
	@Bean
	public CacheManager cacheManager() {
	    RedisCacheManager cacheManager= new RedisCacheManager(
	    		RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory()), 
	    		RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMillis(expireTime)).prefixKeysWith(prefix));
	    
	    return cacheManager;
	}
}
