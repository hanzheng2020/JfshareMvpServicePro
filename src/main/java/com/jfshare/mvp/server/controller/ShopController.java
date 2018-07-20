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
@Api(value="api", tags="商城模块相关API")
@RestController
@RequestMapping("/shop")
public class ShopController {
	
	@ApiOperation(value="获取推广商品", 
			notes="获取目前已经配置的推广商品")
	@GetMapping("/promotionProducts")
	public ResultConstant getPromotionProducts() {
		return null;
	}

}
