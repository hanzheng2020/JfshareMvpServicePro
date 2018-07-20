package com.jfshare.mvp.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jfshare.mvp.server.constants.ResultConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author fengxiang
 * @date 2018-07-20
 */
@Api(value="api", tags="后台管理模块相关API")
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@ApiOperation(value="更新推广商品", 
			notes="根据传入的推广编号，重新配置推广商品")
	@GetMapping("/promotionProducts")
	public ResultConstant updatePromotionProducts() {
		return null;
	}
	
}
