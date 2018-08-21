package com.jfshare.mvp.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.jfshare.finagle.thrift.order.Order;
import com.jfshare.finagle.thrift.order.OrderDetailResult;
import com.jfshare.mvp.server.finagle.server.OrderClient;
import com.jfshare.mvp.server.thirdinterface.AliPayInterface;
import com.jfshare.mvp.server.thirdinterface.WeChatPayInterface;
import com.jfshare.mvp.server.utils.ConstantUtil;

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
	
	@Autowired
	private LevelInfoService levelInfoService;
	
	public boolean checkOrder(OrderDetailResult result, Integer orderAmount) {
		Order order = result.getOrder();
		if (order == null) {
			return false;
		}
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
	
	private int calcuAmt(OrderDetailResult result, Integer jfScore, Integer fenXiangScore) {
		Order order = result.getOrder();
		int orderAmt = Integer.valueOf(order.getClosingPrice());
		levelInfoService.lesslevelInfo(order.getUserId(), jfScore + fenXiangScore, order.getOrderId(), Integer.valueOf(order.getClosingPrice()), fenXiangScore > 0, fenXiangScore);
		return orderAmt-jfScore-fenXiangScore;
	}
	
	public boolean allScorePay(String userId, String orderId, Integer orderAmount, Integer jfScore, Integer fenXiangScore) {
		OrderDetailResult result = orderClient.queryOrderDetail(userId, orderId);
		if (checkOrder(result, orderAmount)) {
			int amt = calcuAmt(result, jfScore, fenXiangScore);
			if (amt == 0) {
				return true;
			}
		}
		return false;
	}
	
	public String weChatPay(String userId, String orderId, Integer orderAmount, String clientIp, Integer jfScore, Integer fenXiangScore) {
		OrderDetailResult result = orderClient.queryOrderDetail(userId, orderId);
		if (checkOrder(result, orderAmount)) {
			int amt = calcuAmt(result, jfScore, fenXiangScore);
			return weChatPayInterface.createPrepayId("Test", orderId, amt, clientIp);
		} else {
			return "";
		}
	}
	
	public String aliPay(String userId, String orderId, Integer orderAmount, Integer jfScore, Integer fenXiangScore) {
		OrderDetailResult result = orderClient.queryOrderDetail(userId, orderId);
		if (checkOrder(result, orderAmount)) {
			try {
				int amt = calcuAmt(result, jfScore, fenXiangScore);
				return aliPayInterface.createPaySign(orderId, result.getOrder().getProductList().get(0).getProductName(), "test", amt);
			} catch (AlipayApiException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
}
