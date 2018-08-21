package com.jfshare.mvp.server.thirdinterface;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.jfshare.mvp.server.config.ConfigManager;
import com.jfshare.mvp.server.utils.DateUtils;

/**
 * @author fengxiang
 * @date 2018-08-15
 */
@Component
public class AliPayInterface {
	
	private static final Logger logger = LoggerFactory.getLogger(AliPayInterface.class);
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ConfigManager configManager;
	
	private String app_id = AlipayConfig.APP_ID;
	private String method = AlipayConfig.ALIPAY_GATEWAY_NEW;
	private String charset = AlipayConfig.input_charset;
	private String sign_type = "RSA";
	private String version = AlipayConfig.version;
	private String notify_url = "";
	private String biz_content = "";
	
	private String privateKey = AlipayConfig.ALIPAY_RSA_PRIVATE;
	
	@PostConstruct
	public void init() {
		notify_url = configManager.getConfigValue("jfx_pay_serv", "alipay_notify_url");
	}
	
	public String createPaySign() throws AlipayApiException {
		Map<String, String> payUrlMap = new HashMap<String, String>();
		Map<String, String> map = new HashMap<>();

		// 公共参数
		payUrlMap.put("app_id", AlipayConfig.APP_ID);
		payUrlMap.put("method", "alipay.trade.app.pay");
		payUrlMap.put("charset", AlipayConfig.input_charset);
		payUrlMap.put("timestamp", DateUtils.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
		payUrlMap.put("version", AlipayConfig.version);
		payUrlMap.put("notify_url", "http://43824ea9.ngrok.io/pay/notify/alipay");
//		payUrlMap.put("notify_url", PropertiesUtil.getProperty("jfx_pay_serv", "alipay_notify_url"));
		payUrlMap.put("sign_type", "RSA");
		// 业务参数   !!!注意:业务参数千万不要放在公共参数内!!!
		/* 传递过去的所有业务参数,以JSON格式拼装 */
		map.put("body", "");//商品描述
		map.put("subject", "");//商品名称
		map.put("out_trade_no", "");//商户的唯一编码
		map.put("total_amount", intToStr(1));
		map.put("product_code", "QUICK_MSECURITY_PAY");//销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
		map.put("goods_type", "0");//商品主类型：0—虚拟类商品，1—实物类商品
		map.put("passback_params", "");//公共回传参数,会原封不动传回
		map.put("enable_pay_channels", "balance,moneyFund,creditCard,debitCardExpress");//可用渠道以','区隔
		payUrlMap.put("biz_content", JSON.toJSONString(map));//业务请求参数的集合，最大长度不限

		// 进行urlEncode编码公共参数
		String payUrlFromAppSDK = buildOrderParam(payUrlMap);
		// 加密sign并进行urlEncode编码
		String sign = AlipaySignature.rsaSign(payUrlMap, privateKey, "utf-8");
		payUrlFromAppSDK = payUrlFromAppSDK + "&" + sign;
		logger.info("AliApp支付申请payUrlFromAppSDK ==> " + payUrlFromAppSDK);
		return payUrlFromAppSDK;
	}
	
	 private static String intToStr(int priceInt) {
	        boolean isPositivenumber = true;
	        if (priceInt <= 0) {
	            priceInt = Math.abs(priceInt);
	            isPositivenumber = false;
	        }
	        if (priceInt == 0) {
	            return "0.00";
	        }
	        String priceStr = priceInt + "";
	        String data1 = "";
	        String data2 = "";
	        int length = priceStr.length();
	        if (length >= 3) {
	            data1 = priceStr.substring(0, length - 2);
	            data2 = priceStr.substring(length - 2, length);
	        }
	        if (length == 2) {
	            data1 = "0";
	            data2 = priceStr;
	        }
	        if (length == 1) {
	            data1 = "0";
	            data2 = "0" + priceStr;
	        }
	        priceStr = data1 + "." + data2;
	        if (!isPositivenumber) {
	            priceStr = "-" + priceStr;
	        }
	        return priceStr;
	    }
	
	private static String buildOrderParam(Map<String, String> map) {
		List<String> keys = new ArrayList<String>(map.keySet());

		StringBuilder sb = new StringBuilder();
		// map的倒数第二个
		for (int i = 0; i < keys.size() - 1; i++) {
			String key = keys.get(i);
			String value = map.get(key);
			sb.append(buildKeyValue(key, value, true));
			sb.append("&");
		}
		//map的最后一个参数
		String tailKey = keys.get(keys.size() - 1);
		String tailValue = map.get(tailKey);
		sb.append(buildKeyValue(tailKey, tailValue, true));

		return sb.toString();
	}
	
	private static String buildKeyValue(String key, String value, boolean isEncode) {
		StringBuilder sb = new StringBuilder();
		sb.append(key);
		sb.append("=");
		if (isEncode) {
			try {
				sb.append(URLEncoder.encode(value, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				sb.append(value);
			}
		} else {
			sb.append(value);
		}
		return sb.toString();
	}
}
