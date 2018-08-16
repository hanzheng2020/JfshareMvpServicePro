package com.jfshare.mvp.server.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.service.ThirdPayService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author fengxiang
 * @date 2018-08-13
 */
@Api(value = "api", tags = "订单模块相关API")
@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private ThirdPayService thirdPayService;
	
	@ApiOperation(value = "调用微信支付接口", notes = "传入订单号和用户终端IP，返回prepay_id（预支付交易会话标识）")
	@PostMapping("/weChatPay")
	public ResultConstant weChatPay(@RequestParam String orderNo, @RequestParam String userIp) {
		String prepay_id = thirdPayService.createWeChatPayOrder(orderNo, userIp);
		if (StringUtils.isEmpty(prepay_id)) {
			ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取prepay_id失败！");
		}
		return ResultConstant.ofSuccess(prepay_id);
	}
}
