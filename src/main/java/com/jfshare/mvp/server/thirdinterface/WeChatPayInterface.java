package com.jfshare.mvp.server.thirdinterface;

import com.jfshare.mvp.server.config.ConfigManager;
import com.jfshare.mvp.server.utils.EncryptUtils;
import com.jfshare.mvp.server.utils.UUIDutils;
import com.jfshare.mvp.server.utils.XmlUtils;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author fengxiang
 * @date 2018-08-17
 */
@Component
public class WeChatPayInterface {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ConfigManager configManager;
	
	private static String payUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	private String appid = "wxc93b05e31a57d38c";
	private String mch_id = "1512993531";
	private String key = "obAgnUgq9maCq78afz07pyn30HighrdA";
	private String notify_url = "";
	
	@PostConstruct
	public void init() {
		notify_url = configManager.getConfigValue("jfx_pay_serv", "weixinpay_notify_url");
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
	public Map<String, Object> createPrepayId(String productDesc, int amount, String userIp,String payId) {
		Map<String, Object> requestMap = new HashMap<>();
		Map<String, Object> context = new HashMap<>();
		context.put("appid", appid);
		context.put("mch_id", mch_id);
		context.put("nonce_str", UUIDutils.getUUID());
		context.put("body", productDesc);
		context.put("out_trade_no", payId);
		context.put("total_fee", amount);
		context.put("spbill_create_ip", userIp);
		context.put("notify_url", notify_url);
		context.put("trade_type", "APP");
		context.put("sign", createSign(context));
		requestMap.put("xml", context);
		String requestXml = XmlUtils.mapToXml(requestMap);
		String responseXml = restTemplate.postForObject(payUrl, requestXml, String.class);
		
		Map<String, Object> resultMap = new HashMap<>();
		try {
			Map<String, Object> responseMap = (Map<String, Object>) XmlUtils.xmlToMap(responseXml).get("xml");
			if ("SUCCESS".equals(responseMap.get("return_code")) 
					&& "OK".equals(responseMap.get("return_msg"))) {
				String prepay_id = (String) responseMap.get("prepay_id");
				resultMap.put("appid", appid);
				resultMap.put("partnerid", mch_id);
				resultMap.put("prepayid", prepay_id);
				resultMap.put("package", "Sign=WXPay");
				resultMap.put("noncestr", UUIDutils.getUUID());
				resultMap.put("timestamp", System.currentTimeMillis());
				resultMap.put("sign", createSign(resultMap));
				resultMap.remove("package");
				resultMap.put("packageValue", "Sign=WXPay");
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	private String createSign(Map<String, Object> context) {
		List<String> keyList = new ArrayList<>(context.keySet());
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
