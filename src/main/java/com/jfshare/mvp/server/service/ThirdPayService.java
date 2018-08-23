package com.jfshare.mvp.server.service;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.jfshare.common.PayConstants;
import com.jfshare.finagle.thrift.order.Order;
import com.jfshare.finagle.thrift.order.OrderDetailResult;
import com.jfshare.finagle.thrift.order.PayParam;
import com.jfshare.finagle.thrift.pay.PayChannel;
import com.jfshare.finagle.thrift.result.FailDesc;
import com.jfshare.finagle.thrift.result.StringResult;
import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.finagle.server.OrderClient;
import com.jfshare.mvp.server.thirdinterface.AliPayInterface;
import com.jfshare.mvp.server.thirdinterface.WeChatPayInterface;
import com.jfshare.mvp.server.utils.ConstantUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

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
		StringResult stringResult = levelInfoService.lesslevelInfo(order.getUserId(), jfScore + fenXiangScore, order.getOrderId(), strToInt(order.getClosingPrice()), fenXiangScore > 0, fenXiangScore);
		/*if (CollectionUtils.isEmpty(stringResult.getResult().getFailDescList())) {
			
		}*/
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

	/**
	 * 微信支付
	 * @param userId
	 * @param orderId
	 * @param orderAmount
	 * @param clientIp
	 * @param jfScore
	 * @param fenXiangScore
	 * @return
	 */
	public ResultConstant weChatPay(String userId, String orderId, Integer orderAmount, String clientIp, Integer jfScore, Integer fenXiangScore) {
		OrderDetailResult result = orderClient.queryOrderDetail(userId, orderId);
		String checkOrderResult = checkOrder(result, orderAmount);


		//TODO 将用户的分象的积分转换成聚分享积分
		Integer scoreFX = dealWithFenXiangScore(userId, fenXiangScore);
		// 总聚分享积分= 原有聚分享积分+分象转换成聚分享的积分大小
		int totalScore = jfScore + scoreFX;// 总的聚分享积分

		//进行预处理
		StringResult stringResult = preDealOrderInfo(userId, orderId, totalScore);

		String payId =null;
		if(0!=stringResult.getResult().code){// 代表处理失败
			FailDesc failDesc = stringResult.getResult().getFailDescList().get(0);
			return ResultConstant.ofFail(Integer.valueOf(failDesc.getFailCode()),failDesc.getDesc());
		}else{
			payId =stringResult.getValue();
		}

		if (StringUtils.isEmpty(checkOrderResult)) {
			int amt = calcuAmt(result, jfScore, fenXiangScore);
			Map<String, Object> resultMap = weChatPayInterface.createPrepayId("Test", orderId, amt, clientIp,payId);
			if (MapUtils.isEmpty(resultMap)) {
				return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取微信支付信息串失败！");
			} 
			return ResultConstant.ofSuccess(JSON.toJSONString(resultMap));
		} else {
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, checkOrderResult);
		}
	}

	/**
	 * 支付宝 支付
	 * @param userId
	 * @param orderId
	 * @param orderAmount 订单总金额
	 * @param jfScore
	 * @param fenXiangScore
	 * @return
	 */
	public ResultConstant aliPay(String userId, String orderId, Integer orderAmount, Integer jfScore, Integer fenXiangScore) {

		OrderDetailResult result = orderClient.queryOrderDetail(userId, orderId);
		String checkOrderResult = checkOrder(result, orderAmount);

		//TODO 将用户的分象的积分转换成聚分享积分
		Integer scoreFX = dealWithFenXiangScore(userId, fenXiangScore);
		// 总聚分享积分= 原有聚分享积分+分象转换成聚分享的积分大小
		int totalScore = jfScore + scoreFX;// 总的聚分享积分

		//进行预处理
		StringResult stringResult = preDealOrderInfo(userId, orderId, totalScore);

		String payId =null;
		if(0!=stringResult.getResult().code){// 代表处理失败
			FailDesc failDesc = stringResult.getResult().getFailDescList().get(0);
			return ResultConstant.ofFail(Integer.valueOf(failDesc.getFailCode()),failDesc.getDesc());
		}else{
			payId =stringResult.getValue();
		}


		if (StringUtils.isEmpty(checkOrderResult)) {
			try {
				int amt = calcuAmt(result, jfScore, fenXiangScore);
				// 暂时一个订单只有一个商品
				String productName = result.getOrder().getProductList().get(0).getProductName();
				String sign = aliPayInterface.createPaySign(orderId, productName, "test", amt,payId);
				if (StringUtils.isEmpty(sign)) {
					return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取支付宝支付串失败！");
				} 
				return ResultConstant.ofSuccess(sign);
			} catch (AlipayApiException e) {
				e.printStackTrace();
			}
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, checkOrderResult);
	}

	/**
	 * 将用户的分象积分转换成聚分享积分
	 * @param userId
	 * @param fenXiangScore
	 * @return  返回兑换成聚分享的积分大小
	 */
	private Integer dealWithFenXiangScore(String userId, Integer fenXiangScore) {

		//TODO 1、将分象积分 直接兑换成聚分享的积分


		//TODO 2、返回兑换成 聚分享的实际积分数量



		return 0;


	}

	/**  内部业务逻辑 支付前的预处理 ！！！ */
	private StringResult preDealOrderInfo(String userId, String orderId,  Integer totalScore) {

		//		TODO case1 全积分支付， case2 混合支付（积分和钱） case3  钱支付
		PayParam payParams =new PayParam();
		payParams.setUserId(Integer.valueOf(userId));
		payParams.setOrderIdList(Arrays.asList(orderId));
		PayChannel channelParams=new PayChannel();
		channelParams.setPayChannel(PayConstants.Channel_AliPay_mvp);

		payParams.setPayChannel(channelParams);

		//这里需要将分象积分转换成 聚分享积分，然后进行累加
		payParams.setExchangeScore(totalScore);

		// 兑换成具体多少钱 ,积分的钱除以100  ；  100积分=1元
		BigDecimal b1 = new BigDecimal(totalScore);
		BigDecimal b2 = new BigDecimal(100);
		String cashStr = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).toPlainString();
		payParams.setExchangeCash(cashStr);

		// 订单的业务逻辑处理；返回payId
		StringResult stringResult = orderClient.beforePayDoSomeStuff(payParams);


		return stringResult;


	}
}
