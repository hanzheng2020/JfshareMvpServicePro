package com.jfshare.mvp.server.controller;


import org.springframework.util.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jfshare.mvp.server.constants.Constant;
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
	
	@ApiOperation(value="订单消费聚金豆", notes="根据传入的使用类型，进行扣减聚金豆")
	@PostMapping("/openOrDisabledJvjindou")
	public ResultConstant openOrDisabledJvjindou(@RequestParam(value="userId", required=true)  int userId
			,@RequestParam(value="useStatus", required=true) int useStatus
			,@RequestParam(value="jvjindou", required=true) int jvjindou) {
		ResultConstant resultConstant=new ResultConstant();
		resultConstant.setCode(0);
		resultConstant.setDesc("成功");
		if(!StringUtils.isEmpty(userId)){
			if(useStatus==Constant.USE_JVJINDOU){
				if(jvjindou<Constant.JVJINDOU_NUM){
					resultConstant.setCode(0);
					resultConstant.setDesc("使用聚金豆的数量大于0");
					return resultConstant.ofSuccess();
				}else{
					//走抵扣聚金豆的逻辑
					
				}
			}else if(useStatus==Constant.DISABLED_JVJINDOU){
				resultConstant.setCode(0);
				resultConstant.setDesc("不使用聚金豆");
				return resultConstant.ofSuccess();
			}
		}else{
			return resultConstant.ofFail(500, "参数有误");
		}
		return resultConstant.ofSuccess();
	}
	
}
