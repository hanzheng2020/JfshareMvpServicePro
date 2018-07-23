package com.jfshare.mvp.server.controller;


import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jfshare.mvp.server.constants.Constant;
import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.model.TbJfRaiders;
import com.jfshare.mvp.server.model.TbProductItemShow;
import com.jfshare.mvp.server.model.TbProductPromotion;
import com.jfshare.mvp.server.service.JfRaidersService;
import com.jfshare.mvp.server.service.JvjindouRuleService;
import com.jfshare.mvp.server.service.LevelInfoService;
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
}
