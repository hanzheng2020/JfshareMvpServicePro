package com.jfshare.mvp.server.controller;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.service.ThirdPayService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;

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
	
	/*
	 * 微信支付
	 */
	private static final int weChatPay = 1;
	/**
	 * 支付宝支付
	 */
	private static final int aliPay = 2;
	
	
	@ApiOperation(value = "调用支付接口", notes = "微信支付返回prepay_id(预支付交易会话标识),支付宝返回sign(签名)")
	@PostMapping("/thirdPay")
	public ResultConstant thirdPay(@ApiParam(value= "{\"userId\":\"用户ID\",\"orderId\":\"订单Id\","
									+ "\"orderAmount\":\"订单金额\",\"payChannel\":\"支付方式，1代表微信，2代表支付宝\"}")
									@RequestBody Map<String, String> map) {
		String result = "";
		int payChannel = Integer.valueOf(map.get("payChannel"));
		int orderAmount = Integer.valueOf(map.get("orderAmount"));
		String userId = map.get("userId");
		String orderId = map.get("orderId");
		String clientIp = "127.0.0.1";
		if (weChatPay == payChannel) {
			result = thirdPayService.createWeChatPayOrder(userId, orderId, orderAmount, clientIp);
			if (StringUtils.isEmpty(result)) {
				ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取微信支付prepay_id失败！");
			}
		}
		if (aliPay == payChannel) {
			result = thirdPayService.createAliPayOrder(userId, orderId, orderAmount);
			if (StringUtils.isEmpty(result)) {
				ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取支付宝sign失败！");
			}
		}
		
		
		return ResultConstant.ofSuccess(result);
	}
}
