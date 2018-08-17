package com.jfshare.mvp.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.jfshare.mvp.server.constants.Constant;
import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.model.TbJfRaiders;
import com.jfshare.mvp.server.model.TbJvjindouRule;
import com.jfshare.mvp.server.model.TbProductItemShow;
import com.jfshare.mvp.server.service.JfRaidersService;
import com.jfshare.mvp.server.service.JvjindouRuleService;
import com.jfshare.mvp.server.service.ProductItemService;
import com.jfshare.mvp.server.service.PromotionSettingService;
import com.jfshare.mvp.server.utils.ConvertBeanToMapUtils;

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
	private JvjindouRuleService jvjindouRuleService;

	@Autowired
	private JfRaidersService jfRaidersService;

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "保存推广微页面设置", notes = "保存传入的推广配置和类目商品展示配置")
	@PostMapping("/promotionSetting")
	public ResultConstant savePromotionSetting(String productPromotionJson,
												String productItemShowJson) {
		List<Map> productPromotions = JSON.parseArray(productPromotionJson, Map.class);
		List<Map> productItemShows = JSON.parseArray(productItemShowJson, Map.class);
		boolean result = promotionSettingService.savePromotionSetting(productPromotions, productItemShows);
		if (result) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "保存推广微页面设置失败！");
	}

	@ApiOperation(value = "发布配置的推广微页面", notes = "publishInd = true时，发布上次保存的推广微页面配置")
	@PostMapping("/publishPromotionSetting")
	public ResultConstant publishPromotionSetting(Boolean publishInd) {
		boolean result = promotionSettingService.publishPromotionSetting(publishInd);
		if (result) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "更新类目商品展示设置失败！");
	}
	
	@ApiOperation(value="获取商品推广设置", 
			notes="获取所有目前已经配置的商品推广设置")
	@GetMapping("/promotionProducts")
	public ResultConstant getPromotionProducts(@RequestParam(defaultValue="false", required=false) Boolean publishInd) {
		List<Map<String, Object>> result = promotionSettingService.getProductPromotionDetails(publishInd);
		if (!CollectionUtils.isEmpty(result)) {
			return ResultConstant.ofSuccess(result);
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取推广商品失败！");
	}
	
	@ApiOperation(value="获取类目商品展示设置", 
			notes="获取所有目前已经配置的类目商品展示设置")
	@GetMapping("/productItemShows")
	public ResultConstant getProductItemShows(@RequestParam(defaultValue="false", required=false) Boolean publishInd) {
		List<TbProductItemShow> tbProductItemShows = promotionSettingService.getProductItemShows(publishInd);
		if (!CollectionUtils.isEmpty(tbProductItemShows)) {
			return ResultConstant.ofSuccess(ConvertBeanToMapUtils.convertBeanListToMap(tbProductItemShows, "products"));
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取类目商品展示失败！");
	}

	@ApiOperation(value = "更新商品类目", notes = "根据传入的商品类目配置，重新配置商品类目")
	@PutMapping("/productItem")
	public ResultConstant updateProductItem(String itemNo,
											String itemName, 
											String itemDesc) {
		boolean result = productItemService.updateProductItem(itemNo, itemName, itemDesc);
		if (result) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "更新商品类目失败！");
	}

	@ApiOperation(value = "获取商品类目", notes = "根据传入的itemNo或者ItemName，获取类目, 如果两者都为空，则获取全部的类目树")
	@GetMapping("/productItem")
	public ResultConstant getProductItem(@RequestParam(required = false) String itemNo,
										@RequestParam(required = false) String itemName, Boolean asTree) {
		List<Map<String, Object>> result = null;
		if (StringUtils.isEmpty(itemName)) {
			result = productItemService.getProductItem(itemName, true, asTree);
		} else {
			result = productItemService.getProductItem(itemNo, asTree);
		}
		if (CollectionUtils.isEmpty(result)) {
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取商品类目失败！");
		}
		return ResultConstant.ofSuccess(result);
	}

	@ApiOperation(value = "新增商品类目", notes = "根据传入的商品类目，新增配置商品类目")
	@PostMapping("/productItem")
	public ResultConstant addProductItem(@RequestBody String itemName, 
										@RequestBody String itemDesc,
										@RequestBody String parentItemNo) {
		boolean result = productItemService.addProductItem(itemName, itemDesc, parentItemNo);
		if (result) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "新增商品类目失败！");
	}

	@ApiOperation(value = "删除商品类目", notes = "根据传入的商品类目编号，删除商品类目")
	@DeleteMapping("/productItem")
	public ResultConstant deleteProductItem(String itemNo) {
		ResultConstant result = productItemService.deleteProductItem(itemNo);
		return result;
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
	public ResultConstant updatejfRaiders(TbJfRaiders jfRaider) {
		TbJfRaiders jfRaiders = jfRaidersService.queryJfRaidersOne(jfRaider.getId());
		if(jfRaiders==null) {
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "文章未找到");
		}
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
	
	@ApiOperation(value = "聚金豆规则信息查询", notes = "获取聚金豆规则设定")
	@GetMapping("/getjvjindouRule")
	public ResultConstant getjvjindouRule() {
		TbJvjindouRule jvjindouRule = jvjindouRuleService.queryTbJvjindouRule();
		return ResultConstant.ofSuccess(jvjindouRule);
	}
	@ApiOperation(value = "聚金豆规则信息修改", notes = "修改赠送聚金豆规则,givingRule(赠送规则，2，固定，1随机)，randomGivingMin(随机赠送最小数),randomGivingMax(最大数),fixedGiving(固定赠送值)")
	@PutMapping("/updatejvjindouRule")
	public ResultConstant updatejvjindouRule(@RequestParam(value="id",required=true)Integer id,
			@RequestParam(value="givingRule",required=true)String givingRule,
			@RequestParam(value="randomGivingMin",required=false)Integer randomGivingMin,
			@RequestParam(value="randomGivingMax",required=false)Integer randomGivingMax,
			@RequestParam(value="fixedGiving",required=false)Integer fixedGiving
			) {
		TbJvjindouRule jvjindouRule =jvjindouRuleService.getJvjindouRule(id);
		if(givingRule.equals(Constant.FIXED_PATTERN)) {
			if(!StringUtils.isEmpty(fixedGiving)){
				jvjindouRule.setFixedGiving(fixedGiving);
			}else {
				return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "参数有误");
			}
		}else if(givingRule.equals(Constant.RANDOUM_PATTERN)) {
			if(!StringUtils.isEmpty(randomGivingMin) && !StringUtils.isEmpty(randomGivingMax)){
				jvjindouRule.setRandomGivingMax(randomGivingMax);
				jvjindouRule.setRandomGivingMin(randomGivingMin);
			}else {
				return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "参数有误");	
			}
		}
		jvjindouRule.setGivingRule(givingRule);
		int result = jvjindouRuleService.updateJvjindouRule(jvjindouRule);
		if(result>0) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "修改失败");
	}
	@ApiOperation(value = "聚金豆规则信息添加", notes = "添加聚金豆规则设定")
	@PostMapping("/addjvjindouRule")
	public ResultConstant addjvjindouRule(TbJvjindouRule jvjindouRule ) {
		if(StringUtils.isEmpty(jvjindouRule)) {
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "参数有误");
		}
		if(jvjindouRule.getGivingRule().equals(Constant.FIXED_PATTERN)) {
			if(!StringUtils.isEmpty(jvjindouRule.getFixedGiving())){
				jvjindouRule.setFixedGiving(jvjindouRule.getFixedGiving());
			}else {
				return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "参数有误");
			}
		}else if(jvjindouRule.getGivingRule().equals(Constant.FIXED_PATTERN)) {
			if(!StringUtils.isEmpty(jvjindouRule.getRandomGivingMin()) && !StringUtils.isEmpty(jvjindouRule.getRandomGivingMax())){
				jvjindouRule.setRandomGivingMax(jvjindouRule.getRandomGivingMax());
				jvjindouRule.setRandomGivingMin(jvjindouRule.getRandomGivingMin());
			}else {
				return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "参数有误");	
			}
		}else {
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "参数有误");
		}
			
		int result = jvjindouRuleService.insertJvjindouRule(jvjindouRule);
		if(result>0) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "修改失败");
	
	}
	
	
	
}
