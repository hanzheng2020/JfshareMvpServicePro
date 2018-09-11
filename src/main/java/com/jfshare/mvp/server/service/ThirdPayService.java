package com.jfshare.mvp.server.service;

import com.alibaba.fastjson.JSON;
import com.jfshare.common.PayConstants;
import com.jfshare.finagle.thrift.order.Order;
import com.jfshare.finagle.thrift.order.OrderDetailResult;
import com.jfshare.finagle.thrift.order.PayParam;
import com.jfshare.finagle.thrift.pay.PayChannel;
import com.jfshare.finagle.thrift.result.FailDesc;
import com.jfshare.finagle.thrift.result.Result;
import com.jfshare.finagle.thrift.result.StringResult;
import com.jfshare.mvp.server.constants.ResultConstant;
import com.jfshare.mvp.server.finagle.server.OrderClient;
import com.jfshare.mvp.server.finagle.server.ScoreClient;
import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.thirdinterface.AliPayInterface;
import com.jfshare.mvp.server.thirdinterface.WeChatPayInterface;
import com.jfshare.mvp.server.utils.ConstantUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
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
	private ScoreClient scoreClient;
	
	@Autowired
	private InformationService informationService;
	
	@Autowired
	private ProductService productService;
	
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
	
	private int calcuAmt(OrderDetailResult result, Integer totalScore) {
		Order order = result.getOrder();
		int orderAmt = strToInt(order.getClosingPrice());
		return orderAmt-totalScore;
	}
	
	public ResultConstant allScorePay(String userId, String orderId, Integer orderAmount, Integer jfScore, Integer fenXiangScore) {
		OrderDetailResult result = orderClient.queryOrderDetail(userId, orderId);
		String checkOrderResult = checkOrder(result, orderAmount);
		if (StringUtils.isEmpty(checkOrderResult)) {
			int amt = calcuAmt(result, jfScore);
			if (amt == 0) {
				return ResultConstant.ofSuccess();
			}
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "订单金额校验失败！");
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, checkOrderResult);
	}
	
	/**
	 * 调用第三方支付
	 * @param userId
	 * @param orderId
	 * @param orderAmount
	 * @param jfScore
	 * @param fenXiangScore
	 * @param payChannel
	 * @return
	 */
	public ResultConstant thirdPay(String userId, String orderId, Integer orderAmount, Integer jfScore, Integer fenXiangScore, Integer payChannel, String clientIp) {
		OrderDetailResult orderDetailResult = orderClient.queryOrderDetail(userId, orderId);
		String checkOrderResult = checkOrder(orderDetailResult, orderAmount);
		
		//将用户的分象的积分转换成聚分享积分
		Integer scoreFX = 0;
		if (fenXiangScore > 0) {
			try {
				scoreFX = dealWithFenXiangScore(userId, fenXiangScore);
			} catch (Exception e1) {
				return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, e1.getMessage());
			}
		}
		// 总聚分享积分= 原有聚分享积分+分象转换成聚分享的积分大小
		int totalScore = jfScore + scoreFX;// 总的聚分享积分

		//进行预处理
		StringResult stringResult = preDealOrderInfo(userId, orderId, totalScore, payChannel);

		String payId =null;// 在第三方订单中展示的订单ID
		if(null == stringResult){// thrift链接超时出现null
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, ResultConstant.FAIL_CODE_SYSTEM_ERROR_DESC);
		}else if(0!=stringResult.getResult().code){// 代表处理失败
			FailDesc failDesc = stringResult.getResult().getFailDescList().get(0);
			return ResultConstant.ofFail(Integer.valueOf(failDesc.getFailCode()),failDesc.getDesc());
		}else{
			payId =stringResult.getValue();
		}
		if (StringUtils.isEmpty(checkOrderResult)) {
			//计算实际需要支付的金额
			int amt = calcuAmt(orderDetailResult, totalScore);
			// 暂时一个订单只有一个商品
			String productName = orderDetailResult.getOrder().getProductList().get(0).getProductName();
			if (PayConstants.Channel_WeChatPay_mvp==payChannel) { //调用微信支付接口
				Map<String, Object> resultMap = weChatPayInterface.createPrepayId(productName, amt, clientIp, payId);
				if (MapUtils.isEmpty(resultMap)) {
					return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取微信支付信息串失败！");
				} 
				return ResultConstant.ofSuccess(JSON.toJSONString(resultMap));
			} else if (PayConstants.Channel_AliPay_mvp==payChannel) { //调用支付宝支付接口
				
				String sign = aliPayInterface.createPaySign(productName, amt, payId);
				if (StringUtils.isEmpty(sign)) {
					return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "获取支付宝支付串失败！");
				} 
				return ResultConstant.ofSuccess(sign);
			} else if (0==payChannel) {
				if (amt > 0) {
					return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, "可用积分不足！");
				} else {
					String productId = orderDetailResult.getOrder().getProductList().get(0).getProductId();
					logger.info("积分支付成功》》》推送系统通知，订单id:"+productId+",userId:"+userId+",amt:"+amt);
					
					TbProduct product = productService.getProductOne(productId);
					int integral=product.getPresentexp();
					logger.info("积分支付成功》》》推送系统通知，订单id:"+productId+",userId:"+userId+",integral:"+integral+",amt:"+amt);
					
					StringResult result=levelInfoService.addlevelInfo(Integer.parseInt(userId), integral, orderId, amt);
					logger.info("返回接口:"+result);
					if(result.getResult().getCode()==0) {
							informationService.sendMsg(userId, "支付成功提醒", "商品购买成功，点击查看订单券码详情>>", orderId);
					}
					return ResultConstant.ofSuccess();
				}
			}
		}
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_SYSTEM_ERROR, checkOrderResult);
	}

	/**
	 * 将用户的分象积分转换成聚分享积分
	 * @param userId
	 * @param fenXiangScore
	 * @return  返回兑换成聚分享的积分大小
	 * @throws Exception 
	 */
	private Integer dealWithFenXiangScore(String userId, Integer fenXiangScore) throws Exception {

		// 将分象积分 直接兑换成聚分享的积分
		Result result = scoreClient.pointExpensesFenXiang(Integer.valueOf(userId), fenXiangScore + "");
		if (null == result) {
			String errorMessage = "分象积分兑换出错!";
			logger.error(errorMessage);
			throw new Exception(errorMessage);
		} if (0 != result.getCode()) {
			String errorMessage = "分象积分兑换出错," + result.getFailDescList().get(0).getDesc();
			logger.error(errorMessage);
			throw new Exception(errorMessage);
		}
		return fenXiangScore;
	}

	/**  内部业务逻辑 支付前的预处理 ！！！ */
	private StringResult preDealOrderInfo(String userId, String orderId, Integer totalScore, Integer payChannel) {

		//		TODO case1 全积分支付， case2 混合支付（积分和钱） case3  钱支付
		PayParam payParams =new PayParam();
		payParams.setUserId(Integer.valueOf(userId));
		payParams.setOrderIdList(Arrays.asList(orderId));
		PayChannel channelParams=new PayChannel();
		channelParams.setPayChannel(payChannel);

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
