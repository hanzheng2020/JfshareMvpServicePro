package com.jfshare.mvp.server.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.jfshare.finagle.thrift.order.Order;
import com.jfshare.finagle.thrift.order.OrderDetailResult;
import com.jfshare.finagle.thrift.result.FailDesc;
import com.jfshare.finagle.thrift.trade.BuyInfo;
import com.jfshare.mvp.server.finagle.server.OrderClient;
import com.jfshare.mvp.server.thirdinterface.AliPayInterface;
import com.jfshare.mvp.server.thirdinterface.WeChatPayInterface;
import com.jfshare.mvp.server.utils.ConstantUtil;
import com.twitter.util.Future;

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
		Order order = result.getOrder();
		// 校验订单是否关闭 PAY_ORDER_CLOSE
		if ((order.getOrderState() == ConstantUtil.ORDER_STATE.TRADE_ADMIN_CANCEL.getEnumVal())
			|| (order.getOrderState() == ConstantUtil.ORDER_STATE.TRADE_CANCEL.getEnumVal())
			|| (order.getOrderState() == ConstantUtil.ORDER_STATE.TRADE_CLOSE.getEnumVal())
			|| (order.getOrderState() == ConstantUtil.ORDER_STATE.WAIT_DELIVER.getEnumVal())
			|| (order.getOrderState() == ConstantUtil.ORDER_STATE.FINISH_DELIVER.getEnumVal())
			|| (order.getOrderState() == ConstantUtil.ORDER_STATE.WAIT_COMMENT.getEnumVal())
			|| (order.getOrderState() == ConstantUtil.ORDER_STATE.FINISH_DELIVER.getEnumVal())) {
			return false;
		}
		
		if (orderAmount == null || !orderAmount.equals(Integer.valueOf(result.getOrder().getClosingPrice()))) {
			return false;
		}
		return true;
	}
	
	public String createWeChatPayOrder(String userId, String orderId, Integer orderAmount, String clientIp) {
		OrderDetailResult result = orderClient.queryOrderDetail(userId, orderId);
		if (checkOrder(result, orderAmount)) {
			return weChatPayInterface.createPrepayId("Test", orderId, orderAmount, clientIp);
		} else {
			return "";
		}
	}
	
	public String createAliPayOrder(String userId, String orderId, Integer orderAmount) {
		OrderDetailResult result = orderClient.queryOrderDetail(userId, orderId);
		if (checkOrder(result, orderAmount)) {
			try {
				return aliPayInterface.createPaySign();
			} catch (AlipayApiException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
}
