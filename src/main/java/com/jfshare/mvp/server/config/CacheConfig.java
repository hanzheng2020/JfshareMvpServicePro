package com.jfshare.mvp.server.config;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.util.Assert;

/**
 * 使用redis自定义CacheManager，设置过期时间
 * @author fengxiang
 * @date 2018-07-20
 */
@Configuration
@EnableCaching
public class CacheConfig {
	
	@Autowired  
    private RedisTemplate<String, Object> redisTemplate;
	
	//缓存失效时间5分钟
	private long expireTime = 5;
	//设置缓存key前缀
	private String prefix = "cache-";
	
	@Bean
	public CacheManager cacheManager() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		registerDefaultConverters(conversionService);
	    RedisCacheManager cacheManager= new RedisCacheManager(
	    		RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory()), 
	    		RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMillis(expireTime)).prefixKeysWith(prefix));
	    
	    return cacheManager;
	}
	
	private static void registerDefaultConverters(ConverterRegistry registry) {

		Assert.notNull(registry, "ConverterRegistry must not be null!");

		registry.addConverter(String.class, byte[].class, source -> source.getBytes(StandardCharsets.UTF_8));
		registry.addConverter(SimpleKey.class, String.class, SimpleKey::toString);
	}
}