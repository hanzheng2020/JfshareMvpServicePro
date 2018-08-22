package com.jfshare.mvp.server.service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.jfshare.finagle.thrift.order.Order;
import com.jfshare.finagle.thrift.order.OrderDetailResult;
import com.jfshare.mvp.server.constants.ResultConstant;
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
	
	public String checkOrder(OrderDetailResult result, Integer orderAmount) {
		Order order = result.getOrder();
		if (order == null) {
			return "当前订单不存在！";
		}
		// 校验订单是否关闭 PAY_ORDER_CLOSE
		if ((order.getOrderState() == ConstantUtil.ORDER_STATE.TRADE_ADMIN_CANCEL.getEnumVal())
			|| (order.getOrderState() == ConstantUtil.ORDER_STATE.TRADE_CANCEL.getEnumVal())
			|| (order.getOrderState() == ConstantUtil.ORDER_STATE.TRADE_CLOSE.getEnumVal())
			|| (order.getOrderState() == ConstantUtil.ORDER_STATE.WAIT_DELIVER.getEnumVal())
			|| (order.getOrderState() == ConstantUtil.ORDER_STATE.FINISH_DELIVER.getEnumVal())
			|| (order.getOrderState() == ConstantUtil.ORDER_STATE.WAIT_COMMENT.getEnumVal())
			|| (order.getOrderState() == ConstantUtil.ORDER_STATE.FINISH_DELIVER.getEnumVal())) {
			return "订单状态出错！";
		}
		if (orderAmount == null || !orderAmount.equals(strToInt(order.getClosingPrice()))) {
			return "订单金额校验失败！";
		}
		return "";
	}
	
	private int strToInt(String str) {
		return Math.round(Float.parseFloat(str) * 100);
	}
	
	private int calcuAmt(OrderDetailResult result, Integer jfScore, Integer fenXiangScore) {
		Order order = result.getOrder();
		int orderAmt = strToInt(order.getClosingPrice());
		levelInfoService.lesslevelInfo(order.getUserId(), jfScore + fenXiangScore, order.getOrderId(), strToInt(order.getClosingPrice()), fenXiangScore > 0, fenXiangScore);
		return orderAmt-jfScore-fenXiangScore;
	}
	
	public ResultConstant allScorePay(String userId, String orderId, Integer orderAmount, Integer jfScore, Integer fenXiangScore) {
		OrderDetailResult result = orderClient.queryOrderDetail(userId, orderId);
		String checkOrderResult = checkOrder(result, orderAmount);
		if (StringUtils.isEmpty(checkOrderResult)) {
			int amt = calcuAmt(result, jfScore, fenXiangScore);
			if (amt == 0) {
				return ResultConstant.ofSuccess();
			}
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "订单金额校验失败！");
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, checkOrderResult);
	}
	
	public String weChatPay(String userId, String orderId, Integer orderAmount, String clientIp, Integer jfScore, Integer fenXiangScore) {
		OrderDetailResult result = orderClient.queryOrderDetail(userId, orderId);
		String checkOrderResult = checkOrder(result, orderAmount);
		if (StringUtils.isEmpty(checkOrderResult)) {
			int amt = calcuAmt(result, jfScore, fenXiangScore);
			return weChatPayInterface.createPrepayId("Test", orderId, amt, clientIp);
		} else {
			return "";
		}
	}
	
	public String aliPay(String userId, String orderId, Integer orderAmount, Integer jfScore, Integer fenXiangScore) {
		OrderDetailResult result = orderClient.queryOrderDetail(userId, orderId);
		String checkOrderResult = checkOrder(result, orderAmount);
		if (StringUtils.isEmpty(checkOrderResult)) {
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
