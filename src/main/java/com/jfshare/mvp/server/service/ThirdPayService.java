package com.jfshare.mvp.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
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
	
	public static final Logger logger = LoggerFactory.getLogger(ThirdPayService.class);
	
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
		OrderDetailResult result = orderClient.queryOrderDetail(userId, orderId);
		if (checkOrder(result, orderAmount)) {
			return weChatPayInterface.createPrepayId(result.getOrder().getProductList().get(0).getProductName(), orderId, orderAmount, clientIp);
		} else {
			return "";
		}
	}
	
	public String createAliPayOrder(String userId, String orderId, Integer orderAmount) {
		
		try {
			return aliPayInterface.createPaySign();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return "";
	}
}
