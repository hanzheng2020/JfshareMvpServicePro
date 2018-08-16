package com.jfshare.mvp.server.thirdinterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.jfshare.mvp.server.utils.EncryptUtils;
import com.jfshare.mvp.server.utils.UUIDutils;
import com.jfshare.mvp.server.utils.XmlUtils;

/**
 * @author fengxiang
 * @date 2018-08-15
 */
@Component
public class WeChatInterface {
	@Autowired
	private RestTemplate restTemplate;
	
	private static String payUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	private String appid = "wxc93b05e31a57d38c";
	private String mch_id = "1330572901";
	private String key = "d1ed1f014730e48b0f209bd2c00942ba";
	private String notify_url = "";
	
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
