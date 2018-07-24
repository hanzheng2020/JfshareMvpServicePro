package com.jfshare.mvp.server.controller;


import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.jfshare.mvp.server.model.TbJfRaiders;
import com.jfshare.mvp.server.model.TbProductItemShow;
import com.jfshare.mvp.server.model.TbProductPromotion;
import com.jfshare.mvp.server.service.JfRaidersService;
import com.jfshare.mvp.server.service.JvjindouRuleService;
import com.jfshare.mvp.server.service.LevelInfoService;
import com.jfshare.mvp.server.service.ProductItemService;
import com.jfshare.mvp.server.service.PromotionSettingService;
import com.jfshare.mvp.server.utils.OSSUtils;

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
	private PromotionSettingService promotionSettingService;
	
	@Autowired
	private ProductItemService productItemService;
	@Autowired
	private LevelInfoService levelInfoService;
	
	@Autowired
	private JfRaidersService jfRaidersService;
	
	@ApiOperation(value="更新商品推广设置", 
			notes="根据传入的推广配置信息，重新配置推广商品")
	@PostMapping("/promotionProducts")
	public ResultConstant updateProductPromotion(ArrayList<TbProductPromotion> tbProductPromotions) {
		boolean result = promotionSettingService.updateProductPromotion(tbProductPromotions);
		if (result) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "更新商品推广设置失败！");
	}
	
	@ApiOperation(value="更新类目商品展示设置", 
			notes="根据传入的类目商品展示信息，重新配置类目商品展示")
	@PostMapping("/productItemShows")
	public ResultConstant updateProductItemShow(ArrayList<TbProductItemShow> tbProductItemShows) {
		boolean result = promotionSettingService.updateProductItemShow(tbProductItemShows);
		if (result) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "更新类目商品展示设置失败！");
	}
	
	@ApiOperation(value="更新商品类目树", 
			notes="根据传入的商品类目，重新配置商品类目树")
	@PutMapping("/productItem")
	public ResultConstant updateProductItem(String itemNo,
			   @RequestParam(required=false) String itemName,
			   @RequestParam(required=false) String itemDesc) {
		boolean result = productItemService.updateProductItem(itemNo, itemName, itemDesc);
		if (result) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "更新商品类目树失败！");
	}
	
	@ApiOperation(value="获取商品类目树", 
			notes="根据传入的类目编号，获取类目以及当前类目的所有子节点")
	@GetMapping("/productItem")
	public ResultConstant getProductItem(@RequestParam(required=false) String itemNo) {
		
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "更新类目商品展示设置失败！");
	}
	
	@ApiOperation(value="更新商品类目树", 
			notes="根据传入的商品类目，重新配置商品类目树")
	@PostMapping("/productItem")
	public ResultConstant addProductItem(ArrayList<TbProductItemShow> tbProductItemShow) {
		boolean result = promotionSettingService.updateProductItemShow(tbProductItemShow);
		if (result) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "更新类目商品展示设置失败！");
	}
	
	@ApiOperation(value="更新商品类目树", 
			notes="根据传入的商品类目，重新配置商品类目树")
	@DeleteMapping("/productItem")
	public ResultConstant deleteProductItem(ArrayList<TbProductItemShow> tbProductItemShow) {
		boolean result = promotionSettingService.updateProductItemShow(tbProductItemShow);
		if (result) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "更新类目商品展示设置失败！");
	}
	
	@ApiOperation(value="订单消费聚金豆", notes="根据传入的使用类型，进行扣减聚金豆")
	@PostMapping("/openOrDisabledJvjindou")
	public ResultConstant openOrDisabledJvjindou(@RequestParam(value="userId", required=true)  Integer userId
			,@RequestParam(value="useStatus", required=true) Integer useStatus
			,@RequestParam(value="jvjindou", required=true) Integer jvjindou) {
		ResultConstant resultConstant=new ResultConstant();
		resultConstant.setCode(0);
		resultConstant.setDesc("成功");
		if(!StringUtils.isEmpty(userId)){
			if(useStatus==Constant.USE_JVJINDOU){
				if(jvjindou<Constant.JVJINDOU_NUM){
					return resultConstant.ofFail(Constant.JVJINDOU_PARR_ERROR, "使用聚金豆的数量大于0");
				}else{
					//走抵扣聚金豆的逻辑
					try {
						levelInfoService.openOrdisableJvjindou(userId, jvjindou);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}else if(useStatus==Constant.DISABLED_JVJINDOU){
				//不使用聚金豆
				return resultConstant.ofSuccess();
			}
		}else{
			return resultConstant.ofFail(Constant.JVJINDOU_PARR_ERROR, "参数有误");
		}
		return resultConstant.ofSuccess();
	}
	

	@ApiOperation(value="积分攻略文章添加", notes="根据传入的类型，添加积分攻略文章")
	@PostMapping("/addjfRaider")
	public ResultConstant addjfRaiders(TbJfRaiders jfRaiders,
			@RequestParam(value="jfRaidersImg", required=true) MultipartFile jfRaidersImg) {
		ResultConstant resultConstant=new ResultConstant();
		resultConstant.setCode(0);
		resultConstant.setDesc("成功");
		String imgUrl="";
		try {
	        if (!jfRaidersImg.isEmpty()) {
	        	Date date = new Date();
	        	StringBuffer sb = new StringBuffer();
	        	sb.append("jfRaider");
				InputStream inputStream=jfRaidersImg.getInputStream();
				String imgName = jfRaidersImg.getOriginalFilename();
				String[] imgNames =  "jf.png".split("\\.");
				long time = date.getTime();
				sb.append(time);
				sb.append(".");
				sb.append(imgNames[(imgNames.length-1)]);
				 imgUrl = OSSUtils.uploadFile2OssForTemp(inputStream,sb.toString());
				 jfRaiders.setImgUrl(imgUrl);
	        }
	      int result =   jfRaidersService.addjfRaiders(jfRaiders);
	      if(result<1) {
	    	  resultConstant.setCode(1);
	    	  resultConstant.setDesc("添加失败");
	      }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultConstant.ofSuccess();

	}

	@ApiOperation(value="积分攻略文章查询", notes="根据传入的类型，查询积分攻略文章")
	@PostMapping("/queryjfRaider")
	public ResultConstant queryjfRaiders(TbJfRaiders jfRaiders,
			@RequestParam(value="page", required=true)Integer page,
			@RequestParam(value="pageSize", required=true)Integer pageSize) {
		PageInfo<Map<String, Object>> pageInfo =jfRaidersService.queryJfRaiders(jfRaiders, page, pageSize);
		return ResultConstant.ofSuccess(pageInfo);
	}
	
	@ApiOperation(value="积分攻略文章更新", notes="根据传入的类型，更新积分攻略文章")
	@PostMapping("/updatejfRaider")
	public ResultConstant updatejfRaiders(TbJfRaiders jfRaider,
			@RequestParam(value="jfRaiderId", required=true)Integer jfRaiderId,
			@RequestParam(value="jfRaidersImg", required=false) MultipartFile jfRaidersImg
			) {
		TbJfRaiders jfRaiders =jfRaidersService.queryJfRaidersOne(jfRaiderId);
		if(!StringUtils.isEmpty(jfRaider.getTitle())) {
			jfRaiders.setTitle(jfRaider.getTitle());
		}
		if(!StringUtils.isEmpty(jfRaider.getContent())) {
			jfRaiders.setContent(jfRaider.getContent());
		}
		try {
        if (!StringUtils.isEmpty(jfRaidersImg)) {
        	Date date = new Date();
        	StringBuffer sb = new StringBuffer();
        	sb.append("jfRaider");
			InputStream inputStream = jfRaidersImg.getInputStream();
			String imgName = jfRaidersImg.getOriginalFilename();
			String[] imgNames =  imgName.split("\\.");
			long time = date.getTime();
			sb.append(time);
			sb.append(".");
			sb.append(imgNames[(imgNames.length-1)]);
			String imgUrl = OSSUtils.uploadFile2OssForTemp(inputStream,sb.toString());
			 jfRaiders.setImgUrl(imgUrl);
        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int result = jfRaidersService.updateJfRaiders(jfRaiders);
		if(result>0) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "修改失败");
	}
	
	@ApiOperation(value="积分攻略文章发布", notes="发布积分攻略文章")
	@PostMapping("/jfRaiderRelease")
	public ResultConstant updatejfRaiders(
			@RequestParam(value="id", required=true)Integer id) {
		TbJfRaiders jfRaiders =jfRaidersService.queryJfRaidersOne(id);
		jfRaiders.setStatus(2);
		jfRaiders.setReleaseTime(new Date());
		int result = jfRaidersService.updateJfRaiders(jfRaiders);
		if(result>0) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "发布失败");
	}
	
	
	
	@ApiOperation(value="积分攻略文章删除", notes="根据传入的文章id，删除文章")
	@PostMapping("/deletejfRaider")
	public ResultConstant deletejfRaiders(@RequestParam(value="id",required=true) Integer id) {
		int result = jfRaidersService.deletejfRaiders(id);
		if(result>0) {
			return ResultConstant.ofSuccess();
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "修改失败");
	}
}
