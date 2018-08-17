package com.jfshare.mvp.server.thirdinterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.time.DateUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.jfshare.mvp.server.config.ConfigManager;
import com.jfshare.mvp.server.utils.EncryptUtils;
import com.jfshare.mvp.server.utils.UUIDutils;
import com.jfshare.mvp.server.utils.XmlUtils;

/**
 * @author fengxiang
 * @date 2018-08-15
 */
@Component
public class WeChatInterface {
	private final static Logger logger = LoggerFactory.getLogger(WeChatInterface.class);
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ConfigManager configManager;
	
	private static String payUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	private String appid = "wxc93b05e31a57d38c";
	private String mch_id = "1330572901";
	private String key = "d1ed1f014730e48b0f209bd2c00942ba";
	private String notify_url = "";
	
	@PostConstruct
	public void init() {
		notify_url = configManager.getConfigValue("jfx_pay_serv", "weixinpay_notify_url");
	}
	
//	@Value("${weChat.appId}")
	private String appId ;
	
//	@Value("${weChat.appSecret}")
	private String appSecret;
	
//	@Value("${weChat.templateIdSuccess}")
	private String templateIdSuccess;
	
//	@Value("${weChat.templateIdFail}")
	private String templateIdFail;
	
//	@Value("${weChat.redirectUrl}")
	private String redirectUrl;
	
	private String accessToken;
	
	private Date expiryTime;
	
	private static final String getOpenIdUrlFormat = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&grant_type=authorization_code&code=%s";
	
	private static final String sendTemplateMsgUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	
	private static final String getWeChatAccessTokenFormat = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	/**
	 * 根据微信公众号静默授权code 获取open id  发送模板消息
	 * @param code 微信公众号静默授权code
	 * @param attrs 跳转页面链接中传入的参数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String sendTemplateMsg(String userToken, String redirectAttrs, boolean isSuccess, String... keywords) {
		accessToken = getAccessToken();
		String openId = "";
		String fullRedirectUrl = redirectUrl+redirectAttrs;
		Map<String, Object> multiValueMap = new HashMap<String, Object>();
		multiValueMap.put("touser", openId);
		if (isSuccess) {
			multiValueMap.put("template_id", templateIdSuccess);
		} else {
			multiValueMap.put("template_id", templateIdFail);
		}
		
		multiValueMap.put("url", fullRedirectUrl);
		multiValueMap.put("data", createTemplateMsgData(isSuccess ? "您下单的商品充值成功" : "您下单的商品充值失败", 
				isSuccess? "感谢使用，如有问题请联系客服" : "非常抱歉，请联系聚分享客服400-800-2383申请售后服务", keywords));
		logger.info("模板消息请求链接:{},请求参数:{}", sendTemplateMsgUrl + accessToken, JSON.toJSONString(multiValueMap));
		ResponseEntity<String> result = restTemplate.postForEntity(sendTemplateMsgUrl + accessToken, multiValueMap, String.class);
		logger.info("模板消息结果:{}", result);
		return "";
	}
	
	/**
	 * 获取微信token，每100分钟刷新一次
	 * @return
	 */
	public String getAccessToken() {
		if (expiryTime == null || expiryTime.before(new Date())) {
			String url = String.format(getWeChatAccessTokenFormat, appId, appSecret);
			String result = restTemplate.getForObject(url, String.class);
			accessToken = JSON.parseObject(result).getString("access_token");
			expiryTime = DateUtils.addMinutes(new Date(), 100);
			logger.info("刷新Access Token result:{}", result);
		}
		return accessToken;
	}
	
	private static Map<String, Map<String, String>> createTemplateMsgData(String firstStr, String remark, String... keywords) {
		Map<String, Map<String, String>> dataMap = new HashMap<>();
		
		Map<String, String> wordMap = new HashMap<>();
		wordMap.put("value",firstStr);
		dataMap.put("first", wordMap);
		
		Map<String, String> remarkMap = new HashMap<>();
		remarkMap.put("value",remark);
		dataMap.put("remark", remarkMap);
		
		if (keywords.length > 0) {
			Map<String, String> wordMap1 = new HashMap<>();
			wordMap1.put("value", keywords[0]);
			dataMap.put("keyword1", wordMap1);
		}
		if (keywords.length > 1) {
			Map<String, String> wordMap2 = new HashMap<>();
			wordMap2.put("value", keywords[1]);
			dataMap.put("keyword2", wordMap2);
		}
		if (keywords.length > 2) {
			Map<String, String> wordMap3 = new HashMap<>();
			wordMap3.put("value", keywords[2]);
			dataMap.put("keyword3", wordMap3);
		}
		if (keywords.length > 3) {
			Map<String, String> wordMap4 = new HashMap<>();
			wordMap4.put("value", keywords[3]);
			dataMap.put("keyword4", wordMap4);
		}
		return dataMap;
	}
	
	
	/**
	 * 微信支付调用接口生成支付信息串
	 * @param productDesc
	 * @param orderNo
	 * @param amount
	 * @param userIp
	 * @return
	 */
	public String createPrepayId(String productDesc, String orderNo, int amount, String userIp) {
		Map<String, Object> requestMap = new HashMap<>();
		Map<String, Object> context = new HashMap<>();
		
		context.put("appid", appid);
		context.put("mch_id", mch_id);
		context.put("nonce_str", UUIDutils.getUUID());
		context.put("body", productDesc);
		context.put("out_trade_no", orderNo);
		context.put("total_fee", amount);
		context.put("spbill_create_ip", userIp);
		context.put("notify_url", notify_url);
		context.put("trade_type", "APP");
		context.put("sign", createSign(context));
		requestMap.put("xml", context);
		String requestXml = XmlUtils.mapToXml(requestMap);
		String responseXml = restTemplate.postForObject(payUrl, requestXml, String.class);
		String prepay_id = "";
		try {
			Map<String, Object> responseMap = XmlUtils.xmlToMap(responseXml);
			if ("SUCCESS".equals(responseMap.get("return_code")) 
					&& "SUCCESS".equals(responseMap.get("result_code"))) {
				prepay_id = (String) responseMap.get("prepay_id");
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return prepay_id;
	}
	
	private String createSign(Map<String, Object> context) {
		List<String> keyList = new ArrayList<>();
		for (String key : context.keySet()) {
			keyList.add(key);
		}
		Collections.sort(keyList);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < keyList.size(); i ++) {
			if (i != 0) {
				sb.append("&");
			}
			sb.append(keyList.get(i) + "=" + context.get(keyList.get(i)));
		}
		sb.append("&key=" + key);
		String sign = EncryptUtils.md5Encrypt(sb.toString()).toUpperCase();
		return sign;
	}
}
