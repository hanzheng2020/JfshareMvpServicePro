package com.jfshare.mvp.server.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
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

import com.github.pagehelper.PageInfo;
import com.jfshare.mvp.server.constants.Constant;
import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.model.TbAppVerifySetting;
import com.jfshare.mvp.server.model.TbJfRaiders;
import com.jfshare.mvp.server.model.TbJvjindouRule;
import com.jfshare.mvp.server.model.TbProductItem;
import com.jfshare.mvp.server.model.TbSystemInformation;
import com.jfshare.mvp.server.service.AdminService;
import com.jfshare.mvp.server.service.AppVerifySettingService;
import com.jfshare.mvp.server.service.JfRaidersService;
import com.jfshare.mvp.server.service.JvjindouRuleService;
import com.jfshare.mvp.server.service.ProductItemService;
import com.jfshare.mvp.server.service.PromotionSettingService;
import com.jfshare.mvp.server.service.SystemInformationService;
import com.jfshare.mvp.server.utils.JedisClusterUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
	
	@Autowired
	private SystemInformationService systemInformationService;

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AppVerifySettingService appVerifySettingService;
	

	
	@ApiOperation(value = "IOS上线审核设置", notes = "保存IOS上线审核设置")
	@PostMapping("/appVerifySetting")
	public ResultConstant saveAppVerifySetting(@RequestBody TbAppVerifySetting tbAppVerifySetting) {
		return appVerifySettingService.saveAppVerifyProducts(tbAppVerifySetting);
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "保存并发布推广微页面设置", notes = "保存并发布传入的推广配置和类目商品展示配置")
	@PostMapping("/promotionSetting")
	public ResultConstant savePromotionSetting(@RequestBody Map<String, List<Map>> map) {
		List<Map> productPromotions = map.get("productPromotions");
		List<Map> productItemShows = map.get("productItemShows");
		boolean result = promotionSettingService.savePromotionSetting(productPromotions, productItemShows);
		if (result) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "保存并发布推广微页面设置失败！");
	}
	
	@ApiOperation(value = "获取推广微页面设置", notes = "获取推广配置和类目商品展示配置")
	@GetMapping("/promotionSetting")
	public ResultConstant getPromotionSetting() {
		Map<String, List<Map<String, Object>>> result = promotionSettingService.getPromotionSetting();
		if (!MapUtils.isEmpty(result)) {
			return ResultConstant.ofSuccess(result);
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取推广微页面设置失败！");
	}
	
	

	/*@ApiOperation(value = "发布配置的推广微页面", notes = "publishInd = true时，发布上次保存的推广微页面配置")
	@PostMapping("/publishPromotionSetting")
	public ResultConstant publishPromotionSetting(@RequestBody Boolean publishInd) {
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
	}*/

	@ApiOperation(value = "更新商品类目", notes = "根据传入的商品类目配置，重新配置商品类目")
	@PostMapping("/productItem")
	public ResultConstant updateProductItem(@RequestBody TbProductItem tbProductItem) {
		boolean result = false;
		if (StringUtils.isEmpty(tbProductItem.getItemNo())) {
			result = productItemService.addProductItem(tbProductItem.getItemName(), tbProductItem.getItemDesc(), tbProductItem.getParentItemNo());
		} else {
			result = productItemService.updateProductItem(tbProductItem.getItemNo(), tbProductItem.getItemName(), tbProductItem.getItemDesc());
		}
		if (result) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "更新商品类目失败！");
	}

	@ApiOperation(value = "获取商品类目", notes = "根据传入的itemNo或者ItemName，获取类目, 如果两者都为空，则获取全部的类目树")
	@GetMapping("/productItem")
	public ResultConstant getProductItem(@RequestParam(required = false) String itemNo,
										@RequestParam(required = false) String itemName, 
										Boolean asTree,
										@RequestParam(required = false) Integer pageNum,
										@RequestParam(required = false) Integer pageSize) {
		List<Map<String, Object>> result = null;
		if (!StringUtils.isEmpty(itemName)) {
			result = productItemService.getProductItem(itemName, true, asTree, pageNum, pageSize);
		} else {
			result = productItemService.getProductItem(itemNo, asTree, pageNum, pageSize);
		}
		if (CollectionUtils.isEmpty(result)) {
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取商品类目失败！");
		}
		
		PageInfo<Map<String, Object>> pageResult = new PageInfo<Map<String, Object>>(result);
		return ResultConstant.ofSuccess(pageResult);
	}

	@ApiOperation(value = "删除商品类目", notes = "根据传入的商品类目编号，删除商品类目")
	@DeleteMapping("/productItem")
	public ResultConstant deleteProductItem(@RequestBody Map<String, List<String>> itemNos) {
		ResultConstant result = productItemService.deleteProductItem(itemNos.get("itemNo"));
		return result;
	}
	
	@GetMapping("")
	public ResultConstant getAppVerify() {
		return ResultConstant.ofSuccess();
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
	@PostMapping("/updatejfRaider")
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
	@PostMapping("/jfRaiderRelease")
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
	@PostMapping("/deletejfRaider")
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
		if(jvjindouRule==null) {
			jvjindouRule=new TbJvjindouRule();
			jvjindouRule.setFixedGiving(0);
			jvjindouRule.setGivingRule("");
			jvjindouRule.setRandomGivingMax(0);
			jvjindouRule.setRandomGivingMin(0);
		}
		return ResultConstant.ofSuccess(jvjindouRule);
	}
	@ApiOperation(value = "聚金豆规则信息修改", notes = "修改赠送聚金豆规则,givingRule(赠送规则，2，固定，1随机)，randomGivingMin(随机赠送最小数),randomGivingMax(最大数),fixedGiving(固定赠送值)")
	@PostMapping("/updatejvjindouRule")
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
	public ResultConstant addjvjindouRule(TbJvjindouRule jvjindouRules ) {
		TbJvjindouRule jvjindouRule = jvjindouRuleService.queryTbJvjindouRule();
		if(jvjindouRule==null) {
			int result = jvjindouRuleService.insertJvjindouRule(jvjindouRules);
			if(result>0) {
				return ResultConstant.ofSuccess();
			}
		}else {
			if(jvjindouRules.getGivingRule().equals(Constant.FIXED_PATTERN)) {
				if(!StringUtils.isEmpty(jvjindouRules.getFixedGiving())){
					jvjindouRule.setFixedGiving(jvjindouRules.getFixedGiving());
				}else {
					return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "参数有误");
				}
			}else if(jvjindouRules.getGivingRule().equals(Constant.RANDOUM_PATTERN)) {
				if(!StringUtils.isEmpty(jvjindouRules.getRandomGivingMin()) && !StringUtils.isEmpty(jvjindouRules.getRandomGivingMax())){
					jvjindouRule.setRandomGivingMax(jvjindouRules.getRandomGivingMax());
					jvjindouRule.setRandomGivingMin(jvjindouRules.getRandomGivingMin());
				}else {
					return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "参数有误");	
				}
			}
			jvjindouRule.setGivingRule(jvjindouRules.getGivingRule());
			int result = jvjindouRuleService.updateJvjindouRule(jvjindouRule);
			if(result>0) {
				return ResultConstant.ofSuccess();
			}
		}

		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "修改失败");
	
	}
	
	@ApiOperation(value = "系统消息填加", notes = "添加聚金豆规则设定,title:标题，cont：内容，user：创建用户")
	@PostMapping("/addInformation")
	public ResultConstant addInformation(
			@RequestParam(value="title",required=true)String title,
			@RequestParam(value="cont",required=true)String cont,
			@RequestParam(value="user",required=true)String user) {
		TbSystemInformation systemInformation = new TbSystemInformation();
		Date date  = new Date();
		systemInformation.setTitle(title);
		systemInformation.setStatus(1);
		systemInformation.setContent(cont);
		systemInformation.setCreateUser(user);
		systemInformation.setUpdateTime(date);
		int result = systemInformationService.saveSystemInformation(systemInformation);
		if(result>0) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "添加失败");
	}
	@ApiOperation(value = "系统消息填加并发布", notes = "添加聚金豆规则设定,title:标题，cont：内容，user：创建用户")
	@PostMapping("/addAndReleaseInformation")
	public ResultConstant addAndReleaseInformation(
			@RequestParam(value="title",required=true)String title,
			@RequestParam(value="cont",required=true)String cont,
			@RequestParam(value="user",required=true)String user) {
		TbSystemInformation systemInformation = new TbSystemInformation();
		Date date  = new Date();
		systemInformation.setTitle(title);
		systemInformation.setStatus(2);
		systemInformation.setContent(cont);
		systemInformation.setCreateUser(user);
		systemInformation.setReleaseTime(date);
		systemInformation.setUpdateTime(date);
		int result = systemInformationService.saveSystemInformation(systemInformation);
		if(result>0) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "添加失败");
	}
	
	
	@ApiOperation(value = "管理员登陆", notes = "管理员登陆")
	@PostMapping("/adminLogin")
	public ResultConstant adminLogin(@RequestParam(value="loginId",required=true)String loginId,
			@RequestParam(value="pwd",required=true)String pwd) {
		
		Map<String, Object> map = adminService.adminLogin(loginId,pwd);
		if(map==null){
			return	ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "登录失败");
		}
		return ResultConstant.ofSuccess(map);
	}
	
	@ApiOperation(value="系统消息查询（管理中心）",notes ="系统消息查询,titleOrContent:标题或者内容，page:当前页，pageSize：每页条数")
	@GetMapping("/getInformation")
	public ResultConstant getInformation(
			@RequestParam(value="titleOrContent",required=false)String titleOrContent,
			@RequestParam(value="page",required=true)Integer page,
			@RequestParam(value="pageSize",required=true)Integer pageSize
			) {
		PageInfo pageInfo = systemInformationService.getSystemInformations(titleOrContent, page, pageSize);
		return ResultConstant.ofSuccess(pageInfo);
		
	}
	

	
	@ApiOperation(value="系统消息修改",notes="系统消息修改，id:消息id，title:标题，cont:内容")
	@PostMapping("/updateInformation")
	public ResultConstant updateInformation(
			@RequestParam(value="id",required=true)Integer id,
			@RequestParam(value="title",required=true)String title,
			@RequestParam(value="cont",required=true)String cont) {
		TbSystemInformation systemInformation = systemInformationService.getInformatinInfo(id);
		int result=0;
		if(systemInformation!=null) {
			systemInformation.setTitle(title);
			systemInformation.setContent(cont);
			result = systemInformationService.updateSystemInformation(systemInformation);
		}
		if(result>0) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "修改失败");
	}
	
	@ApiOperation(value="系统消息删除",notes="删除系统消息，id:消息id")
	@PostMapping("/deleteInformation")
	public ResultConstant deleteInformation(@RequestParam(value="id",required=true)String id) {
		int result=0;
		String [] ids = id.split(",");
		for(int i=0;i<ids.length;i++) {
			result = systemInformationService.deleteSystemInformation(Integer.parseInt(ids[i]));
		}
		if(result>0) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "删除失败");
	}

	
	@ApiOperation(value="系统消息详情",notes="获取系统详情，id:消息id")
	@GetMapping("/getInformationInfo")
	public ResultConstant getInformationInfo(
			@RequestParam(value="id",required=true)Integer id) {
		TbSystemInformation systemInformation = systemInformationService.getInformatinInfo(id);
		if(systemInformation!=null) {
			return ResultConstant.ofSuccess(systemInformation);
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "获取失败，请检查id是否正确");
	}
	
	@ApiOperation(value="系统消息发布",notes="系统消息发布，id:消息id")
	@PostMapping("/releaseformation")
	public ResultConstant releaseformation( 
			@RequestParam(value="id",required=true)Integer id) {
		TbSystemInformation systemInformation = systemInformationService.getInformatinInfo(id);
		int result=0;
		if(systemInformation!=null && systemInformation.getStatus()==1) {
			systemInformation.setStatus(2);
			systemInformation.setReleaseTime(new Date());
			result = systemInformationService.updateSystemInformation(systemInformation);
		}
		if(result>0) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "发布失败");
	}
	
}
