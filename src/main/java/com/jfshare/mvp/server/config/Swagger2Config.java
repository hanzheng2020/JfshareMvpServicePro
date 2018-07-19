package com.jfshare.mvp.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author fengxiang
 * @date 2018-07-19
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
	
	@Value("${enableApi}")
	private boolean enableApi;

	/**
	 * 自动生成swagger api
	 * @return
	 */
	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(new ApiInfoBuilder()
		                //页面标题
		                .title("聚分享MVP项目 API")
		                //版本号
		                .version("1.0")
		                .build())
				.select()
				.apis(RequestHandlerSelectors.any()) 
				.paths(PathSelectors.any()) 
				.build().enable(enableApi);
	}
}
