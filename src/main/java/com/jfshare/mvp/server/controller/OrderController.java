package com.jfshare.mvp.server.controller;

import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.service.ThirdPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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

	@Autowired
	private HttpServletRequest request;
	
	@ApiOperation(value = "调用支付接口", notes = "微信支付返回prepay_id(预支付交易会话标识),支付宝返回sign(签名)")
	@PostMapping("/thirdPay")
	public ResultConstant thirdPay(@ApiParam(value= "{\"userId\":\"用户ID\",\"orderId\":\"订单Id\","
									+ "\"orderAmount\":\"订单金额\",\"payChannel\":\"支付方式，201代表微信，202代表支付宝\","
									+ "\"jfScore\":\"扣减的聚金豆\", \"fenXiangScore\":\"扣减的分象积分\"}")
									@RequestBody Map<String, String> map) {
		
		int payChannel = Integer.valueOf(map.get("payChannel"));
		int orderAmount = Integer.valueOf(map.get("orderAmount"));
		int jfScore = Integer.valueOf(map.get("jfScore"));
		int fenXiangScore = Integer.valueOf(map.get("fenXiangScore"));
		String userId = map.get("userId");
		String orderId = map.get("orderId");

		System.out.println("X-Real-IP : "+request.getHeader("X-Real-IP"));
		System.out.println("X-Real-IP : "+request.getHeader("X-Real-IP"));
		System.out.println("X-Real-IP : "+request.getHeader("X-Real-IP"));
		System.out.println("X-Real-IP : "+request.getHeader("X-Real-IP"));


		return thirdPayService.thirdPay(userId, orderId, orderAmount, jfScore, fenXiangScore, payChannel);

	}
}
