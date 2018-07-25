package com.jfshare.mvp.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.github.pagehelper.PageInfo;
import com.jfshare.mvp.server.constants.Constant;
import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.model.TbAppVerinfo;
import com.jfshare.mvp.server.model.TbJfRaiders;
import com.jfshare.mvp.server.model.TbLevelInfo;
import com.jfshare.mvp.server.model.TbProductItem;
import com.jfshare.mvp.server.model.TbProductItemShow;
import com.jfshare.mvp.server.model.TbProductPromotion;
import com.jfshare.mvp.server.service.AppInfoServer;
import com.jfshare.mvp.server.service.JfRaidersService;
import com.jfshare.mvp.server.service.LevelInfoService;
import com.jfshare.mvp.server.service.ProductItemService;
import com.jfshare.mvp.server.service.PromotionSettingService;
import com.jfshare.mvp.server.utils.ConvertBeanToMapUtils;
import com.jfshare.mvp.server.utils.OSSUtils;

/**
 * @author fengxiang
 * @date 2018-07-20
 */
@Api(value = "api", tags = "后台管理模块相关API")
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private PromotionSettingService promotionSettingService;

	@Autowired
	private ProductItemService productItemService;

	@Autowired
	private LevelInfoService levelInfoService;

	@Autowired
	private JfRaidersService jfRaidersService;

	@Autowired
	private AppInfoServer appInfoServer;

	@ApiOperation(value = "更新商品推广设置", notes = "根据传入的推广配置信息，重新配置推广商品")
	@PostMapping("/promotionProducts")
	public ResultConstant updateProductPromotion(ArrayList<TbProductPromotion> tbProductPromotions) {
		boolean result = promotionSettingService.updateProductPromotion(tbProductPromotions);
		if (result) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "更新商品推广设置失败！");
	}

	@ApiOperation(value = "更新类目商品展示设置", notes = "根据传入的类目商品展示信息，重新配置类目商品展示")
	@PostMapping("/productItemShows")
	public ResultConstant updateProductItemShow(ArrayList<TbProductItemShow> tbProductItemShows) {
		boolean result = promotionSettingService.updateProductItemShow(tbProductItemShows);
		if (result) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "更新类目商品展示设置失败！");
	}

	@ApiOperation(value = "更新商品类目", notes = "根据传入的商品类目配置，重新配置商品类目")
	@PutMapping("/productItem")
	public ResultConstant updateProductItem(@RequestParam(required = true) String itemNo,
											@RequestParam(required = true) String itemName, 
											@RequestParam(required = true) String itemDesc) {
		boolean result = productItemService.updateProductItem(itemNo, itemName, itemDesc);
		if (result) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "更新商品类目失败！");
	}

	@ApiOperation(value = "获取商品类目", notes = "根据传入的类目名称，获取类目, 如果itemNo为空，则获取全部的类目树")
	@GetMapping("/productItem")
	public ResultConstant getProductItem(@RequestParam(required = false) String itemNo) {
		List<TbProductItem> tbProductItems = productItemService.getProductItem(itemNo);
		if (CollectionUtils.isEmpty(tbProductItems)) {
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取商品类目失败！");
		}
		return ResultConstant.ofSuccess(ConvertBeanToMapUtils.convertBeanListToMap(tbProductItems));
	}

	@ApiOperation(value = "新增商品类目", notes = "根据传入的商品类目，新增配置商品类目")
	@PostMapping("/productItem")
	public ResultConstant addProductItem(@RequestParam(required = true) String itemName, 
										 @RequestParam(required = true) String itemDesc,
										 @RequestParam(required = false) String parentItemNo) {
		boolean result = productItemService.addProductItem(itemName, itemDesc, parentItemNo);
		if (result) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "新增商品类目失败！");
	}

	@ApiOperation(value = "删除商品类目", notes = "根据传入的商品类目编号，删除商品类目")
	@DeleteMapping("/productItem")
	public ResultConstant deleteProductItem(@RequestParam(required=false) String itemNo) {
		ResultConstant result = productItemService.deleteProductItem(itemNo);
		return result;
	}

	@ApiOperation(value = "订单消费聚金豆", notes = "根据传入的使用类型，进行扣减聚金豆")
	@PostMapping("/openOrDisabledJvjindou")
	public ResultConstant openOrDisabledJvjindou(@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "jvjindou", required = true) Integer jvjindou) {
		if (!StringUtils.isEmpty(userId)) {
			if (jvjindou < Constant.JVJINDOU_NUM) {
				return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "使用聚金豆的数量大于0");
			} else {
				// 走抵扣聚金豆的逻辑
				try {
					levelInfoService.openOrdisableJvjindou(userId, jvjindou);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "参数有误");
		}
		return ResultConstant.ofSuccess();
	}

	@ApiOperation(value = "查询用户聚金豆", notes = "根据传入的userId，返回聚金豆")
	@GetMapping("/selectJvjindou")
	public ResultConstant selectJvjindou(@RequestParam(value = "userId", required = true) Integer userId) {
		if (!StringUtils.isEmpty(userId)) {
			TbLevelInfo levelInfo = levelInfoService.selectByuserid(userId);
			return ResultConstant.ofSuccess(ConvertBeanToMapUtils.convertBeanToMap(levelInfo));
		} else {
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "参数有误");
		}
	}

	@ApiOperation(value = "积分攻略文章添加", notes = "根据传入的类型，添加积分攻略文章")
	@PostMapping("/addjfRaider")
	public ResultConstant addjfRaiders(TbJfRaiders jfRaiders) {
		int result = jfRaidersService.addjfRaiders(jfRaiders);
		if (result < 1) {
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "添加失败");
		}
		return ResultConstant.ofSuccess();

	}

	@ApiOperation(value = "积分攻略文章查询", notes = "根据传入的类型，查询积分攻略文章")
	@GetMapping("/queryjfRaider")
	public ResultConstant queryjfRaiders(TbJfRaiders jfRaiders,
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "pageSize", required = true) Integer pageSize) {
		PageInfo<Map<String, Object>> pageInfo = jfRaidersService.queryJfRaiders(jfRaiders, page, pageSize);
		return ResultConstant.ofSuccess(pageInfo);
	}

	@ApiOperation(value = "积分攻略文章更新", notes = "根据传入的类型，更新积分攻略文章")
	@PutMapping("/updatejfRaider")
	public ResultConstant updatejfRaiders(TbJfRaiders jfRaider,
			@RequestParam(value = "jfRaiderId", required = true) Integer jfRaiderId,
			@RequestParam(value = "jfRaidersImg", required = false) MultipartFile jfRaidersImg) {
		TbJfRaiders jfRaiders = jfRaidersService.queryJfRaidersOne(jfRaiderId);
		if (!StringUtils.isEmpty(jfRaider.getTitle())) {
			jfRaiders.setTitle(jfRaider.getTitle());
		}
		if (!StringUtils.isEmpty(jfRaider.getContent())) {
			jfRaiders.setContent(jfRaider.getContent());
		}
		if (!StringUtils.isEmpty(jfRaider.getImgUrl())) {
			jfRaiders.setImgUrl(jfRaider.getImgUrl());
		}

		if (!StringUtils.isEmpty(jfRaider.getImgUrl())) {
			jfRaiders.setImgUrl(jfRaider.getImgUrl());
		}
		int result = jfRaidersService.updateJfRaiders(jfRaiders);
		if (result > 0) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "修改失败");
	}

	@ApiOperation(value = "积分攻略文章发布", notes = "发布积分攻略文章")
	@PutMapping("/jfRaiderRelease")
	public ResultConstant updatejfRaiders(@RequestParam(value = "id", required = true) Integer id) {
		TbJfRaiders jfRaiders = jfRaidersService.queryJfRaidersOne(id);
		jfRaiders.setStatus(2);
		jfRaiders.setReleaseTime(new Date());
		int result = jfRaidersService.updateJfRaiders(jfRaiders);
		if (result > 0) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "发布失败");
	}

	@ApiOperation(value = "积分攻略文章删除", notes = "根据传入的文章id，删除文章")
	@DeleteMapping("/deletejfRaider")
	public ResultConstant deletejfRaiders(@RequestParam(value = "id", required = true) Integer id) {
		int result = jfRaidersService.deletejfRaiders(id);
		if (result > 0) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "修改失败");
	}

	@ApiOperation(value = "更新app版本", notes = "根据传入的app信息更新app版本号")
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

	@ApiOperation(value = "获取app信息", notes = "根据app类型获取对应的app信息")
	@PostMapping("/getAppVerinfo")
	public ResultConstant getAppVerinfo(@RequestParam(value = "appType", required = true) Integer appType) {
		TbAppVerinfo appVerinfo = appInfoServer.getAppVerinfo(appType);
		if (appVerinfo != null) {
			return ResultConstant.ofSuccess(appVerinfo);
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取app版本信息失败！");
	}
}
