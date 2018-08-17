package com.jfshare.mvp.server.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfshare.finagle.thrift.order.OrderDetailResult;
import com.jfshare.mvp.server.finagle.server.OrderClient;
import com.jfshare.mvp.server.thirdinterface.AliPayInterface;
import com.jfshare.mvp.server.thirdinterface.WeChatPayInterface;

/**
 * @author fengxiang
 * @date 2018-08-16
 */
@Service
public class ThirdPayService {
	@Autowired
	private WeChatPayInterface weChatPayInterface;
	
	@Autowired
	private AliPayInterface aliPayInterface;
	
	@Autowired
	private OrderClient orderClient;
	
	public boolean checkOrder(OrderDetailResult result, Integer orderAmount) {
		return true;
	}
	
	public String createWeChatPayOrder(String userId, String orderId, Integer orderAmount, String clientIp) {
		OrderDetailResult result = orderClient.queryOrder(userId, orderId);
		if (checkOrder(result, orderAmount)) {
			return weChatPayInterface.createPrepayId(result.getOrder().getProductList().get(0).getProductName(), orderId, orderAmount, clientIp);
		} else {
			return "";
		}
		
	}
	
	public String createAliPayOrder(String orderNo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("", "");
		return aliPayInterface.createPaySign();
	}
}
