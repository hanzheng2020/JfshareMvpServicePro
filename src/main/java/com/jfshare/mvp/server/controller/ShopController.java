package com.jfshare.mvp.server.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.mapper.TbProductItemShow;
import com.jfshare.mvp.server.mapper.TbProductPromotion;
import com.jfshare.mvp.server.service.PromotionSettingService;
import com.jfshare.mvp.server.utils.ConvertBeanToMapUtils;

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
	
	private PromotionSettingService productPromotionService;
	
	@ApiOperation(value="获取推广商品", 
			notes="获取所有目前已经配置的推广商品")
	@GetMapping("/promotionProducts")
	public ResultConstant getPromotionProducts() {
		List<TbProductPromotion> tbProductPromotions = productPromotionService.getProductPromotions();
		if (CollectionUtils.isEmpty(tbProductPromotions)) {
			return ResultConstant.ofSuccess(ConvertBeanToMapUtils.convertBeanToMap(tbProductPromotions));
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取推广商品失败！");
	}
	
	@ApiOperation(value="获取类目商品展示", 
			notes="获取所有目前已经配置的类目商品展示")
	@GetMapping("/productItemShows")
	public ResultConstant getProductItemShows() {
		List<TbProductItemShow> tbProductItemShows = productPromotionService.getProductItemShows();
		if (CollectionUtils.isEmpty(tbProductItemShows)) {
			return ResultConstant.ofSuccess(ConvertBeanToMapUtils.convertBeanToMap(tbProductItemShows));
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取类目商品展示失败！");
	}

}
