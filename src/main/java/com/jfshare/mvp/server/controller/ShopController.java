package com.jfshare.mvp.server.controller;

import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.model.TbAppVerinfo;
import com.jfshare.mvp.server.model.TbProductItemShow;
import com.jfshare.mvp.server.service.AppInfoServer;
import com.jfshare.mvp.server.service.PromotionSettingService;
import com.jfshare.mvp.server.utils.ConvertBeanToMapUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author fengxiang
 * @date 2018-07-20
 */
@Api(value = "api", tags = "商城模块相关API")
@RestController
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	private PromotionSettingService promotionSettingService;

	@Autowired
	private AppInfoServer appInfoServer;
	
	@ApiOperation(value = "获取前端展示的推广商品", notes = "获取所有目前已经配置的推广商品")
	@GetMapping("/promotionProducts")
	public ResultConstant getPromotionProducts() {
		List<Map<String, Object>> result = promotionSettingService.getProductPromotionDetails(false);
		if (!CollectionUtils.isEmpty(result)) {
			return ResultConstant.ofSuccess(result);
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取推广商品失败！");
	}
	
	@ApiOperation(value="获取前端展示的商品类目", 
			notes="获取所有目前已经配置的类目")
	@GetMapping("/productItemShows")
	public ResultConstant getProductItemShows() {
		List<TbProductItemShow> tbProductItemShows = promotionSettingService.getProductItemShows();
		if (!CollectionUtils.isEmpty(tbProductItemShows)) {
			return ResultConstant.ofSuccess(ConvertBeanToMapUtils.convertBeanListToMap(tbProductItemShows, "products", "itemNo", "createTime", "updateTime"));
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取类目商品展示失败！");
	}
	
	@ApiOperation(value="获取类目商品详情", 
			notes="根据itemShowNo获取对应类目下的所有商品详情")
	@GetMapping("/productShowDetail")
	public ResultConstant getProductShowDetail(Integer itemShowNo) {
		List<Map<String, Object>> result = promotionSettingService.getProductShowDetail(itemShowNo, false);
		if (!CollectionUtils.isEmpty(result)) {
			return ResultConstant.ofSuccess(result);
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取类目商品详情失败！");
	}

	@ApiOperation(value = "更新app版本", notes = "根据传入的app信息更新app版本号  appType: 1 安卓  3 ios  version:更新的版本号  url:版本下载地址  upgradeDesc:更新说明")
	@PostMapping("/updateAppVerinfo")
	public ResultConstant updateAppVerinfo(@RequestParam(value = "appType", required = true) Integer appType,
			@RequestParam(value = "version", required = true) String version,
			@RequestParam(value = "url", required = true) String url,
			@RequestParam(value = "upgradeDesc", required = true) String upgradeDesc) {
		boolean result = false;
		try {
			result = appInfoServer.updateAppVerinfo(appType, version, url, upgradeDesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "更新app版本失败！");
	}

	@ApiOperation(value = "获取app信息", notes = "根据app类型获取对应的app信息   appType: 1 安卓  3 ios")
	@GetMapping("/getAppVerinfo")
	public ResultConstant getAppVerinfo(Integer appType,String version) {
		TbAppVerinfo appVerinfo = null;
		try {
			appVerinfo = appInfoServer.getAppVerinfo(appType,version);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//if (appVerinfo != null) {
			return ResultConstant.ofSuccess(appVerinfo);
		//}
		//return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取app版本信息失败！");
	}
	
	@ApiOperation(value = "获取app信息", notes = "根据app类型获取对应的app信息   appType: 1 安卓  3 ios")
	@PostMapping("/queryAppVerinfo")
	public ResultConstant queryAppVerinfo(@RequestParam(value = "appType", required = true) Integer appType) {
		TbAppVerinfo appVerinfo = appInfoServer.getCurAppVersion(appType);
		if (appVerinfo != null) {
			return ResultConstant.ofSuccess(appVerinfo);
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取app版本信息失败！");
	}
}
