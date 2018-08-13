package com.jfshare.mvp.server.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.model.TbProductItemShow;
import com.jfshare.mvp.server.model.TbProductPromotion;
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
	
	@Autowired
	private PromotionSettingService promotionSettingService;
	
	@ApiOperation(value="获取推广商品", 
			notes="获取所有目前已经配置的推广商品")
	@GetMapping("/promotionProducts")
	public ResultConstant getPromotionProducts() {
		List<TbProductPromotion> tbProductPromotions = promotionSettingService.getProductPromotions();
		if (!CollectionUtils.isEmpty(tbProductPromotions)) {
			return ResultConstant.ofSuccess(ConvertBeanToMapUtils.convertBeanListToMap(tbProductPromotions));
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取推广商品失败！");
	}
	
	@ApiOperation(value="获取前端展示的商品类目", 
			notes="获取所有目前已经配置的类目")
	@GetMapping("/productItemShows")
	public ResultConstant getProductItemShows() {
		List<TbProductItemShow> tbProductItemShows = promotionSettingService.getProductItemShows();
		if (!CollectionUtils.isEmpty(tbProductItemShows)) {
			return ResultConstant.ofSuccess(ConvertBeanToMapUtils.convertBeanListToMap(tbProductItemShows, "products"));
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取类目商品展示失败！");
	}
	
	@ApiOperation(value="获取类目商品详情", 
			notes="根据itemShowNo获取对应类目下的所有商品详情")
	@GetMapping("/productShowDetail")
	public ResultConstant getProductShowDetail() {
		List<TbProductItemShow> tbProductItemShows = promotionSettingService.getProductItemShows();
		if (!CollectionUtils.isEmpty(tbProductItemShows)) {
			return ResultConstant.ofSuccess(ConvertBeanToMapUtils.convertBeanListToMap(tbProductItemShows));
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取类目商品展示失败！");
	}

}
