package com.jfshare.mvp.server.thirdinterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.jfshare.mvp.server.config.ConfigManager;
import com.jfshare.mvp.server.dao.JedisClusterDao;
import com.jfshare.mvp.server.utils.EncryptUtils;
import com.jfshare.mvp.server.utils.UUIDutils;
import com.jfshare.mvp.server.utils.XmlUtils;

/**
 * @author fengxiang
 * @date 2018-08-17
 */
@Component
public class WeChatPayInterface {
	private static final transient Logger logger = LoggerFactory.getLogger(WeChatPayInterface.class);

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ConfigManager configManager;
	@Autowired
	private JedisClusterDao jedisClusterDao;
	
	private static String payUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	private String appid = "wxc93b05e31a57d38c";
	private String mch_id = "1512993531";
	private String key = "obAgnUgq9maCq78afz07pyn30HighrdA";
	private String applt_mch_id = "10011931";
	private String appletKey = "esvlhExwkEjICItRmHJqwP65ohRrFeJR";
	
	
	private String notify_url = "";
	
	private String applet_notify_url = "";
	
	@PostConstruct
	public void init() {
		notify_url = configManager.getConfigValue("jfx_pay_serv", "weixinpay_notify_url_mvp");
		applet_notify_url = configManager.getConfigValue("jfx_pay_serv", "weixinpay_notify_url_mvp_applet");
	}

	/**
	 * 微信支付调用接口生成支付信息串
	 * @param productDesc
	 * @param orderId
	 * @param amount
	 * @param userIp
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> createPrepayId(String productDesc, int amount, String userIp,String payId, String client, String customCode) {
		Map<String, Object> requestMap = new HashMap<>();
		Map<String, Object> context = new HashMap<>();
		if ("wxApplet".equals(client)) {
			context.put("appid", WeChatAppletInterface.appId);
			context.put("trade_type", "JSAPI");
			context.put("mch_id", applt_mch_id);
			context.put("openid", getOpenId(customCode));
			context.put("notify_url", applet_notify_url);
		} else {
			context.put("appid", appid);
			context.put("trade_type", "APP");
			context.put("mch_id", mch_id);
			context.put("notify_url", notify_url);
		}
		context.put("nonce_str", UUIDutils.getUUID());
		context.put("body", productDesc);
		context.put("out_trade_no", payId);
		context.put("total_fee", amount);
		context.put("spbill_create_ip", userIp);
		
		
		
		context.put("sign", createSign(context, client));
		requestMap.put("xml", context);
		String requestXml = XmlUtils.mapToXml(requestMap);
		logger.info("生成支付信息串请求："+requestXml);
		String responseXml = restTemplate.postForObject(payUrl, requestXml, String.class);
		logger.info("生成支付信息串响应："+responseXml);
		Map<String, Object> resultMap = new HashMap<>();
		try {
			Map<String, Object> responseMap = (Map<String, Object>) XmlUtils.xmlToMap(responseXml).get("xml");
			if ("SUCCESS".equals(responseMap.get("return_code")) 
					&& "OK".equals(responseMap.get("return_msg"))) {
				String prepay_id = (String) responseMap.get("prepay_id");
				if ("wxApplet".equals(client)) {
					resultMap.put("appId", WeChatAppletInterface.appId);
					resultMap.put("nonceStr", UUIDutils.getUUID());
					resultMap.put("package", "prepay_id=" + prepay_id);
					resultMap.put("signType", "MD5");
					resultMap.put("timeStamp", System.currentTimeMillis()/1000);
					resultMap.put("paySign", createSign(resultMap, client));
				} else {
					resultMap.put("appid", appid);
					resultMap.put("partnerid", mch_id);
					resultMap.put("prepayid", prepay_id);
					resultMap.put("package", "Sign=WXPay");
					resultMap.put("noncestr", UUIDutils.getUUID());
					resultMap.put("timestamp", System.currentTimeMillis()/1000);
					resultMap.put("sign", createSign(resultMap, client));
					resultMap.remove("package");
					resultMap.put("packageValue", "Sign=WXPay");
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	private String getOpenId(String customCode) {
		String inStr = jedisClusterDao.getString("MVP_sessionKey:" + customCode);
		logger.info("获取的inStr："+inStr);
		return inStr.split("&&")[1];
	}
	
	private String createSign(Map<String, Object> context, String client) {
		List<String> keyList = new ArrayList<>(context.keySet());
		Collections.sort(keyList);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < keyList.size(); i ++) {
			if (i != 0) {
				sb.append("&");
			}
			sb.append(keyList.get(i) + "=" + context.get(keyList.get(i)));
		}
		if ("wxApplet".equals(client)) {
			sb.append("&key=" + appletKey);
		} else {
			sb.append("&key=" + key);
		}
		System.out.println(sb.toString());
		String sign = EncryptUtils.md5Encrypt(sb.toString()).toUpperCase();
		return sign;
	}
}
