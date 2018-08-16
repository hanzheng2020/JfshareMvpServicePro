package com.jfshare.mvp.server.thirdinterface;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;

/**
 * @author fengxiang
 * @date 2018-08-15
 */
@Component
public class AliPayInterface {
	
	private String privateKey = "";
	
	public String createPaySign(Map<String, String> params) {
		String sign = "";
		try {
			sign = AlipaySignature.rsaSign(params, privateKey, "utf-8");
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return sign;
	}
}
