package com.jfshare.mvp.server.config;

import java.time.Duration;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;

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
	
	private DefaultConversionService defaultConversionService;
	
	@PostConstruct
	private void initConversionService() {
		defaultConversionService = new DefaultConversionService();
		defaultConversionService.addConverter(new Converter<String, T>() {
			@Override
			public T convert(String source) {
				if (StringUtils.isEmpty(source)) {
					return null;
				}
				return JSON.parseObject(source, T.class);
			}
		});
		defaultConversionService.addConverter(new Converter<T, String>() {
			@Override
			public String convert(T source) {
				if (source == null) {
					return null;
				}
				return JSON.toJSONString(source);
			}
		});
	}
	
	/**
	 * 默认cacheManager 失效时间30分钟
	 * @return
	 */
	@Primary
	@Bean
	public CacheManager cacheManager() {
	    RedisCacheManager cacheManager= new RedisCacheManager(
	    		RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory()), 
	    		RedisCacheConfiguration.defaultCacheConfig()
	    		.withConversionService(defaultConversionService)
	    		.entryTtl(Duration.ofMillis(30)).prefixKeysWith(prefix));
	    
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
	    		RedisCacheConfiguration.defaultCacheConfig()
	    		.withConversionService(defaultConversionService)
	    		.entryTtl(Duration.ofMillis(60)).prefixKeysWith(prefix));
	    
	    return cacheManager;
	}
}
