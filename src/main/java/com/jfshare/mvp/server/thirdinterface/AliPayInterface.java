package com.jfshare.mvp.server.thirdinterface;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.jfshare.mvp.server.config.ConfigManager;

/**
 * @author fengxiang
 * @date 2018-08-15
 */
@Component
public class AliPayInterface {
	
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
	
	public String createPaySign() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("app_id", app_id);
		params.put("method", method);
		params.put("charset", charset);
		params.put("sign_type", sign_type);
		params.put("version", version);
		params.put("notify_url", notify_url);
		params.put("biz_content", biz_content);
		String sign = "";
		try {
			sign = AlipaySignature.rsaSign(params, privateKey, "utf-8");
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return sign;
	}
}
