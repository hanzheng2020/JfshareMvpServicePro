package com.jfshare.mvp.server.thirdinterface;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

/**
 * @author fengxiang
 * @date 2018-08-15
 */
//@Component
public class WeChatAppletInterface {
	private final static Logger logger = LoggerFactory.getLogger(WeChatAppletInterface.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${wxApplet.appId}")
	private String appId ;
	
	@Value("${wxApplet.appSecret}")
	private String appSecret;
	
	
	private String accessToken;
	
	private Date expiryTime;
	
	
	private static final String getWXACodeUnlimitUrl = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";
	
	private static final String getWeChatAccessTokenFormat = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	
	
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
	
	public byte[] createProductQRCode(String productId) {
		accessToken = getAccessToken();
		Map<String, Object> multiValueMap = new HashMap<String, Object>();
		multiValueMap.put("scene", productId);
		multiValueMap.put("page", "pages/shop/product/product");
		multiValueMap.put("is_hyaline", true);
//		restTemplate.pos
		ResponseEntity<Byte> result = restTemplate.postForEntity(getWXACodeUnlimitUrl + accessToken, multiValueMap, Byte.class);
		logger.info("模板消息结果:{}", result);
		return null;
	}
}
