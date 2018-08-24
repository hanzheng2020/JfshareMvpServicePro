package com.jfshare.mvp.server.config;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * 通用配置类
 * @author fengxiang
 * @date 2018-07-02
 */
@Configuration
public class CommonConfig {
	
	/**
	 * restTemplate 配置
	 * @return
	 */
	@Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        return restTemplate;
    }
	
	/**
	 * 时间格式转换
	 * @return
	 */
	@Bean
	public Converter<String, Date> DateConvert() {
		return new Converter<String, Date>() {
			public Date convert(String source) {
				if (StringUtils.isEmpty(source)) {
					return null;
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = null;
				try {
					date = sdf.parse((String) source);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return date;
			}
		};
	}
	
	
}
