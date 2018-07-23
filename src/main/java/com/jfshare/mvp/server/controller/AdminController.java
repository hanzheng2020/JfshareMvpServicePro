package com.jfshare.mvp.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.mapper.TbProductPromotion;
import com.jfshare.mvp.server.service.ProductPromotionService;

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
	
	@Autowired
	private ProductPromotionService productPromotionService;
	
	@ApiOperation(value="更新商品推广设置", 
			notes="根据传入的推广配置信息，重新配置推广商品")
	@PostMapping("/promotionProducts")
	public ResultConstant updateProductPromotion(List<TbProductPromotion> tbProductPromotions) {
		boolean result = productPromotionService.updateProductPromotion(tbProductPromotions);
		if (result) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "更新失败！");
	}
}
