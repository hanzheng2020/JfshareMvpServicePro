package com.jfshare.mvp.server.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfshare.mvp.server.thirdinterface.AliPayInterface;
import com.jfshare.mvp.server.thirdinterface.WeChatInterface;

/**
 * @author fengxiang
 * @date 2018-08-16
 */
@Service
public class ThirdPayService {
	@Autowired
	private WeChatInterface weChatInterface;
	
	@Autowired
	private AliPayInterface aliPayInterface;
	
	public String createWeChatPayOrder(String orderNo, String userIp) {
		
		return weChatInterface.createPrepayId("test", orderNo, 1, userIp);
	}
	
	public String createAliPayOrder(String orderNo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("", "");
		return aliPayInterface.createPaySign(params);
	}
}
